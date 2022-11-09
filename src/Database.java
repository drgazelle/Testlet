import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Scanner;

/** Database class creates a tree that
 *  can be imported and exported to a
 *  text document
 *
 *  <p> Database > Course > Deck > Flashcard </p>
 *
 *  @author RMizelle
 *  @version V1.2
 */
public class Database implements TreeSelectionListener, TreeModelListener, ActionListener {

    private final ArrayList<Course> database;
    private File data;
    private JTree tree;
    private Deck userDeck = null;
    private Object userSelected;

    private JFrame frame;
    JLabel selectionText;

    private static final String ADD_COMMAND = "add";
    private static final String REMOVE_COMMAND = "remove";
    private static final String CLEAR_COMMAND = "clear";
    private static final String CONFIRM_COMMAND = "confirm";

    /** 0-arg constructor implements ArrayList of
     *  Course objects from a text document.
     */
    public Database() {
        database = new ArrayList<>();
        //creates resource folder if necessary
        File directory = new File("resources");
        if (!directory.exists()) {
            System.out.println("New Resources Directory Generated");
            directory.mkdir();
        }
        //checks for data.txt
        try {
            data = new File("resources/data.txt");
            //if data.txt does not exist, instantiates empty Database
            if (data.createNewFile()) {
                System.out.println("New Data File Generated");
            }
            //else data.txt exists, calls import method
            else {
                System.out.println("Accessing Data...");
                if (importData()) {
                    System.out.println("Data Retrieved Successfully");
                }
                else {
                    System.out.println("ERROR: Failed to Retrieve Data");
                }
            }
        }
        catch (IOException e) {
            //Error when generating Database
            System.out.println("ERROR: Could not generate Database");
            e.printStackTrace();
        }
    }

    //////////////////// Data Methods ////////////////////

    /** Displays a JFrame containing the JTree representation of Database */
    public void showDatabaseGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Database");
        //content panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        //Sets Decks selection text
        selectionText = new JLabel("Please Select a Deck");

        //JTree UI
        JScrollPane treeView = new JScrollPane(toTree());

        //JTree Commands panel
        JPanel options = new JPanel(new GridLayout(0,5));
        options.setBackground(Color.lightGray);
        //Allows Users to add Courses or Decks
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBackground(new Color(154,205,50));
        confirmButton.setOpaque(true);
        confirmButton.setActionCommand(CONFIRM_COMMAND);
        confirmButton.addActionListener(this);

        //Allows Users to add Courses or Decks
        JButton addButton = new JButton("Add");
        addButton.setActionCommand(ADD_COMMAND);
        addButton.addActionListener(this);

        //Allows Users to remove Courses or Decks
        JButton removeButton = new JButton("Remove");
        removeButton.setActionCommand(REMOVE_COMMAND);
        removeButton.addActionListener(this);

        //Allows Users to clear Database
        JButton clearButton = new JButton("Clear");
        clearButton.setActionCommand(CLEAR_COMMAND);
        clearButton.addActionListener(this);

        //adds to options panel
        options.add(addButton);
        options.add(removeButton);
        options.add(clearButton);
        options.add(new JLabel(""));
        options.add(confirmButton);

        //adds panels to JFrame
        panel.add(selectionText, BorderLayout.BEFORE_FIRST_LINE);
        panel.add(treeView, BorderLayout.CENTER);
        panel.add(options, BorderLayout.AFTER_LAST_LINE);

        //sets frame attributes
        frame.setContentPane(panel);
        frame.setIconImage(new ImageIcon("resources/icons/database.png").getImage());
        frame.setSize(640, 380);
        frame.setLocation(50, 50);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /** Converts database structure to JTree,
     * adds tree listeners
     *
     * @return JTree representation of database
     */
    public JTree toTree() {
        //JTree variables
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Database");
        DefaultMutableTreeNode course;
        DefaultMutableTreeNode deck;
        DefaultMutableTreeNode flashcard;

        //Loops through database tree
        for(Course c : database) {
            //adds course TreeNode to root
            course = new DefaultMutableTreeNode(c);
            root.add(course);
            for(Deck d : c.get()) {
                //adds deck TreeNode to course
                deck = new DefaultMutableTreeNode(d);
                course.add(deck);
                for(Flashcard f : d.get()){
                    //adds flashcard TreeNode to root
                    flashcard = new DefaultMutableTreeNode(f);
                    deck.add(flashcard);
                }
            }
        }
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        treeModel.addTreeModelListener(this);
        //instantiates JTree
        tree = new JTree(treeModel);
        tree.setCellRenderer(new DatabaseTreeCellRender());
        tree.setCellEditor(new DatabaseTreeCellEditor(tree, (DefaultTreeCellRenderer) tree.getCellRenderer()));
        tree.setEditable(true);
        tree.setRootVisible(false);
        tree.addTreeSelectionListener(this);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        return tree;
    }

    /** Converts JTree to database structure */
    private void updateDatabase() {
        //clears database
        database.clear();
        //navigates database
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
        for (int x = 0; x < root.getChildCount(); x++) {
            //Tree node -> Course object
            DefaultMutableTreeNode nodeC = (DefaultMutableTreeNode) root.getChildAt(x);
            Course c = new Course(nodeC.getUserObject().toString());
            //navigates course children
            for (int y = 0; y < nodeC.getChildCount(); y++) {
                //Tree node -> Deck object
                DefaultMutableTreeNode nodeD = (DefaultMutableTreeNode) nodeC.getChildAt(y);
                Deck d = new Deck(nodeD.getUserObject().toString());
                //navigates Deck children
                for(int z = 0; z < nodeD.getChildCount(); z++) {
                    //Tree node -> Flashcard object
                    DefaultMutableTreeNode nodeF = (DefaultMutableTreeNode) nodeD.getChildAt(z);
                    Flashcard f = (Flashcard) (nodeF.getUserObject());
                    nodeF.setUserObject(f);
                    d.add(f);
                }
                nodeD.setUserObject(d);
                c.add(d);
            }
            //adds course to database
            nodeC.setUserObject(c);
            database.add(c);
        }
    }

    /** importData method instantiates database using data.txt
     *  @return true if successfully instantiated database, false if error
     */
    private boolean importData() {
        //course index
        int cIndex = -1;
        // deck index
        int dIndex = -1;

        try {
            //creates scanner
            Scanner input = new Scanner(data);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                //if data is a course
                if (line.contains("******")) {
                    //adds new course
                    database.add(new Course(line.substring(6).trim()));
                    //resets deck and course index
                    dIndex = -1;
                    // increments course index
                    cIndex++;
                }
                //else if data is a deck
                else if (line.contains("****")) {
                    //extrapolates name and description
                    String[] parts = line.substring(4).split(",");
                    if (parts.length == 1) {
                        database.get(cIndex).add(new Deck(parts[0]));
                    }
                    else {
                        database.get(cIndex).add(new Deck(parts[0], parts[1]));
                    }
                    //increments index
                    dIndex++;
                }
                //else if data is a flashcard
                else if (line.contains("**")) {
                    //extrapolates term and definition
                    String[] parts = line.substring(2).split(",");
                    database.get(cIndex).get(dIndex).add(new Flashcard(parts[0].trim(), parts[1].trim()));
                }
                else {
                    System.out.println("ERROR: Data Corrupted");
                    return false;
                }
            }
            //closes scanner
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Couldn't Read File");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /** exportData method takes existing
     *  Database and implements data.txt
     *  @return true if successful, false if error
     */
    public boolean exportDatabase() {
        try {
            FileWriter output = new FileWriter(data);
            //loops through courses
            for (Course c : database) {
                output.write("******" + c.toString() + "\n");
                //loops through decks
                for (Deck d : c.get()) {
                    output.write("****" + d.getName() + "," + d.getDescription() + "\n");
                    //loops through flashcard
                    for(Flashcard f: d.get()) {
                        output.write("**" + f.getTerm() + "," + f.getDef() + "\n");
                    }
                }
            }
            //closes fileReader
            output.close();
        }
        catch (IOException e) {
            System.out.println("ERROR: Failure to write data.txt");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /** Clears course ArrayList and writes clears data.txt
     * @return true if successfully clear both data.txt and
     *         Array list of Courses, false otherwise
     */
    public boolean wipe() {
        database.clear();
        if (exportDatabase()) {
            System.out.println("Data Successfully Wiped");
            return true;
        }
        System.out.println("ERROR: Failed to Wipe Database");
        return false;
    }

    ////////////////////  Database Methods ////////////////////

    /** Converts TreePath to corresponding Objects
     * @return Object[] of TreePath excluding root (i.e. [Course, Deck, Flashcard])
     */
    public Object[] databasePath(TreePath path) {
        ArrayList<Object> list = new ArrayList<>();
        //works from child to root
        while (path.getParentPath() != null) {
            list.add(0, ((DefaultMutableTreeNode) path.getLastPathComponent()).getUserObject());
            path = path.getParentPath();
        }
        //converts ArrayList to Array
        return list.toArray();
    }

    /** Accessor method for database
     * @return ArrayList of Courses
     */
    public ArrayList<Course> getContent() {
        return database;
    }

    /** Appends the specified Course to the end of this database. */
    public boolean add(Course c) {
        return database.add(c);
    }
    /** Inserts the specified Course at the specified position in this database.
     * @param i index
     * @param c Course
     */
    public void add(int i, Course c) {
        database.add(i, c);
    }
    /** Removes all Courses from this database. */
    public void clear() {
        database.clear();
    }

    /** Returns true if this database contains the specified Course.
     *  @param c Course comparable
     *  @return true if database contains Course, false otherwise
     */
    public boolean contains(Course c) {
        return database.contains(c);
    }

    /** Returns the Course at the specified position in this database.
     *  @param i index
     *  @return Course at index
     */
    public Course get(int i) {
        return database.get(i);
    }

    /** Returns the index of the first occurrence of the
     *  specified Course in this database, or -1 if this database
     *  does not contain the Course.
     *
     *  @param c Course comparable
     *  @return index of Course, -1 if non-existent
     */
    public int indexOf(Course c) {
        return database.indexOf(c);
    }

    /** Removes the Course at the specified position in this database.
     * @param i index
     * @return previous Course at index i
     */
    public Course remove(int i) {
        return database.remove(i);
    }

    /** Removes the first occurrence of the specified
     *  Course from this database, if it is present.
     * @param c Course to be removed
     * @return true if removed, false otherwise
     */
    public boolean remove(Course c) {
        return database.remove(c);
    }

    /** Replaces the Course at the specified position
     *  in this database with the specified Course.
     * @param i index
     * @param c specified Course
     * @return previous Course at index
     */
    public Course set(int i, Course c) {
        return database.set(i, c);
    }

    /** Returns the number of elements in this list.
     * @return database size
     */
    public int size() {
        return database.size();
    }

    ////////////////////  Listener Methods ////////////////////

    /** Implements TreeSelectionListeners */
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                tree.getLastSelectedPathComponent();

        //if nothing is selected
        if (node == null) return;
        //retrieve the node that was selected
        Object nodeInfo = node.getUserObject();
        //sets current selected
        userSelected = nodeInfo;
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        System.out.println("[Database] User Selected: " + nodeInfo.toString());
        //Checks if parent is Deck
        if (parent.getUserObject() instanceof Deck) {
            nodeInfo = parent.getUserObject();
        }
        //Checks if node is Deck
        if (nodeInfo instanceof Deck) {
            userDeck = (Deck) nodeInfo;
            selectionText.setText("Selected Deck: " + userDeck.toString());
        }
    }

    /** Implements Action Listener */
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (ADD_COMMAND.equals(command)) {
            //Add button clicked
            System.out.println("[Database] User Selected: Add Button");

            DefaultMutableTreeNode parentNode = null;
            DefaultMutableTreeNode newNode = null;

            if (tree.getLastSelectedPathComponent() == null) {
                parentNode = (DefaultMutableTreeNode) tree.getModel().getRoot();
            }
            else {
                parentNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            }

            //generates new node
            if (parentNode.getAllowsChildren()) {
                int level = parentNode.getLevel();
                //database
                if (level == 0) {
                    newNode = new DefaultMutableTreeNode(new Course("Course"));
                }
                //course
                else if (level == 1) {
                    newNode = new DefaultMutableTreeNode(new Deck("Deck"));
                    System.out.print("Deck");
                }
                //deck
                else if (level == 2) {
                    newNode = new DefaultMutableTreeNode(new Flashcard("Term", "Def"), false);
                    System.out.print("Flashcard");
                }
                System.out.println("[Database] Added new " + newNode.getUserObject().toString() + " to " + parentNode.getUserObject().toString());

                //inserts node into model
                DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
                treeModel.insertNodeInto(newNode, parentNode, parentNode.getChildCount());
            }
            else {
                System.out.println("[Database] User Selection is a Child Node");
            }
        }
        else if (REMOVE_COMMAND.equals(command)) {
            //Remove button clicked

            System.out.println("[Database] User Selected: Remove Button");

            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            TreePath path = tree.getSelectionPath();

            if (path != null) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        path.getLastPathComponent();
                if (node.getParent() != null) {
                    model.removeNodeFromParent(node);
                }
            }
            if (userSelected != null) {
                //outputs to terminal
                System.out.println("[Database] User Removed " + userSelected.toString());

                //unselects deck is removed
                if (userSelected == userDeck) {
                    userDeck = null;
                    selectionText.setText("Please Select a Deck");
                }
                //unselects current object
                userSelected = null;
            }
            else {
                System.out.println("[Database] No Objected Selected to be Removed");
            }
        }
        else if (CLEAR_COMMAND.equals(command)) {
            //Clear button clicked.

            //wipes JTree
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
            root.removeAllChildren();
            model.reload();
            //wipes database
            database.clear();
            System.out.println("[Database] User Selected: Clear Button");
        }
        else if (CONFIRM_COMMAND.equals(command)) {
            System.out.println("[Database] User Selected: Confirm Button");
            updateDatabase();
            exportDatabase();
            MainPanel.setDeck(userDeck);
            frame.dispose();
        }
    }

    @Override
    public void treeNodesChanged(TreeModelEvent e) {
        updateDatabase();
    }

    @Override
    public void treeNodesInserted(TreeModelEvent e) {

    }

    @Override
    public void treeNodesRemoved(TreeModelEvent e) {

    }

    @Override
    public void treeStructureChanged(TreeModelEvent e) {

    }
}

/** DatabaseTreeCellEditor class modifies DefaultTreeCellEditor to block modification of flashcards
 *  <p> Special thanks to Rob Spoor for isCellEditable() method basis </p>
 *  @author RMizelle, Rob Spoor
 */
class DatabaseTreeCellEditor extends DefaultTreeCellEditor {

    public DatabaseTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    @Override
    public boolean isCellEditable(EventObject event) {
        if(!super.isCellEditable(event)) {
            return false;
        }
        if(event != null && event.getSource() instanceof JTree && event instanceof MouseEvent) {
            MouseEvent mouseEvent = (MouseEvent) event;
            JTree tree = (JTree) event.getSource();
            TreePath path = tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
            return path.getPathCount() != 4;
        }
        return false;
    }



}

/** DatabaseTreeCellRender modifies Default TreeCellEditor to use custom icons
 * @author RMizelle
 */
class DatabaseTreeCellRender extends DefaultTreeCellRenderer {

    private Border border = BorderFactory.createEmptyBorder ( 2, 0, 2, 0);

    @Override
    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {

        super.getTreeCellRendererComponent(
                tree, value, sel,
                expanded, leaf, row,
                hasFocus);

        //sets user object

        Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
        ImageIcon imageIcon;
        int dimensions = 36;
        //course node
        if (userObject instanceof Course) {
            imageIcon = new ImageIcon(new ImageIcon("resources/icons/course.png").getImage().getScaledInstance(dimensions, dimensions, Image.SCALE_DEFAULT));
            this.setIcon(imageIcon);
        }
        //closed deck node
        else if (userObject instanceof Deck && !expanded) {
            imageIcon = new ImageIcon(new ImageIcon("resources/icons/deck.png").getImage().getScaledInstance(dimensions, dimensions, Image.SCALE_DEFAULT));
            this.setIcon(imageIcon);
        }
        //open deck node
        else if (userObject instanceof Deck) {
            imageIcon = new ImageIcon(new ImageIcon("resources/icons/deckOpen.png").getImage().getScaledInstance(dimensions, dimensions, Image.SCALE_DEFAULT));
            this.setIcon(imageIcon);
        }
        //flashcard node
        else if (userObject instanceof Flashcard) {
            imageIcon = new ImageIcon(new ImageIcon("resources/icons/flashcard.png").getImage().getScaledInstance(dimensions, dimensions, Image.SCALE_DEFAULT));
            this.setIcon(imageIcon);
        }
        //sets border
        this.setBorder(border);

        return this;
    }
}