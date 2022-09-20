import java.util.ArrayList;

/** Deck class defines an ArrayList of Flashcard
 *  objects
 * @author RMizelle
 * @version V0.1
 */
public class Deck {
    private ArrayList<Flashcard> Deck;
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

    /** Accessor method for name */
    public String getName() {
        return name;
    }

    /** move method changes the position of a
     *  Flashcard within a Deck
     *  @param indexStart initial index of Flashcard
     *  @param indexEnd final index of Flashcard
     */
    public void move(int indexStart, int indexEnd) {
        Flashcard temp = Deck.remove(indexStart);
        Deck.add(indexEnd, temp);
    }
}