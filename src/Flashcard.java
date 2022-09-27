/** Flashcard Class defines an object pertaining
 *  a String definition and term.
 * @author RMizelle
 * @version V1.0
 */
public class Flashcard {
    private String term, def;

    /**
     * 2-arg constructor that instantiates the
     * term and definition.
     * @param term defining phrase
     * @param def definition of term
     */
    public Flashcard(String term, String def) {
        this.term = term;
        this.def = def;
    }

    /** 0-arg constructor that instantiates
     *  the term and definition as empty
     */
    public Flashcard() {
        term = "";
        def = "";
    }

    /** accessor method for term. */
    public String getTerm() {
        return term;
    }

    /** accessor method for def. */
    public String getDef() {
        return def;
    }

    /** setter method for term. */
    public void setTerm(String term) {
        this.term = term;
    }

    /** setter method for definition. */
    public void setDef(String def) {
        this.def = def;
    }

    /** swap method exchanges the term and definition. */
    public void swap() {
        String temp = term;
        term = def;
        def = term;
    }
}
