import java.util.ArrayList;

/** Course class defines an ArrayList
 *  of Decks
 *  @author RMizelle
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

    /** Accessor method for name */
    public String getName() {
        return name;
    }

    /** move method changes the position of a
     *  Deck within a Course
     *  @param indexStart initial index of Deck
     *  @param indexEnd final index of Deck
     */
    public void move(int indexStart, int indexEnd) {
        Deck temp = course.remove(indexStart);
        course.add(indexEnd, temp);
    }
}
