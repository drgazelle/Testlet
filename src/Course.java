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

    /**
     * 0-arg construction instantiates Deck
     * ArrayList and sets name to empty
     */
    public Course() {
        course = new ArrayList<Deck>();
        name = "";
    }

    //////////////////// Name Methods ////////////////////

    /** setName method modifies name
     * @param name Course name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Accessor method for name
     *  @return Course name
     */
    public String getName() {
        return name;
    }

    //////////////////// Course Methods ////////////////////

    /** Appends the specified Deck to the end of this course. */
    public boolean add(Deck c) {
        return course.add(c);
    }
    /** Inserts the specified Deck at the specified position in this course.
     * @param i index
     * @param c Deck
     */
    public void add(int i, Deck c) {
        course.add(i, c);
    }
    /** Removes all Decks from this course. */
    public void clear() {
        course.clear();
    }

    /** Returns true if this course contains the specified Deck.
     *  @param c Deck comparable
     *  @return true if course contains Deck, false otherwise
     */
    public boolean contains(Deck c) {
        return course.contains(c);
    }

    /** Returns the Deck at the specified position in this course.
     *  @param i index
     *  @return Deck at index
     */
    public Deck get(int i) {
        return course.get(i);
    }

    /** Returns the index of the first occurrence of the
     *  specified Deck in this course, or -1 if this course
     *  does not contain the Deck.
     *
     *  @param c Deck comparable
     *  @return index of Deck, -1 if non-existent
     */
    public int indexOf(Deck c) {
        return course.indexOf(c);
    }

    /** Removes the Deck at the specified position in this course.
     * @param i index
     * @return previous Deck at index i
     */
    public Deck remove(int i) {
        return course.remove(i);
    }

    /** Removes the first occurrence of the specified
     *  Deck from this course, if it is present.
     * @param c Deck to be removed
     * @return true if removed, false otherwise
     */
    public boolean remove(Deck c) {
        return course.remove(c);
    }

    /** Replaces the Deck at the specified position
     *  in this course with the specified Deck.
     * @param i index
     * @param c specified Deck
     * @return previous Deck at index
     */
    public Deck set(int i, Deck c) {
        return course.set(i, c);
    }

    /** Returns the number of elements in this list.
     * @return course size
     */
    public int size() {
        return course.size();
    }

    /** Move method changes the position of a
     *  Deck within a Course.
     *  @param indexStart initial index of Deck
     *  @param indexEnd final index of Deck
     */
    public void move(int indexStart, int indexEnd) {
        course.add(indexEnd, course.remove(indexStart));
    }
}