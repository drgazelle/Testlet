import java.util.ArrayList;

/** Course class defines an ArrayList
 *  of Sets
 *  @author RMizelle
 *  @since 9/16/2022
 *  @version V0.2
 */
public class Course {
    private ArrayList<Set> course = new ArrayList<Set>();

    /** accessor method for sets.
     * @return Set object at index i; null if out-of-bounds
     * @param i set index
     */
    public Set getSet(int i) {
        if (i >= course.size()) {
            return null;
        }
        return course.get(i);
    }

    public void removeSet() {

    }
}
