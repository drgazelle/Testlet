import java.util.ArrayList;

/** Deck class defines an ArrayList of Flashcard
 *  objects
 * @author RMizelle
 * @since 9/15/2022
 * @version V0.1
 */
public class Deck {
    private ArrayList<Flashcard> Deck;

    //private String[] tags;
    private String name;

    /** 1-arg constructor instantiates Flashcard
     * ArrayList and defines name.
     * @param name Deck name
     */
    public Deck(String name) {
        Deck = new ArrayList<Flashcard>();
        this.name = name;
    }

    /** setName method modifies name */
    public void setName(String name) {
        this.name = name;
    }
}