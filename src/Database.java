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

    /** importData method instantiates database using data.txt */
    private void importData() {
        //TO-DO
    }

    /** exportData method takes existing Database and implements data.txt */
    public void exportData() {
        //TO-DO
    }
}
