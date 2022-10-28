import javax.swing.*;

/** Flashcard Class defines an object pertaining
 *  a String definition and term.
 * @author RMizelle
 * @version V1.0
 */
public class Flashcard {
    private String term, def;

    //ava added here (using these comments just to keep track so I can delete my work if it is incorrect)
    private int x, y; //position in pixel space that the asteroid will be drawn
    ImageIcon picture1 = new ImageIcon("resources/images/flashcardAnimation/flashcard1.png");
    ImageIcon picture2 = new ImageIcon("resources/images/flashcardAnimation/flashcard2.png");
    ImageIcon picture3 = new ImageIcon("resources/images/flashcardAnimation/flashcard3.png");
    ImageIcon picture4 = new ImageIcon("resources/images/flashcardAnimation/flashcard4.png");
    ImageIcon picture5 = new ImageIcon("resources/images/flashcardAnimation/flashcard5.png");
    ImageIcon picture6 = new ImageIcon("resources/images/flashcardAnimation/flashcard6.png");
    ImageIcon picture7 = new ImageIcon("resources/images/flashcardAnimation/flashcard7.png");
    ImageIcon picture8 = new ImageIcon("resources/images/flashcardAnimation/flashcard8.png");
    ImageIcon picture9 = new ImageIcon("resources/images/flashcardAnimation/flashcard9.png");
    ImageIcon picture10 = new ImageIcon("resources/images/flashcardAnimation/flashcard10.png");
    ImageIcon picture11 = new ImageIcon("resources/images/flashcardAnimation/flashcard11.png");
    ImageIcon picture12 = new ImageIcon("resources/images/flashcardAnimation/flashcard12.png");
    ImageIcon picture13 = new ImageIcon("resources/images/flashcardAnimation/flashcard13.png");
    ImageIcon picture14 = new ImageIcon("resources/images/flashcardAnimation/flashcard14.png");
    ImageIcon picture15 = new ImageIcon("resources/images/flashcardAnimation/flashcard15.png");
    ImageIcon picture16 = new ImageIcon("resources/images/flashcardAnimation/flashcard16.png");
    ImageIcon picture17 = new ImageIcon("resources/images/flashcardAnimation/flashcard17.png");
    ImageIcon picture18 = new ImageIcon("resources/images/flashcardAnimation/flashcard18.png");
    ImageIcon picture19 = new ImageIcon("resources/images/flashcardAnimation/flashcard19.png");
    ImageIcon picture20 = new ImageIcon("resources/images/flashcardAnimation/flashcard20.png");
    ImageIcon picture21 = new ImageIcon("resources/images/flashcardAnimation/flashcard21.png");
    ImageIcon picture22 = new ImageIcon("resources/images/flashcardAnimation/flashcard22.png");
    ImageIcon picture23 = new ImageIcon("resources/images/flashcardAnimation/flashcard23.png");
    ImageIcon picture24 = new ImageIcon("resources/images/flashcardAnimation/flashcard24.png");
    ImageIcon picture25 = new ImageIcon("resources/images/flashcardAnimation/flashcard25.png");
    ImageIcon picture26 = new ImageIcon("resources/images/flashcardAnimation/flashcard26.png");
    ImageIcon picture27 = new ImageIcon("resources/images/flashcardAnimation/flashcard27.png");
    ImageIcon picture28 = new ImageIcon("resources/images/flashcardAnimation/flashcard28.png");
    ImageIcon picture29 = new ImageIcon("resources/images/flashcardAnimation/flashcard29.png");
    ImageIcon picture30 = new ImageIcon("resources/images/flashcardAnimation/flashcard30.png");
    ImageIcon picture31 = new ImageIcon("resources/images/flashcardAnimation/flashcard31.png");
    ImageIcon picture32 = new ImageIcon("resources/images/flashcardAnimation/flashcard32.png");
    private ImageIcon[] pictures = {picture1, picture2, picture3, picture4, picture5, picture6, picture7, picture8, picture9, picture10, picture11, picture12, picture13, picture14, picture15, picture16, picture17, picture18, picture19, picture20, picture21, picture22, picture23, picture24, picture25, picture26, picture27, picture28, picture29, picture30, picture31, picture32, picture31, picture30, picture29, picture28, picture27, picture26, picture25, picture24, picture23, picture22, picture21, picture20, picture19, picture18, picture17, picture16, picture15, picture14, picture13, picture12, picture11, picture10, picture9, picture8, picture7, picture6, picture5, picture4, picture3, picture2, picture1}; //array of animation frames
    private int animationIndex; //the current index of pictures that we will display
    private int animationDelay; //the higher the delay, the slower the animation will be
    private int numFrames; //the total number of frames that have passed, used to control animationDelay

    /**
     * 2-arg constructor that instantiates the
     * term and definition.
     * @param term defining phrase
     * @param def definition of term
     */
    public Flashcard(String term, String def) {
        this.term = term;
        this.def = def;

        //ava added here
        x = 330;
        y = 100;
        animationIndex = 0;
        animationDelay = 6;
        numFrames = 0;
    }

    /** 0-arg constructor that instantiates
     *  the term and definition as empty
     */
    public Flashcard() {
        term = "";
        def = "";

        //ava added here
        x = 400;
        y = 100;
        animationIndex = 0;
        animationDelay = 400;
        numFrames = 0;
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

    /* toString method for JTree */
    @Override
    public String toString() {
        return def + ": " + term;
    }

    //ava added here
    /**
     * Accessor method for which picture should be displayed to the screen
     * based on animation delay and array of ImageIcons
     * @return ImageIcon that should be displayed to screen
     */
    public ImageIcon getPicture(boolean increaseFrames)
    {
        if (animationIndex < 0 || animationIndex >= pictures.length)
            return null;
        if(increaseFrames == true)
        {
            ImageIcon temp = pictures[animationIndex]; //the current picture we will show
            if (numFrames == Integer.MAX_VALUE)
                numFrames = 0;
            numFrames++; //advance the number of frames
            if (numFrames % animationDelay == 0)
                animationIndex = (animationIndex + 1) % pictures.length;
            return temp;
        }
        //increaseFrames == false
        else {
            ImageIcon temp = pictures[0];
            animationIndex = 0;
            if (numFrames % animationDelay == 0)
                animationIndex = (animationIndex + 1) % pictures.length;
            return temp;
        }
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean continueFlipAnimation()
    {
        if(animationIndex  >= pictures.length-3)
        return false;
        return true;
    }
}
