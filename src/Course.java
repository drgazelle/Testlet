import java.util.ArrayList;

/** Course class defines an ArrayList
 *  of Decks
 *  @author RMizelle
 *  @since 9/16/2022
 *  @version V0.1
 */
public class Course {
    private ArrayList<Deck> course;
    private String name;

    /** 1-arg constructor instantiates Deck ArrayList
     *  and sets name
     * @param name course/subject name
     */
    public Course(String name) {
        course = new ArrayList<Deck>();
        this.name = name;
    }

    /** setName method modifies name */
    public void setName(String name) {
        this.name = name;
    }
}
