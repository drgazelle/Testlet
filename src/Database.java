import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
import javax.swing.tree.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Scanner;

/** Database class creates a tree that
 *  can be imported and exported to a
 *  text document. The data can be
 *  modified graphically in the form
 *  of a JTree.
 *
 *  <p> Database > Course > Deck > Flashcard </p>
 *
 *  @author RMizelle
 *  @version V2.0
 */
public class Database implements TreeSelectionListener, TreeModelListener, ActionListener {

    //UI Modifiers
    private final Font font = new Font("atkinson hyperlegible", Font.PLAIN, 18);
    private final Color menuColor = new Color(135, 206, 235);
    private final int width = 640;
    private final int height = 380;

    private JFrame frame;
    JLabel selectionText;
    JTextField nameEditor;
    JTextField defEditor;

    //Database variables
    private final ArrayList<Course> database;
    private File data;
    private JTree tree;
    private Deck userDeck = null;
    private Object userSelected;

    //Command Variables
    private static final String ADD_COMMAND = "add";
    private static final String REMOVE_COMMAND = "remove";
    private static final String CLEAR_COMMAND = "clear";
    private static final String CONFIRM_COMMAND = "confirm";
    private static final String IMPORT_COMMAND = "import";

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

    //////////////////// Display Methods ////////////////////

    /** Displays a JFrame containing the JTree representation of Database */
    public void showDatabaseGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Database");
        //content panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(menuColor);

        //Sets Decks selection text
        selectionText = new JLabel("Please Select a Deck");
        selectionText.setFont(font);

        //JTree UI
        JScrollPane treeView = new JScrollPane(toTree());

        //JTree Commands panel
        JPanel options = new JPanel(new GridLayout(0,6));
        options.setBackground(menuColor);
        //Allows Users to add Courses or Decks
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setToolTipText("Confirm Deck Selection");
        confirmButton.setBackground(new Color(154,205,50));
        confirmButton.setOpaque(true);
        confirmButton.setActionCommand(CONFIRM_COMMAND);
        confirmButton.addActionListener(this);
        confirmButton.setFont(font);

        //Allows Users to add Courses or Decks
        JButton addButton = new JButton("Add");
        addButton.setToolTipText("Add a New Item to Database Selection");
        addButton.setActionCommand(ADD_COMMAND);
        addButton.addActionListener(this);
        addButton.setFont(font);

        //Allows Users to import Quizlet Sets
        JButton importButton = new JButton("Import");
        importButton.setToolTipText("Import Quizlet Sets");
        importButton.setActionCommand(IMPORT_COMMAND);
        importButton.addActionListener(this);
        importButton.setFont(font);

        //Allows Users to remove Courses or Decks
        JButton removeButton = new JButton("Remove");
        removeButton.setToolTipText("Remove Selected Item");
        removeButton.setActionCommand(REMOVE_COMMAND);
        removeButton.addActionListener(this);
        removeButton.setFont(font);

        //Allows Users to clear Database
        JButton clearButton = new JButton("Clear");
        clearButton.setToolTipText("Wipe Database");
        clearButton.setActionCommand(CLEAR_COMMAND);
        clearButton.addActionListener(this);
        clearButton.setFont(font);

        //adds to options panel
        options.add(addButton);
        options.add(importButton);
        options.add(removeButton);
        options.add(clearButton);
        options.add(new JLabel(""));
        options.add(confirmButton);

        Font fontSmall = font.deriveFont(Font.PLAIN, 14);

        //text-fields Testing
        JPanel editPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //base constraints
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;

        //term constraints
        c.gridx = 0;
        c.weightx = 0;
        c.ipadx = 10;

        //term editor
        JLabel boxText = new JLabel("Term");
        boxText.setFont(fontSmall);
        editPanel.add(boxText, c);

        nameEditor = new JTextField(20);
        nameEditor.addActionListener(this);
        nameEditor.setFont(fontSmall);

        //term editor constraints
        c.gridx = 1;
        c.weightx = 1;
        c.ipadx = 0;

        editPanel.add(nameEditor, c);

        //def editor
        //def constraints
        c.gridx = 2;
        c.weightx = 0;
        c.ipadx = 1;
        boxText = new JLabel("Definition");
        boxText.setFont(fontSmall);
        editPanel.add(boxText, c);

        defEditor = new JTextField(20);
        defEditor.addActionListener(this);
        defEditor.setFont(fontSmall);
        //def editor constraints
        c.gridx = 3;
        c.weightx = 1;
        c.ipadx = 0;
        editPanel.add(defEditor, c);

        JSplitPane inputs = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editPanel, options);
        inputs.setContinuousLayout(true);

        //adds panels to JFrame
        panel.add(selectionText, BorderLayout.NORTH);
        panel.add(treeView, BorderLayout.CENTER);
        panel.add(inputs, BorderLayout.SOUTH);


        //sets frame attributes
        frame.setContentPane(panel);
        frame.setIconImage(new ImageIcon("resources/icons/database.png").getImage());
        frame.setSize(width, height);
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
    private JTree toTree() {
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
                    flashcard = new DefaultMutableTreeNode(f, false);
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
        tree.addMouseListener(getMouseListener(tree));
        tree.setEditable(true);
        tree.setRootVisible(false);
        tree.setFont(font);
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

    //////////////////// Data Methods ////////////////////

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
                    //extrapolates name adds new deck
                        database.get(cIndex).add(new Deck(line.substring(4).trim()));
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
                    output.write("****" + d.getName() + "\n");
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
        if (node == null) {
            nameEditor.setText("");
            return;
        }
        //retrieve the node that was selected
        Object nodeInfo = node.getUserObject();
        //sets current selected
        userSelected = nodeInfo;
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        System.out.println("[Database] User Selected: " + nodeInfo.toString());
        nameEditor.setText(nodeInfo.toString());
        //Checks if parent is Deck
        if (parent.getUserObject() instanceof Deck) {
            nodeInfo = parent.getUserObject();
        }
        //Checks if node is Deck
        if (nodeInfo instanceof Deck) {
            userDeck = (Deck) nodeInfo;
            selectionText.setText("Selected Deck: " + userDeck.toString());
        }
        //checks if node is Flashcard
        if (userSelected instanceof Flashcard) {
            defEditor.setText(((Flashcard) userSelected).getDef());
        }
        else {
            defEditor.setText("");
        }

    }

    /** Implements Action Listener */
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (ADD_COMMAND.equals(command)) {
            //Add button clicked
            System.out.println("[Database] User Selected: Add Button");

            DefaultMutableTreeNode parentNode;
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
                treeModel.reload(parentNode);
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
            int n = JOptionPane.showConfirmDialog(frame,
                    "This will delete ALL of your data, do you want to proceed?",
                    "WARNING",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (n == 0) {
                //wipes JTree
                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
                root.removeAllChildren();
                model.reload();
                //wipes database
                database.clear();
            }
            System.out.println("[Database] User Selected: Clear Button");
        }
        else if (CONFIRM_COMMAND.equals(command)) {
            //Confirms button clicked
            System.out.println("[Database] User Selected: Confirm Button");
            updateDatabase();
            exportDatabase();
            MainPanel.setDeck(userDeck);
            frame.dispose();
        }
        else if (IMPORT_COMMAND.equals(command)) {
            //Import button clicked
            String instructions = "NOTICE: Only Quizlets sets created by the user can be imported \n\n" +
                                    "STEP 1) Go to www.Quizlet.com\n" +
                                    "STEP 2) Open a Quizlet Set\n" +
                                    "STEP 3) Click on the three dots (...) and select 'export'\n\n" +
                                    "STEP 4a) Make sure 'Between term and definition' is set to 'tab'\n" +
                                    "STEP 4b) Set 'Between rows' to custom, typing '####' into the box\n" +
                                    "STEP 4c) Copy the text at the bottom\n\n" +
                                    "STEP 5) Paste the copied text to the text-box below and press 'OK'\n\n";
            //checks that userDeck is selected
            if (userDeck != null) {
                //prompts user for Quizlet Set
                String s = (String) JOptionPane.showInputDialog(frame, instructions, "Import Quizlet", JOptionPane.PLAIN_MESSAGE, null, null, "");
                //if not cancelled
                if (s != null) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    //if child node selected
                    if (node.getLevel() == 3) {
                        node = (DefaultMutableTreeNode) node.getParent();
                    }
                    //new Flashcards
                    Deck newDeck = (Deck) node.getUserObject();
                    //old Flashcards
                    int start = newDeck.size();
                    //if correctly formatted
                    if (newDeck.importQuizlet(s)) {
                        //sets node to deck
                        node.setUserObject(newDeck);
                        //loops through updated deck
                        for(int i = start; i < newDeck.size(); i++) {
                            DefaultMutableTreeNode child = new DefaultMutableTreeNode(newDeck.get(i));
                            child.setAllowsChildren(false);
                            node.add(child);
                        }
                        //updates Tree and Deck
                        userDeck = newDeck;
                        tree.updateUI();
                    }
                    //if incorrectly formatted
                    else {
                        JOptionPane.showMessageDialog(frame, "Failed to Import Set", "ERROR", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }

        //Text-field editors
        String name = nameEditor.getText();
        String def = defEditor.getText();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (userSelected instanceof Flashcard) {
            Flashcard card = (Flashcard) node.getUserObject();
            card.setTerm(name);
            card.setDef(def);
            node.setUserObject(card);
        }
        else if (userSelected != null) {
            node.setUserObject(name);
        }
        updateDatabase();
    }

    /** updates database everytime a node is changed */
    @Override
    public void treeNodesChanged(TreeModelEvent e) {
        updateDatabase();
        tree.updateUI();
    }

    @Override
    public void treeNodesInserted(TreeModelEvent e) {
        updateDatabase();
        tree.updateUI();
    }

    @Override
    public void treeNodesRemoved(TreeModelEvent e) {

    }

    @Override
    public void treeStructureChanged(TreeModelEvent e) {

    }

    /** MouseListener that enables tree to be deselected when outside of rows
     *
     * <p> Special thanks to Ioan M for code basis </p>
     * @param tree to be listened to
     * @return modified mouse listener
     */
    private static MouseListener getMouseListener(final JTree tree) {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(tree.getRowForLocation(e.getX(),e.getY()) == -1) {
                    System.out.println("[Database] User Clicked outside of JTree");
                    tree.clearSelection();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
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

    private Border border = BorderFactory.createEmptyBorder( 2, 2, 2, 2);

    /** Adds icons and border */
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

        int level = ((DefaultMutableTreeNode) value).getLevel();

        ImageIcon imageIcon;
        //Dimensions of Icons
        final int dimensions = 36;
        //course node
        if (level == 1) {
            imageIcon = new ImageIcon(new ImageIcon("resources/icons/course.png").getImage().getScaledInstance(dimensions, dimensions, Image.SCALE_DEFAULT));
            this.setIcon(imageIcon);
        }
        //closed deck node
        else if (level == 2 && !expanded) {
            imageIcon = new ImageIcon(new ImageIcon("resources/icons/deck.png").getImage().getScaledInstance(dimensions, dimensions, Image.SCALE_DEFAULT));
            this.setIcon(imageIcon);
        }
        //open deck node
        else if (level == 2) {
            imageIcon = new ImageIcon(new ImageIcon("resources/icons/deckOpen.png").getImage().getScaledInstance(dimensions, dimensions, Image.SCALE_DEFAULT));
            this.setIcon(imageIcon);
        }
        //flashcard node
        else if (level == 3) {
            imageIcon = new ImageIcon(new ImageIcon("resources/icons/flashcard.png").getImage().getScaledInstance(dimensions, dimensions, Image.SCALE_DEFAULT));
            this.setIcon(imageIcon);
        }
        //sets border
        this.setBorder(border);
        return this;
    }
}