import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** Database class creates a tree that
 *  can be imported and exported to a
 *  text document
 *
 *  <p> Database > Course > Deck > Flashcard </p>
 *
 *  @author RMizelle
 *  @version V1.1
 */
public class Database {

    private ArrayList<Course> database;
    private File data;

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
                    String parts[] = line.substring(4).split(",");
                    database.get(cIndex).add(new Deck(parts[0], parts[1]));
                    dIndex++;
                }
                //else if data is a flashcard
                else if (line.contains("**")) {
                    //extrapolates term and definition
                    String parts[] = line.substring(2).split(",");
                    database.get(cIndex).get(dIndex).add(new Flashcard(parts[0], parts[1]));
                }
                else {
                    System.out.println("ERROR: Data Corrupted");
                    return false;
                }
            }
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
                output.write("******" + c.getName() + "\n");
                //loops through decks
                for (Deck d : c.get()) {
                    output.write("****" + d.getName() + "," + d.getDescription() + "\n");
                    //loops through flashcard
                    for(Flashcard f: d.get()) {
                        output.write("**" + f.getTerm() + "," + f.getDef() + "\n");
                    }
                }
            }
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
}
