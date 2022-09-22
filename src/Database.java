import java.io.File;
import java.util.ArrayList;

/** Database class creates a tree that
 *  can be imported and exported to a
 *  text document
 *
 *  <p> Database > Course > Deck > Flashcard </p>
 *
 *  @author RMizelle
 *  @version V0.1
 */
public class Database {

    private ArrayList<Course> database;

    /** 0-arg constructor implements ArrayList of course objects */
    public Database() {
        //checks for data.txt

        //if data.txt exists, calls import method

        //else instantiates empty Database
    }

    //////////////////// Data Methods ////////////////////

    /** importData method instantiates database using data.txt */
    private void importData() {
        //TO-DO
    }

    /** exportData method takes existing Database and implements data.txt */
    public void exportData() {
        //TO-DO
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

