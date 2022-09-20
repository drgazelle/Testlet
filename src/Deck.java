import java.util.ArrayList;

/** Deck class defines an ArrayList of Flashcard
 *  objects
 * @author RMizelle
 * @version V0.1
 */
public class Deck {
    private ArrayList<Flashcard> deck;
    private String name;

    /** 1-arg constructor instantiates Flashcard
     * ArrayList and defines name.
     * @param name Deck name
     */
    public Deck(String name) {
        deck = new ArrayList<Flashcard>();
        this.name = name;
    }

    /**
     * 0-arg construction instantiates Flashcard
     * ArrayList and sets name to empty
     */
    public Deck() {
        deck = new ArrayList<Flashcard>();
        name = "";
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
        Flashcard temp = deck.remove(indexStart);
        deck.add(indexEnd, temp);
    }
}