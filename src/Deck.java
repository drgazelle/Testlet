import java.util.ArrayList;

/** Deck class defines an ArrayList of Flashcard
 *  objects with a name and description.
 * @author RMizelle
 * @version V1.0
 */
public class Deck {
    private ArrayList<Flashcard> deck;
    private String name, description;

    /** 2-arg constructor instantiates Flashcard
     * ArrayList and defines both name and description.
     * @param name Deck name
     * @param description Deck description
     */
    public Deck(String name, String description) {
        deck = new ArrayList<Flashcard>();
        this.name = name;
        this.description = description;
    }

    /** 1-arg constructor instantiates Flashcard
     * ArrayList and defines name.
     * @param name Deck name
     */
    public Deck(String name) {
        deck = new ArrayList<Flashcard>();
        this.name = name;
        description = "";
    }

    /**
     * 0-arg construction instantiates Flashcard
     * ArrayList and sets name and description to empty.
     */
    public Deck() {
        deck = new ArrayList<Flashcard>();
        name = "";
        description = "";
    }

    //////////////////// Name Methods ////////////////////

    /** Modifies deck name */
    public void setName(String name) {
        this.name = name;
    }

    /** Accessor method for name */
    public String getName() {
        return name;
    }

    //////////////////// Description Methods ////////////////////

    /** Modifies deck description */
    public void setDescription(String description) {
        this.description = description;
    }
    /** Accessor method for description */
    public String getDescription() {
        return description;
    }

    //////////////////// Deck Methods ////////////////////

    /** imports user generated Quizlet decks
     * @param i "term" + "    " + "def" separated by "####"
     * @return true if successfully added new deck, false otherwise
     */
    public boolean importQuizlet(String i) {
        //splits flashcards
        String[] lines = i.split("####");
        try {
            //loops through lines splitting term and definitions
            for (String l : lines) {
                String[] parts = l.split("\t");
                deck.add(new Flashcard(parts[0], parts[1]));
            }
        }
        catch(Exception e) {
            System.out.println("ERROR: Couldn't Import Quizlet Set");
            return false;
        }
        return true;
    }

    /** Accessor method for Flashcard ArrayList
     * @return ArrayList of Flashcards
     */
    public ArrayList<Flashcard> getContent() {
        return deck;
    }

    /** Appends the specified Flashcard to the end of this deck. */
    public boolean add(Flashcard c) {
        return deck.add(c);
    }
    /** Inserts the specified Flashcard at the specified position in this deck.
     * @param i index
     * @param c Flashcard
     */
    public void add(int i, Flashcard c) {
        deck.add(i, c);
    }
    /** Removes all Flashcards from this deck. */
    public void clear() {
        deck.clear();
    }

    /** Returns true if this deck contains the specified Flashcard.
     *  @param c Flashcard comparable
     *  @return true if deck contains Flashcard, false otherwise
     */
    public boolean contains(Flashcard c) {
        return deck.contains(c);
    }

    /** Returns the Flashcard at the specified position in this deck.
     *  @param i index
     *  @return Flashcard at index
     */
    public Flashcard get(int i) {
        return deck.get(i);
    }
    /** Returns the ArrayList of Flashcards.
     * @return Flashcard ArrayList
     */
    public ArrayList<Flashcard> get() {
        return deck;
    }

    /** Returns the index of the first occurrence of the
     *  specified Flashcard in this deck, or -1 if this deck
     *  does not contain the Flashcard.
     *
     *  @param c Flashcard comparable
     *  @return index of Flashcard, -1 if non-existent
     */
    public int indexOf(Flashcard c) {
        return deck.indexOf(c);
    }

    /** Removes the Flashcard at the specified position in this deck.
     * @param i index
     * @return previous Flashcard at index i
     */
    public Flashcard remove(int i) {
        return deck.remove(i);
    }

    /** Removes the first occurrence of the specified
     *  Flashcard from this deck, if it is present.
     * @param c Flashcard to be removed
     * @return true if removed, false otherwise
     */
    public boolean remove(Flashcard c) {
        return deck.remove(c);
    }

    /** Replaces the Flashcard at the specified position
     *  in this deck with the specified Flashcard.
     * @param i index
     * @param c specified Flashcard
     * @return previous Flashcard at index
     */
    public Flashcard set(int i, Flashcard c) {
        return deck.set(i, c);
    }

    /** Returns the number of elements in this list.
     * @return deck size
     */
    public int size() {
        return deck.size();
    }

    /** move method changes the position of a
     *  Flashcard within a Deck
     *  @param indexStart initial index of Flashcard
     *  @param indexEnd final index of Flashcard
     */
    public void move(int indexStart, int indexEnd) {
        deck.add(indexEnd, deck.remove(indexStart));
    }

    /* toString method for JTree */
    @Override
    public String toString() {
        return name;
    }
}
