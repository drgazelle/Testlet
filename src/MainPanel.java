import javax.swing.*;
//import java.util.*;
import java.awt.*;
//import java.awt.Rectangle.*;
//import java.awt.event.*;
import java.awt.Shape;
import java.awt.Color;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
//import java.awt.Container;
import java.util.ArrayList;

/** MainPanel class defines the containing window
 *  for the application
 *  @author ACraig, RMizelle
 *  @version V0.9
 */
public class MainPanel extends JPanel implements MouseListener, MouseMotionListener
{
    //screen size
    private static final int xSIZE = 960;
    private static final int ySIZE = 540;

    //the left sidebar's length is 200 (x)
    private static final int leftBarSize = 200;

    //the top bar's height is 50 (y)
    private static final int topBarHeight = 50;
    private boolean homeScreenDisplay = false;
    private boolean learnDisplay = false;
    private boolean testDisplay = false;
    private boolean matchingDisplay = false;
    private ImageIcon testletCharacter = new ImageIcon("resources/images/Testlet_Character.png");
    private ImageIcon testletCharacterSpeechBubble = new ImageIcon("resources/images/TestletHomeSpeechBubble.png");

    //for gravity and asteroids
    private boolean gravityStartDisplay = false;
    private boolean gravityDisplay = false;
    private static final int asteroidTextSize = 25;
    private static int DELAY = 1;
    private static Timer t;
    private static int frameGravity;
    private static int asteroidX, asteroidY;
    private ImageIcon[] asteroidImages = {new ImageIcon("resources/images/asteroid1.png")};
    private Asteroid asteroid1;

    //for flashcards
    private int termOddDefEven = 1; //keeps track of which side of the flash card to show
    private boolean flipCard = false;
    private boolean flashcardsDisplay = false;
    private static final int flashCardTextSize = 25;
    private static int frameFlashCard;
    private int flashcardShownInt = 0;
    private Flashcard flashcard1;

    //for learn
    private ImageIcon incorrectAnswer = new ImageIcon("resources/images/incorrectLearnA1.png");
    private ImageIcon correctAnswer = new ImageIcon("resources/images/correctLearnA1.png");
    private ImageIcon deckCompleteCongrats = new ImageIcon("resources/images/deckCompleteCongrats.png");
    private static MyQueue<Flashcard> learnQueue = new MyQueue();
    private int learnCardNumber = 0;
    private int randomAnswers;
    private int randomWrongAnswer1;
    private int randomWrongAnswer2;
    private int randomWrongAnswer3;
    private int randomWrongAnswer4;
    private String A;
    private String B;
    private String C;
    private String D;
    private boolean showIncorrect1 = false;
    private boolean showIncorrect2 = false;
    private boolean showIncorrect3 = false;
    private boolean showIncorrect4 = false;
    private boolean showCorrect1 = false;
    private boolean showCorrect2 = false;
    private boolean showCorrect3 = false;
    private boolean showCorrect4 = false;
    private boolean nullDeckDuringLearn = false;
    //for test
    private int currentQuestion = 1;
    private static ArrayList<Flashcard> testQuestions = new ArrayList<>();
    private ImageIcon answerBubble1 = new ImageIcon("resources/images/answerBubble1.png");
    private ImageIcon answerBubble2 = new ImageIcon("resources/images/answerBubble2.png");
    private boolean question1Answered = false;
    private boolean question2Answered = false;
    private boolean question3Answered = false;
    private boolean question4Answered = false;
    private boolean question5Answered = false;
    private boolean question6Answered = false;
    private boolean question7Answered = false;
    private boolean question8Answered = false;
    private boolean question9Answered = false;
    private boolean question10Answered = false;
    private boolean question11Answered = false;
    private boolean question12Answered = false;
    private boolean question13Answered = false;
    private boolean question14Answered = false;
    private boolean question15Answered = false;
    private boolean question16Answered = false;
    private boolean question17Answered = false;
    private boolean question18Answered = false;
    private boolean question19Answered = false;
    private boolean question20Answered = false;
    private int currentTimer = -1;

    //images
    //private ImageIcon startScreen  = new ImageIcon("images/backgrounds/startScreen.png");

    //array of buttons
    private Button[] buttons = new Button[23];

    //each int represents a different button.
    private static final int HOMEBUTTON = 0;
    private static final int EXPORTBUTTON = 1;
    private static final int SETTINGSBUTTON = 2;
    private static final int NEWDECKBUTTON = 3;
    private static final int FLASHCARDS = 4;
    private static final int LEARN = 5;
    private static final int TEST = 6;
    private static final int GRAVITY = 7;
    private static final int MATCHING = 8;
    private static final int GRAVITYSTARTGAME = 9;
    private static final int FLASHCARDFLIP = 10;
    private static final int PREVIOUSCARD = 11;
    private static final int NEXTCARD = 12;
    private static final int LEARNA = 13;
    private static final int LEARNB = 14;
    private static final int LEARNC = 15;
    private static final int LEARND = 16;
    private static final int TRYAGAIN = 17;
    private static final int NEXTTESTQUESTION = 18;
    private static final int PREVIOUSTESTQUESTION = 19;
    private static final int TRUEBUTTONFORTEST = 20;
    private static final int FALSEBUTTONFORTEST = 21;
    private static final int SUBMITTEST = 22;
    private boolean deckIsComplete = false;
    private boolean changeAnswers = false;
    private boolean trueFalse1;
    private boolean trueFalse2;
    private boolean trueFalse3;
    private boolean trueFalse4;
    private boolean trueFalse5;

    private int trueOrFalse1 = (int)(Math.random()*2);
    private int trueOrFalse2 = (int)(Math.random()*2);
    private int trueOrFalse3 = (int)(Math.random()*2);
    private int trueOrFalse4 = (int)(Math.random()*2);
    private int trueOrFalse5 = (int)(Math.random()*2);

    private int trueFalseWrongAnswer1;
    private int trueFalseWrongAnswer2;
    private int trueFalseWrongAnswer3;
    private int trueFalseWrongAnswer4;
    private int trueFalseWrongAnswer5;

    private String currentDeck = "";

    //screen modes
    private static final int HOMESCREEN = 0;

    //keeps track of what screen mode should be shown on the screen
    private static int screenMode;

    private static final int SIZE = 500;

    //keeps track of how many times the setting button has been clicked
    //0 means it has not been clicked or it is clicked again to exit the settings menu. Do not highlight the button
    //1 means it has been clicked. Keep the button highlighted
    int numberOfSettingsClicks = 0;

    //mouse location
    protected static int mouseX;
    protected static int mouseY;

    //database variables
    private Database data;
    private static Deck deck = null;

    /** 0-arg constructor */
    public MainPanel() {
        //creates database
        data = new Database();

        addMouseListener(this);
        addMouseMotionListener(this);
        mouseX = SIZE/2;
        mouseY = SIZE/2;

        asteroidX = 300;
        asteroidY = -50;

        asteroid1 = new Asteroid(new Flashcard("hello", "5 letter word for hi"), asteroidX, asteroidY, asteroidImages, 400);
        flashcard1 = new Flashcard("test1", "test2");

        t = new Timer(DELAY, new Listener());
        t.start();
        frameGravity = 0;
        frameFlashCard = 0;

        //add buttons here
        //example below:
        //                          x    y    width   height
        //Shape r1 = new Rectangle(350, 300,   270,    120);
        //ImageIcon startButton1 = new ImageIcon("images/buttons/start1.png");
        //ImageIcon startButton2 = new ImageIcon("images/buttons/start2.png");
        //buttons[STARTBUTTON] = new Button(r1, "start", startButton1, startButton2);

        //creates the shape that the button will be
        Shape r1 = new Rectangle(0, 0, leftBarSize, topBarHeight);
        //this image is what the button usually looks like/is not over the button
        ImageIcon homeButton1 = new ImageIcon("resources/images/home1.png");
        //this image is what the button will look like when the mouse is hovering over it
        ImageIcon homeButton2 = new ImageIcon("resources/images/home1.png"); //same image as homeButton1
        //initializes the button with shape, title, and its images
        buttons[HOMEBUTTON] = new Button(r1, "home", homeButton1, homeButton2);

        Shape r2 = new Ellipse2D.Float(xSIZE - (3*topBarHeight) - 30, 4, topBarHeight-4, topBarHeight-5-4); //circle
        ImageIcon exportButton1 = new ImageIcon("resources/images/export1.png");
        ImageIcon exportButton2 = new ImageIcon("resources/images/export2.png");
        buttons[EXPORTBUTTON] = new Button(r2, "export", exportButton1, exportButton2);

        Shape r3 = new Ellipse2D.Float(xSIZE - (2*topBarHeight) - 20, 4, topBarHeight-4, topBarHeight-5-4); //circle
        ImageIcon settingsButton1 = new ImageIcon("resources/images/settings1.png");
        ImageIcon settingsButton2 = new ImageIcon("resources/images/settings2.png");
        buttons[SETTINGSBUTTON] = new Button(r3, "settings", settingsButton1, settingsButton2);

        Shape r4 = new Ellipse2D.Float(xSIZE - (topBarHeight) - 10, 4, topBarHeight-4, topBarHeight-5-4); //circle
        ImageIcon newDeckButton1 = new ImageIcon("resources/images/newDeck1.png");
        ImageIcon newDeckButton2 = new ImageIcon("resources/images/newDeck2.png"); //same image as homeButton1
        buttons[NEWDECKBUTTON] = new Button(r4, "newDeck", newDeckButton1, newDeckButton2);

        Shape r8 = new Rectangle(0, topBarHeight+3, leftBarSize, 91);
        ImageIcon flashCardButton1 = new ImageIcon("resources/images/flashcards1.png");
        ImageIcon flashCardButton2 = new ImageIcon("resources/images/flashcards2.png");
        buttons[FLASHCARDS] = new Button(r8, "flashcards", flashCardButton1, flashCardButton2);

        Shape r9 = new Rectangle(0, topBarHeight +3 + 91, leftBarSize, 91); //
        ImageIcon learnButton1 = new ImageIcon("resources/images/learn1.png");
        ImageIcon learnButton2 = new ImageIcon("resources/images/learn2.png");
        buttons[LEARN] = new Button(r9, "learn", learnButton1, learnButton2);

        Shape r10 = new Rectangle(0, topBarHeight +3 + 91 + 91, leftBarSize, 91);
        ImageIcon testButton1 = new ImageIcon("resources/images/test1.png");
        ImageIcon testButton2 = new ImageIcon("resources/images/test2.png");
        buttons[TEST] = new Button(r10, "test", testButton1, testButton2);

        Shape r11 = new Rectangle(0, topBarHeight +3 + 91 + 91 + 91, leftBarSize, 91);
        ImageIcon gravityButton1 = new ImageIcon("resources/images/gravity1.png");
        ImageIcon gravityButton2 = new ImageIcon("resources/images/gravity2.png");
        buttons[GRAVITY] = new Button(r11, "gravity", gravityButton1, gravityButton2);

        Shape r12 = new Rectangle(0, topBarHeight + 3+91 + 91 + 91 + 91, leftBarSize, 90);
        ImageIcon matchingButton1 = new ImageIcon("resources/images/matching1.png");
        ImageIcon matchingButton2 = new ImageIcon("resources/images/matching2.png");
        buttons[MATCHING] = new Button(r12, "matching", matchingButton1, matchingButton2);

        Shape r13 = new Rectangle(xSIZE/2, ySIZE/2, 200, 100);
        ImageIcon gravityStartGameButton1 = new ImageIcon("resources/images/gravityStart1.png");
        ImageIcon gravityStartGameButton2 = new ImageIcon("resources/images/gravityStart2.png");
        buttons[GRAVITYSTARTGAME] = new Button(r13, "gravityStartGame", gravityStartGameButton1, gravityStartGameButton2);

        Shape r14 = new Rectangle(flashcard1.getX(), flashcard1.getY(), flashCardTextSize*20, flashCardTextSize*12);
        ImageIcon flashCardFlipButton1 = new ImageIcon("resources/images/gravityStart1.png");
        ImageIcon flashCardFlipButton2 = new ImageIcon("resources/images/gravityStart2.png");
        buttons[FLASHCARDFLIP] = new Button(r14, "flashCardFlip", flashCardFlipButton1, flashCardFlipButton2);

        Shape r15 = new Rectangle(310, 420, 101, 53);
        ImageIcon previousCardButton1 = new ImageIcon("resources/images/prevflashcard1.png");
        ImageIcon previousCardButton2 = new ImageIcon("resources/images/prevflashcard2.png");
        buttons[PREVIOUSCARD] = new Button(r15, "previousCard", previousCardButton1, previousCardButton2);

        Shape r16 = new Rectangle(750, 420, 101, 53);
        ImageIcon nextCardButton1 = new ImageIcon("resources/images/nextflashcard1.png");
        ImageIcon nextCardButton2 = new ImageIcon("resources/images/nextflashcard2.png");
        buttons[NEXTCARD] = new Button(r16, "nextCard", nextCardButton1, nextCardButton2);

        Shape r17 = new Rectangle(205, 330, 365, 80);
        ImageIcon learnA1 = new ImageIcon("resources/images/learnA1.png");
        ImageIcon learnA2 = new ImageIcon("resources/images/learnA2.png"); //change image
        buttons[LEARNA] = new Button(r17, "learnA", learnA1, learnA2);

        Shape r18 = new Rectangle(575, 330, 365, 80);
        ImageIcon learnB1 = new ImageIcon("resources/images/learnA1.png"); //change images
        ImageIcon learnB2 = new ImageIcon("resources/images/learnA2.png");
        buttons[LEARNB] = new Button(r18, "learnB", learnB1, learnB2);

        Shape r19 = new Rectangle(205, 420, 365, 80);
        ImageIcon learnC1 = new ImageIcon("resources/images/learnA1.png"); //change images
        ImageIcon learnC2 = new ImageIcon("resources/images/learnA2.png");
        buttons[LEARNC] = new Button(r19, "learnC", learnC1, learnC2);

        Shape r20 = new Rectangle(575, 420, 365, 80);
        ImageIcon learnD1 = new ImageIcon("resources/images/learnA1.png"); //change images
        ImageIcon learnD2 = new ImageIcon("resources/images/learnA2.png");
        buttons[LEARND] = new Button(r20, "learnD", learnD1, learnD2);

        Shape r21 = new Rectangle(350, 280, 365, 80);
        ImageIcon tryAgain1 = new ImageIcon("resources/images/tryAgain.png"); //change images
        ImageIcon tryAgain2 = new ImageIcon("resources/images/tryAgain.png");
        buttons[TRYAGAIN] = new Button(r21, "tryAgain", tryAgain1, tryAgain2);

        Shape r22 = new Rectangle(210, 445, 101, 53);
        ImageIcon previousTestQuestion1 = new ImageIcon("resources/images/prevflashcard1.png");
        ImageIcon previousTestQuestion2 = new ImageIcon("resources/images/prevflashcard2.png");
        buttons[PREVIOUSTESTQUESTION] = new Button(r22, "previousTestQuestion", previousTestQuestion1, previousTestQuestion2);

        Shape r23 = new Rectangle(840, 445, 101, 53);
        ImageIcon nextTestQuestion1 = new ImageIcon("resources/images/nextflashcard1.png");
        ImageIcon nextTestQuestion2 = new ImageIcon("resources/images/nextflashcard2.png");
        buttons[NEXTTESTQUESTION] = new Button(r23, "nextTestQuestion", nextTestQuestion1, nextTestQuestion2);

        Shape r24 = new Rectangle(350, 320, 185, 65);
        ImageIcon trueButton1 = new ImageIcon("resources/images/truefalseButton1.png");
        ImageIcon trueButton2 = new ImageIcon("resources/images/truefalseButton2.png");
        buttons[TRUEBUTTONFORTEST] = new Button(r24, "trueButton", trueButton1, trueButton2);

        Shape r25 = new Rectangle(550, 320, 185, 65);
        ImageIcon falseButton1 = new ImageIcon("resources/images/truefalseButton1.png");
        ImageIcon falseButton2 = new ImageIcon("resources/images/truefalseButton2.png");
        buttons[FALSEBUTTONFORTEST] = new Button(r25, "falseButton", falseButton1, falseButton2);

        Shape r26 = new Rectangle(840, 445, 101, 53);
        ImageIcon submitButton1 = new ImageIcon("resources/images/submit1.png");
        ImageIcon submitButton2 = new ImageIcon("resources/images/nextflashcard2.png");
        buttons[SUBMITTEST] = new Button(r26, "submitTest", submitButton1, submitButton2);

        //sets the screen mode
        screenMode = HOMESCREEN;
    }

    /**
     * Paints the screen depending on what screenMode is chosen
     * @param g
     */
    public void showBoard(Graphics g) {
        if (screenMode == HOMESCREEN) {

            //background image
            //g.drawImage(startScreen.getImage(), 0,0, xSIZE, ySIZE, null);
            g.setColor(Color.WHITE);
            g.fillRect(0,0, xSIZE, ySIZE);

            //left sidebar background
            g.setColor(new Color(220,220,220));
            g.fillRect(0,0,leftBarSize, ySIZE);

            buttons[FLASHCARDS].drawButton(g); //course names will be displayed depending on the courses of the user
            buttons[FLASHCARDS].setEnabled(true);
            buttons[FLASHCARDS].setVisible(true);
            buttons[LEARN].drawButton(g);
            buttons[LEARN].setEnabled(true);
            buttons[LEARN].setVisible(true);
            buttons[TEST].drawButton(g);
            buttons[TEST].setEnabled(true);
            buttons[TEST].setVisible(true);
            buttons[GRAVITY].drawButton(g);
            buttons[GRAVITY].setEnabled(true);
            buttons[GRAVITY].setVisible(true);
            buttons[MATCHING].drawButton(g);
            buttons[MATCHING].setEnabled(true);
            buttons[MATCHING].setVisible(true);

            if(homeScreenDisplay == true)
            {
                g.drawImage(testletCharacter.getImage(), leftBarSize+30, topBarHeight+30, 313, 410, null);
                g.drawImage(testletCharacterSpeechBubble.getImage(), leftBarSize+30+230, topBarHeight+20, 300, 130, null);
            }

            if(gravityStartDisplay == true)
            {
                buttons[GRAVITYSTARTGAME].drawButton(g);
                buttons[GRAVITYSTARTGAME].setEnabled(true);
                buttons[GRAVITYSTARTGAME].setVisible(true);

                buttons[FLASHCARDFLIP].setEnabled(false);
                buttons[PREVIOUSCARD].setEnabled(false);
                buttons[NEXTCARD].setEnabled(false);
            }

            if(gravityDisplay == true)
            {
                g.drawImage(asteroid1.getPicture().getImage(), asteroid1.getX(), asteroid1.getY(), asteroidTextSize * 5, asteroidTextSize * 5, null);
            }

            if(flashcardsDisplay == true)
            {
                //deactivate gravity buttons
                buttons[GRAVITYSTARTGAME].setEnabled(false);
                buttons[GRAVITYSTARTGAME].setVisible(false);
                //deactivate learn buttons
                buttons[LEARNA].setVisible(false);
                buttons[LEARNA].setEnabled(false);
                buttons[LEARNB].setVisible(false);
                buttons[LEARNB].setEnabled(false);
                buttons[LEARNC].setVisible(false);
                buttons[LEARNC].setEnabled(false);
                buttons[LEARND].setVisible(false);
                buttons[LEARND].setEnabled(false);
                //deactivate test buttons
                buttons[PREVIOUSTESTQUESTION].setVisible(false);
                buttons[PREVIOUSTESTQUESTION].setEnabled(false);
                buttons[NEXTTESTQUESTION].setVisible(false);
                buttons[NEXTTESTQUESTION].setEnabled(false);
                //activate necessary flashcard buttons
                buttons[FLASHCARDFLIP].drawButton(g);
                buttons[FLASHCARDFLIP].setEnabled(true);
                buttons[FLASHCARDFLIP].setVisible(false);
                buttons[PREVIOUSCARD].drawButton(g);
                buttons[PREVIOUSCARD].setEnabled(true);
                buttons[PREVIOUSCARD].setVisible(true);
                buttons[NEXTCARD].drawButton(g);
                buttons[NEXTCARD].setEnabled(true);
                buttons[NEXTCARD].setVisible(true);

                if(deck != null) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("SansSerif", Font.BOLD, 20));
                    if (deck.size() < 20)
                        g.drawString("" + (flashcardShownInt+1) + "/" + deck.size(), 540, 75);
                }

                if(flipCard == true)
                {
                    g.drawImage(flashcard1.getPicture(true).getImage(), flashcard1.getX(), flashcard1.getY(), flashCardTextSize*20, flashCardTextSize*12, null); //400x300
                }
                else //if(flipCard == false)
                {
                    g.drawImage(flashcard1.getPicture(false).getImage(), flashcard1.getX(), flashcard1.getY(), flashCardTextSize * 20, flashCardTextSize * 12, null); //400x300
                    if(deck == null)
                    {
                        g.setColor(Color.RED);
                        g.setFont(new Font("Helvetica", Font.PLAIN, 30));
                        g.drawString("Select a deck!", 490, 260);
                    }
                    if(termOddDefEven % 2 == 0) //termOddDefEven is even, show the definition
                     {
                         g.setColor(Color.BLACK);
                         g.setFont(new Font("Helvetica", Font.PLAIN, 30));
                         g.drawString("Definition", 350, 150);
                         if(deck != null)
                         {
                             int yValue = 190; //increase by 30 each time
                             ArrayList<String> lines = addLinesToString(32, deck.get(flashcardShownInt).getDef());
                             for(int i = 0; i<lines.size(); i++)
                             {
                                 g.drawString(lines.get(i), 350, yValue);
                                 //g.drawString("" + lines.size(), 215, yValue-10); //the size is only 1 ??? what
                                 yValue += 33;
                             }
                         }
                     }
                     else //termOddDefEven is odd, show term
                     {
                         g.setColor(Color.BLACK);
                         g.setFont(new Font("Helvetica", Font.PLAIN, 30));
                         g.drawString("Term", 350, 150);
                         if(deck != null) {
                             int yValue1 = 260; //increase by 30 each time
                             ArrayList<String> lines = addLinesToString(32, deck.get(flashcardShownInt).getTerm());
                             for(int i = 0; i<lines.size(); i++)
                             {
                                 g.drawString(lines.get(i), 350, yValue1);
                                 yValue1 += 33;
                             }
                         }
                     }
                }
            }

            if(testDisplay == true)
            {
                //deactivate gravity buttons
                buttons[GRAVITYSTARTGAME].setEnabled(false);
                buttons[GRAVITYSTARTGAME].setVisible(false);
                //deactivate flashcard buttons
                buttons[FLASHCARDFLIP].setEnabled(false);
                buttons[FLASHCARDFLIP].setVisible(false);
                buttons[PREVIOUSCARD].setVisible(false);
                buttons[PREVIOUSCARD].setEnabled(true);
                buttons[NEXTCARD].setVisible(false);
                buttons[NEXTCARD].setEnabled(false);
                //deactivate learn buttons
                buttons[LEARNA].setVisible(false);
                buttons[LEARNA].setEnabled(false);
                buttons[LEARNB].setVisible(false);
                buttons[LEARNB].setEnabled(false);
                buttons[LEARNC].setVisible(false);
                buttons[LEARNC].setEnabled(false);
                buttons[LEARND].setVisible(false);
                buttons[LEARND].setEnabled(false);
                //activate test buttons
               // buttons[NEXTTESTQUESTION].drawButton(g);
               // buttons[NEXTTESTQUESTION].setEnabled(true);
                buttons[PREVIOUSTESTQUESTION].drawButton(g);
                buttons[PREVIOUSTESTQUESTION].setEnabled(true);


                if(deck == null)
                {
                    g.setColor(Color.RED);
                    g.setFont(new Font("Helvetica", Font.PLAIN, 30));
                    g.drawString("Select a deck!", 490, 260);
                }
                if(deck != null) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("SansSerif", Font.BOLD, 20));
                    if (deck.size() < 20)
                        g.drawString("" + currentQuestion + "/" + deck.size(), 540, 75);
                    else
                        g.drawString("" + currentQuestion + "/20", 540, 75);
                    g.setFont(new Font("Helvetica", Font.BOLD, 30));
                }

                if(currentQuestion < testQuestions.size()) //this part doesn't work?
                {
                    buttons[NEXTTESTQUESTION].drawButton(g);
                    buttons[NEXTTESTQUESTION].setEnabled(true);
                    buttons[SUBMITTEST].setEnabled(false);
                    buttons[SUBMITTEST].setVisible(false);
                }
                else
                {
                    buttons[NEXTTESTQUESTION].setEnabled(false);
                    buttons[NEXTTESTQUESTION].setVisible(false);
                    buttons[SUBMITTEST].drawButton(g);
                    buttons[SUBMITTEST].setEnabled(true);
                }

                //NEED TO DRAW STRINGS FOR OTHER ANSWER BUTTONS, HAVE ONLY DONE IT FOR QUESTION 1
                if(question1Answered == true && deck != null && deck.size()>= 1) {
                    g.drawImage(answerBubble2.getImage(), leftBarSize + 10, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("1", leftBarSize + 20, topBarHeight + 55);
                }
                else if(deck != null && deck.size()>= 1) {
                    g.drawImage(answerBubble1.getImage(), leftBarSize + 10, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("1", leftBarSize + 20, topBarHeight + 55);
                }

                if(question2Answered == true && deck != null && deck.size()>= 2) {
                    g.drawImage(answerBubble2.getImage(), leftBarSize + 45, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("2", leftBarSize + 56, topBarHeight + 55);
                }
                else if(deck != null && deck.size()>= 2) {
                    g.drawImage(answerBubble1.getImage(), leftBarSize + 45, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("2", leftBarSize + 56, topBarHeight + 55);
                }

                if(question3Answered == true && deck != null && deck.size()>= 3) {
                    g.drawImage(answerBubble2.getImage(), leftBarSize + 80, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("3", leftBarSize + 91, topBarHeight + 55);
                }
                else if(deck != null && deck.size()>= 3) {
                    g.drawImage(answerBubble1.getImage(), leftBarSize + 80, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("3", leftBarSize + 91, topBarHeight + 55);
                }

                if(question4Answered == true && deck != null && deck.size()>= 4) {
                    g.drawImage(answerBubble2.getImage(), leftBarSize + 115, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("4", leftBarSize + 126, topBarHeight + 55);
                }
                else if(deck != null && deck.size()>= 4) {
                    g.drawImage(answerBubble1.getImage(), leftBarSize + 115, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("4", leftBarSize + 126, topBarHeight + 55);
                }

                if(question5Answered == true && deck != null && deck.size()>= 5) {
                    g.drawImage(answerBubble2.getImage(), leftBarSize + 150, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("5", leftBarSize + 161, topBarHeight + 55);
                }
                else if(deck != null && deck.size()>= 5) {
                    g.drawImage(answerBubble1.getImage(), leftBarSize + 150, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("5", leftBarSize + 161, topBarHeight + 55);
                }

                if(question6Answered == true && deck != null && deck.size()>= 6) {
                    g.drawImage(answerBubble2.getImage(), leftBarSize + 185, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("6", leftBarSize + 196, topBarHeight + 55);
                }
                else if(deck != null && deck.size()>= 6) {
                    g.drawImage(answerBubble1.getImage(), leftBarSize + 185, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("6", leftBarSize + 196, topBarHeight + 55);
                }

                if(question7Answered == true && deck != null && deck.size()>= 7) {
                    g.drawImage(answerBubble2.getImage(), leftBarSize + 220, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("7", leftBarSize + 231, topBarHeight + 55);
                }
                else if(deck != null && deck.size()>= 7) {
                    g.drawImage(answerBubble1.getImage(), leftBarSize + 220, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("7", leftBarSize + 231, topBarHeight + 55);
                }

                if(question8Answered == true && deck != null && deck.size()>= 8) {
                    g.drawImage(answerBubble2.getImage(), leftBarSize + 255, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("8", leftBarSize + 266, topBarHeight + 55);
                }
                else if(deck != null && deck.size()>= 8) {
                    g.drawImage(answerBubble1.getImage(), leftBarSize + 255, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("8", leftBarSize + 266, topBarHeight + 55);
                }

                if(question9Answered == true && deck != null && deck.size()>= 9) {
                    g.drawImage(answerBubble2.getImage(), leftBarSize + 290, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("9", leftBarSize + 301, topBarHeight + 55);
                }
                else if(deck != null && deck.size()>= 9) {
                    g.drawImage(answerBubble1.getImage(), leftBarSize + 290, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("9", leftBarSize + 301, topBarHeight + 55);
                }

                if(question10Answered == true && deck != null && deck.size()>= 10) {
                    g.drawImage(answerBubble2.getImage(), leftBarSize + 325, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("10", leftBarSize + 332, topBarHeight + 55);
                }
                else if(deck != null && deck.size()>= 10) {
                    g.drawImage(answerBubble1.getImage(), leftBarSize + 325, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("10", leftBarSize + 332, topBarHeight + 55);
                }

                if(question11Answered == true && deck != null && deck.size()>= 11) {
                    g.drawImage(answerBubble2.getImage(), leftBarSize + 360, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("11", leftBarSize + 367, topBarHeight + 55);
                }
                else if(deck != null && deck.size()>= 11) {
                    g.drawImage(answerBubble1.getImage(), leftBarSize + 360, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("11", leftBarSize + 367, topBarHeight + 55);
                }

                if(question12Answered == true && deck != null && deck.size()>= 12) {
                    g.drawImage(answerBubble2.getImage(), leftBarSize + 395, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("12", leftBarSize + 402, topBarHeight + 55);
                }
                else if(deck != null && deck.size()>= 12) {
                    g.drawImage(answerBubble1.getImage(), leftBarSize + 395, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("12", leftBarSize + 402, topBarHeight + 55);
                }

                if(question13Answered == true && deck != null && deck.size()>= 13) {
                    g.drawImage(answerBubble2.getImage(), leftBarSize + 430, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("13", leftBarSize + 437, topBarHeight + 55);
                }
                else if(deck != null && deck.size()>= 13) {
                    g.drawImage(answerBubble1.getImage(), leftBarSize + 430, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("13", leftBarSize + 437, topBarHeight + 55);
                }

                if(question14Answered == true && deck != null && deck.size()>= 14) {
                    g.drawImage(answerBubble2.getImage(), leftBarSize + 465, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("14", leftBarSize + 472, topBarHeight + 55);
                }
                else if(deck != null && deck.size()>= 14) {
                    g.drawImage(answerBubble1.getImage(), leftBarSize + 465, topBarHeight + 35, 30, 30, null);
                    g.setFont(new Font("Helvetica", Font.BOLD, 15));
                    g.drawString("14", leftBarSize + 472, topBarHeight + 55);
                }

                if(question15Answered == true && deck != null && deck.size()>= 15)
                    g.drawImage(answerBubble2.getImage(), leftBarSize+500, topBarHeight+35, 30, 30, null);
                else if(deck != null && deck.size()>= 15)
                    g.drawImage(answerBubble1.getImage(), leftBarSize+500, topBarHeight+35, 30, 30, null);

                if(question16Answered == true && deck != null && deck.size()>= 16)
                    g.drawImage(answerBubble2.getImage(), leftBarSize+535, topBarHeight+35, 30, 30, null);
                else if(deck != null && deck.size()>= 16)
                    g.drawImage(answerBubble1.getImage(), leftBarSize+535, topBarHeight+35, 30, 30, null);

                if(question17Answered == true && deck != null && deck.size()>= 17)
                    g.drawImage(answerBubble2.getImage(), leftBarSize+570, topBarHeight+35, 30, 30, null);
                else if(deck != null && deck.size()>= 17)
                    g.drawImage(answerBubble1.getImage(), leftBarSize+570, topBarHeight+35, 30, 30, null);

                if(question18Answered == true && deck != null && deck.size()>= 18)
                    g.drawImage(answerBubble2.getImage(), leftBarSize+605, topBarHeight+35, 30, 30, null);
                else if(deck != null && deck.size()>= 18)
                    g.drawImage(answerBubble1.getImage(), leftBarSize+605, topBarHeight+35, 30, 30, null);

                if(question19Answered == true && deck != null && deck.size()>= 19)
                    g.drawImage(answerBubble2.getImage(), leftBarSize+640, topBarHeight+35, 30, 30, null);
                else if(deck != null && deck.size()>= 19)
                    g.drawImage(answerBubble1.getImage(), leftBarSize+640, topBarHeight+35, 30, 30, null);

                if(question20Answered == true && deck != null && deck.size()>= 20)
                    g.drawImage(answerBubble2.getImage(), leftBarSize+675, topBarHeight+35, 30, 30, null);
                else if(deck != null && deck.size()>= 20)
                    g.drawImage(answerBubble1.getImage(), leftBarSize+675, topBarHeight+35, 30, 30, null);

                if(deck != null && deck.size()>0) { //&& deck.size() >= 20 NEED TO ADD THIS BACK WHEN DONE TESTING!!!!
                    //true/false questions
                    if (currentQuestion == 1 || currentQuestion == 2 || currentQuestion == 3 || currentQuestion == 4 || currentQuestion == 5)
                    {
                        //display term
                        g.setFont(new Font("Helvetica", Font.BOLD, 25));
                        g.drawString("Term:", leftBarSize + 10, topBarHeight + 100);
                        g.setFont(new Font("Helvetica", Font.PLAIN, 22));
                        g.drawString(testQuestions.get(currentQuestion-1).getTerm(), leftBarSize + 10, topBarHeight+ 130);
                        //display definition
                        g.setFont(new Font("Helvetica", Font.BOLD, 25));
                        g.drawString("Definition:", leftBarSize + 380, topBarHeight + 100);
                        g.setFont(new Font("Helvetica", Font.PLAIN, 22));
                        int yVal = topBarHeight + 130; //increase by 30 each time
                        if(currentQuestion == 1 && trueOrFalse1 == 0) {
                            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(currentQuestion - 1).getDef());
                            for (int i = 0; i < linesforDefinition.size(); i++) {
                                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                                yVal += 30;
                            }
                        }
                        else if(currentQuestion == 1 && trueOrFalse1 == 1)
                        {
                            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(trueFalseWrongAnswer1).getDef());
                            for (int i = 0; i < linesforDefinition.size(); i++) {
                                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                                yVal += 30;
                            }
                        }
                        if(currentQuestion == 2 && trueOrFalse2 == 0) {
                            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(currentQuestion - 1).getDef());
                            for (int i = 0; i < linesforDefinition.size(); i++) {
                                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                                yVal += 30;
                            }
                        }
                        else if(currentQuestion == 2 && trueOrFalse2 == 1)
                        {
                            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(trueFalseWrongAnswer2).getDef());
                            for (int i = 0; i < linesforDefinition.size(); i++) {
                                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                                yVal += 30;
                            }
                        }
                        if(currentQuestion == 3 && trueOrFalse3 == 0) {
                            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(currentQuestion - 1).getDef());
                            for (int i = 0; i < linesforDefinition.size(); i++) {
                                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                                yVal += 30;
                            }
                        }
                        else if(currentQuestion == 3 && trueOrFalse3 == 1)
                        {
                            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(trueFalseWrongAnswer3).getDef());
                            for (int i = 0; i < linesforDefinition.size(); i++) {
                                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                                yVal += 30;
                            }
                        }
                        if(currentQuestion == 4 && trueOrFalse4 == 0) {
                            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(currentQuestion - 1).getDef());
                            for (int i = 0; i < linesforDefinition.size(); i++) {
                                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                                yVal += 30;
                            }
                        }
                        else if(currentQuestion == 4 && trueOrFalse4 == 1)
                        {
                            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(trueFalseWrongAnswer4).getDef());
                            for (int i = 0; i < linesforDefinition.size(); i++) {
                                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                                yVal += 30;
                            }
                        }
                        if(currentQuestion == 5 && trueOrFalse5 == 0) {
                            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(currentQuestion - 1).getDef());
                            for (int i = 0; i < linesforDefinition.size(); i++) {
                                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                                yVal += 30;
                            }
                        }
                        else if(currentQuestion == 5 && trueOrFalse5 == 1)
                        {
                            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(trueFalseWrongAnswer5).getDef());
                            for (int i = 0; i < linesforDefinition.size(); i++) {
                                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                                yVal += 30;
                            }
                        }

                        buttons[TRUEBUTTONFORTEST].drawButton(g);
                        buttons[FALSEBUTTONFORTEST].drawButton(g);
                        buttons[TRUEBUTTONFORTEST].setEnabled(true);
                        buttons[TRUEBUTTONFORTEST].setVisible(true);
                        buttons[FALSEBUTTONFORTEST].setEnabled(true);
                        buttons[FALSEBUTTONFORTEST].setVisible(true);
                        g.setFont(new Font("Helvetica", Font.BOLD, 22));
                        g.drawString("True", 370, 360);
                        g.drawString("False", 570, 360);
                    }
                    //multiple choice questions
                    else if(currentQuestion == 6 || currentQuestion == 7 || currentQuestion == 8 || currentQuestion == 9 || currentQuestion == 10)
                    {

                    }
                    //matching questions
                    else if(currentQuestion == 11 || currentQuestion == 12 || currentQuestion == 13 || currentQuestion == 14 || currentQuestion == 15)
                    {

                    }
                    //type your answer questions
                    else if(currentQuestion == 16 || currentQuestion == 17 || currentQuestion == 18 || currentQuestion == 19 || currentQuestion == 20)
                    {

                    }
                }
            }

            if(learnDisplay == true)
            {
                //deactivate gravity buttons
                buttons[GRAVITYSTARTGAME].setEnabled(false);
                buttons[GRAVITYSTARTGAME].setVisible(false);
                //deactivate flashcard buttons
                buttons[FLASHCARDFLIP].setEnabled(false);
                buttons[FLASHCARDFLIP].setVisible(false);
                buttons[PREVIOUSCARD].setVisible(false);
                buttons[PREVIOUSCARD].setEnabled(true);
                buttons[NEXTCARD].setVisible(false);
                buttons[NEXTCARD].setEnabled(false);

                //draw and activate learn buttons needed for learn display
                buttons[LEARNA].drawButton(g);
                buttons[LEARNA].setEnabled(true);
                buttons[LEARNB].drawButton(g);
                buttons[LEARNB].setEnabled(true);
                buttons[LEARNC].drawButton(g);
                buttons[LEARNC].setEnabled(true);
                buttons[LEARND].drawButton(g);
                buttons[LEARND].setEnabled(true);

                if(nullDeckDuringLearn == true && deck != null)
                {
                    nullDeckDuringLearn = false; //we don't want the if statement to be true again. Make one of the conditions false

                    g.setColor(Color.BLACK);
                    g.setFont(new Font("SansSerif", Font.BOLD, 20));
                    g.drawString("" + learnCardNumber + "/" + deck.size(), 540, 75);
                    g.setFont(new Font("Helvetica", Font.BOLD, 30));
                    // g.drawString(deck.get((int) (Math.random() * (deck.size()))).getDef(), 300, 200);
                    if(learnQueue.peek() == null)
                        makeLearnQueue();
                    g.setColor(Color.GRAY);
                    g.setFont(new Font("SansSerif", Font.BOLD, 20));
                    g.drawString("Definition", 215, 103);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("SansSerif", Font.PLAIN, 27));

                    randomAnswers = (int) (Math.random() * (4)) + 1;

                    randomWrongAnswer1 = (int) (Math.random() * (deck.size()));

                    //checking if the string equals the actual answer does not work right now :(
                    while ((deck.get(randomWrongAnswer1).getTerm()).equals(learnQueue.peek().getTerm())) {
                        randomWrongAnswer1 = (int) (Math.random() * (deck.size()));
                    }

                    randomWrongAnswer2 = (int) (Math.random() * (deck.size()));
                    while (randomWrongAnswer1 == randomWrongAnswer2 || (deck.get(randomWrongAnswer2).getTerm()).equals(learnQueue.peek().getTerm())) {
                        randomWrongAnswer2 = (int) (Math.random() * (deck.size()));
                    }

                    randomWrongAnswer3 = (int) (Math.random() * (deck.size()));
                    while (randomWrongAnswer1 == randomWrongAnswer3 || randomWrongAnswer3 == randomWrongAnswer2 || (deck.get(randomWrongAnswer3).getTerm()).equals(learnQueue.peek().getTerm())) {
                        randomWrongAnswer3 = (int) (Math.random() * (deck.size()));
                    }

                    randomWrongAnswer4 = (int) (Math.random() * (deck.size()));
                    while (randomWrongAnswer1 == randomWrongAnswer4 || randomWrongAnswer2 == randomWrongAnswer4 || randomWrongAnswer3 == randomWrongAnswer4 || (deck.get(randomWrongAnswer4).getTerm()).equals(learnQueue.peek().getTerm())) {
                        randomWrongAnswer4 = (int) (Math.random() * (deck.size()));
                    }

                    int yValue = 135; //increase by 30 each time
                    ArrayList<String> lines = addLinesToString(58, learnQueue.peek().getDef());
                    for(int i = 0; i<lines.size(); i++)
                    {
                        g.drawString(lines.get(i), 215, yValue);
                        //g.drawString("" + lines.size(), 215, yValue-10); //the size is only 1 ??? what
                        yValue += 33;
                    }

                    g.setFont(new Font("SansSerif", Font.BOLD, 20));
                    if(showIncorrect1 == true || showIncorrect2 == true || showIncorrect3 == true || showIncorrect4 == true)
                        g.drawString("Not quite!", 212, 320);
                    else if(showCorrect1 == true || showCorrect2 == true || showCorrect3 == true || showCorrect4 == true)
                        g.drawString("Good job!", 212, 320);
                    else
                        g.drawString("Choose matching term", 212, 320);

                    if(randomAnswers == 1)
                    {
                        A =  learnQueue.peek().getTerm();
                    }
                    else
                    {
                        A = deck.get(randomWrongAnswer1).getTerm();
                    }

                    if(randomAnswers == 2)
                    {
                        B = learnQueue.peek().getTerm();
                    }
                    else
                    {
                        B = deck.get(randomWrongAnswer2).getTerm();
                    }

                    if(randomAnswers == 3) {
                        C = learnQueue.peek().getTerm();
                    }
                    else
                    {
                        C = deck.get(randomWrongAnswer3).getTerm();
                    }

                    if(randomAnswers == 4)
                    {
                        D = learnQueue.peek().getTerm();
                    }
                    else
                    {
                        D = deck.get(randomWrongAnswer4).getTerm();
                    }

                    g.setFont(new Font("SansSerif", Font.PLAIN, 22));
                    g.drawString(A, 215, 380);
                    g.drawString(B, 585, 380);
                    g.drawString(C, 215, 475);
                    g.drawString(D, 585, 475);

                    if(showIncorrect1 == true) {
                        g.drawImage(incorrectAnswer.getImage(), 205, 330, 365, 80, null);
                        g.drawString(A, 215, 380);
                        buttons[LEARNA].setEnabled(false);
                        buttons[LEARNB].setEnabled(false);
                        buttons[LEARNC].setEnabled(false);
                        buttons[LEARND].setEnabled(false);
                        //draw red colored button
                    }

                    if(showIncorrect2 == true) {
                        g.drawImage(incorrectAnswer.getImage(), 575, 330, 365, 80, null);
                        g.drawString(B, 585, 380);
                        buttons[LEARNA].setEnabled(false);
                        buttons[LEARNB].setEnabled(false);
                        buttons[LEARNC].setEnabled(false);
                        buttons[LEARND].setEnabled(false);
                        //draw red colored button
                    }
                    if(showIncorrect3 == true) {
                        g.drawImage(incorrectAnswer.getImage(), 205, 420, 365, 80, null);
                        g.drawString(C, 215, 475);
                        buttons[LEARNA].setEnabled(false);
                        buttons[LEARNB].setEnabled(false);
                        buttons[LEARNC].setEnabled(false);
                        buttons[LEARND].setEnabled(false);
                        //draw red colored button
                    }
                    if(showIncorrect4 == true) {
                        g.drawImage(incorrectAnswer.getImage(), 575, 420, 365, 80, null);
                        g.drawString(D, 585, 475);
                        buttons[LEARNA].setEnabled(false);
                        buttons[LEARNB].setEnabled(false);
                        buttons[LEARNC].setEnabled(false);
                        buttons[LEARND].setEnabled(false);
                        //draw red colored button
                    }
                    if(showCorrect1 == true) {
                        g.drawImage(correctAnswer.getImage(), 205, 330, 365, 80, null);
                        g.drawString(A, 215, 380);
                        buttons[LEARNA].setEnabled(false);
                        buttons[LEARNB].setEnabled(false);
                        buttons[LEARNC].setEnabled(false);
                        buttons[LEARND].setEnabled(false);
                        //draw green colored button
                    }
                    if(showCorrect2 == true) {
                        g.drawImage(correctAnswer.getImage(), 575, 330, 365, 80, null);
                        g.drawString(B, 585, 380);
                        buttons[LEARNA].setEnabled(false);
                        buttons[LEARNB].setEnabled(false);
                        buttons[LEARNC].setEnabled(false);
                        buttons[LEARND].setEnabled(false);
                        //draw green colored button
                    }
                    if(showCorrect3 == true) {
                        g.drawImage(correctAnswer.getImage(), 205, 420, 365, 80, null);
                        g.drawString(C, 215, 475);
                        buttons[LEARNA].setEnabled(false);
                        buttons[LEARNB].setEnabled(false);
                        buttons[LEARNC].setEnabled(false);
                        buttons[LEARND].setEnabled(false);
                        //draw green colored button
                    }
                    if(showCorrect4 == true) {
                        g.drawImage(correctAnswer.getImage(), 575, 420, 365, 80, null);
                        g.drawString(D, 585, 475);
                        buttons[LEARNA].setEnabled(false);
                        buttons[LEARNB].setEnabled(false);
                        buttons[LEARNC].setEnabled(false);
                        buttons[LEARND].setEnabled(false);
                        //draw green colored button
                    }
                }

                if(showIncorrect1 == true) {
                    g.drawImage(incorrectAnswer.getImage(), 205, 330, 365, 80, null);
                    g.drawString(A, 215, 380);
                    //draw red colored button
                }

                if(frameGravity >= currentTimer + 75 && changeAnswers == true)
                {
                    randomAnswers = (int)(Math.random()*(4))+1;
                    randomAnswers = (int)(Math.random()*(4))+1;
                    randomAnswers = (int)(Math.random()*(4))+1;

                    learnQueue.remove();

                    showIncorrect1 = false;
                    showIncorrect2 = false;
                    showIncorrect3 = false;
                    showIncorrect4 = false;
                    showCorrect1 = false;
                    showCorrect2 = false;
                    showCorrect3 = false;
                    showCorrect4 = false;

                    if(!learnQueue.isEmpty()) {
                        randomWrongAnswer1 = (int) (Math.random() * (deck.size()));

                        //checking if the string equals the actual answer does not work right now :(
                        while ((deck.get(randomWrongAnswer1).getTerm()).equals(learnQueue.peek().getTerm())) {
                            randomWrongAnswer1 = (int) (Math.random() * (deck.size()));
                        }

                        randomWrongAnswer2 = (int) (Math.random() * (deck.size()));
                        while (randomWrongAnswer1 == randomWrongAnswer2 || (deck.get(randomWrongAnswer2).getTerm()).equals(learnQueue.peek().getTerm())) {
                            randomWrongAnswer2 = (int) (Math.random() * (deck.size()));
                        }

                        randomWrongAnswer3 = (int) (Math.random() * (deck.size()));
                        while (randomWrongAnswer1 == randomWrongAnswer3 || randomWrongAnswer3 == randomWrongAnswer2 || (deck.get(randomWrongAnswer3).getTerm()).equals(learnQueue.peek().getTerm())) {
                            randomWrongAnswer3 = (int) (Math.random() * (deck.size()));
                        }

                        randomWrongAnswer4 = (int) (Math.random() * (deck.size()));
                        while (randomWrongAnswer1 == randomWrongAnswer4 || randomWrongAnswer2 == randomWrongAnswer4 || randomWrongAnswer3 == randomWrongAnswer4 || (deck.get(randomWrongAnswer4).getTerm()).equals(learnQueue.peek().getTerm())) {
                            randomWrongAnswer4 = (int) (Math.random() * (deck.size()));
                        }
                    }
                    changeAnswers = false;
                    learnCardNumber++;
                    if(learnQueue.isEmpty())
                        deckIsComplete = true;

                    currentTimer = -1;
                }

                if(deckIsComplete == true)
                {
                    g.setColor(Color.WHITE);
                    g.fillRect(200,50, xSIZE-200,ySIZE-50);
                    g.drawImage(deckCompleteCongrats.getImage(), 200,50,760, 490, null);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Helvetica", Font.PLAIN, 30));
                    //try again button is activated and drawn on the screen
                    buttons[TRYAGAIN].drawButton(g);
                    buttons[TRYAGAIN].setEnabled(true);
                }

                if(deck == null)
                {
                    nullDeckDuringLearn = true;
                    g.setColor(Color.RED);
                    g.setFont(new Font("Helvetica", Font.PLAIN, 30));
                    g.drawString("Select a deck!", 490, 260);
                }
                else if(!learnQueue.isEmpty())
                {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("SansSerif", Font.BOLD, 20));
                    g.drawString("" + learnCardNumber + "/" + deck.size(), 540, 75);
                    g.setFont(new Font("Helvetica", Font.BOLD, 30));
                   // g.drawString(deck.get((int) (Math.random() * (deck.size()))).getDef(), 300, 200);
                    if(learnQueue.peek() == null)
                        makeLearnQueue();
                    g.setColor(Color.GRAY);
                    g.setFont(new Font("SansSerif", Font.BOLD, 20));
                    g.drawString("Definition", 215, 103);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("SansSerif", Font.PLAIN, 27));

                    int yValue = 135; //increase by 30 each time
                    ArrayList<String> lines = addLinesToString(58, learnQueue.peek().getDef());
                     for(int i = 0; i<lines.size(); i++)
                     {
                         g.drawString(lines.get(i), 215, yValue);
                         //g.drawString("" + lines.size(), 215, yValue-10); //the size is only 1 ??? what
                         yValue += 33;
                     }

                    g.setFont(new Font("SansSerif", Font.BOLD, 20));
                    if(showIncorrect1 == true || showIncorrect2 == true || showIncorrect3 == true || showIncorrect4 == true)
                        g.drawString("Not quite!", 212, 320);
                    else if(showCorrect1 == true || showCorrect2 == true || showCorrect3 == true || showCorrect4 == true)
                        g.drawString("Good job!", 212, 320);
                    else
                        g.drawString("Choose matching term", 212, 320);

                    if(randomAnswers == 1)
                    {
                      A =  learnQueue.peek().getTerm();
                    }
                    else
                    {
                        A = deck.get(randomWrongAnswer1).getTerm();
                    }

                    if(randomAnswers == 2)
                    {
                        B = learnQueue.peek().getTerm();
                    }
                    else
                    {
                        B = deck.get(randomWrongAnswer2).getTerm();
                    }

                    if(randomAnswers == 3) {
                        C = learnQueue.peek().getTerm();
                    }
                    else
                    {
                        C = deck.get(randomWrongAnswer3).getTerm();
                    }

                    if(randomAnswers == 4)
                    {
                        D = learnQueue.peek().getTerm();
                    }
                    else
                    {
                        D = deck.get(randomWrongAnswer4).getTerm();
                    }

                    g.setFont(new Font("SansSerif", Font.PLAIN, 22));
                    g.drawString(A, 215, 380);
                    g.drawString(B, 585, 380);
                    g.drawString(C, 215, 475);
                    g.drawString(D, 585, 475);

                    if(showIncorrect1 == true) {
                        g.drawImage(incorrectAnswer.getImage(), 205, 330, 365, 80, null);
                        g.drawString(A, 215, 380);
                        buttons[LEARNA].setEnabled(false);
                        buttons[LEARNB].setEnabled(false);
                        buttons[LEARNC].setEnabled(false);
                        buttons[LEARND].setEnabled(false);
                        //draw red colored button
                    }

                    if(showIncorrect2 == true) {
                        g.drawImage(incorrectAnswer.getImage(), 575, 330, 365, 80, null);
                        g.drawString(B, 585, 380);
                        buttons[LEARNA].setEnabled(false);
                        buttons[LEARNB].setEnabled(false);
                        buttons[LEARNC].setEnabled(false);
                        buttons[LEARND].setEnabled(false);
                        //draw red colored button
                    }
                    if(showIncorrect3 == true) {
                        g.drawImage(incorrectAnswer.getImage(), 205, 420, 365, 80, null);
                        g.drawString(C, 215, 475);
                        buttons[LEARNA].setEnabled(false);
                        buttons[LEARNB].setEnabled(false);
                        buttons[LEARNC].setEnabled(false);
                        buttons[LEARND].setEnabled(false);
                        //draw red colored button
                    }
                    if(showIncorrect4 == true) {
                        g.drawImage(incorrectAnswer.getImage(), 575, 420, 365, 80, null);
                        g.drawString(D, 585, 475);
                        buttons[LEARNA].setEnabled(false);
                        buttons[LEARNB].setEnabled(false);
                        buttons[LEARNC].setEnabled(false);
                        buttons[LEARND].setEnabled(false);
                        //draw red colored button
                    }
                    if(showCorrect1 == true) {
                        g.drawImage(correctAnswer.getImage(), 205, 330, 365, 80, null);
                        g.drawString(A, 215, 380);
                        buttons[LEARNA].setEnabled(false);
                        buttons[LEARNB].setEnabled(false);
                        buttons[LEARNC].setEnabled(false);
                        buttons[LEARND].setEnabled(false);
                        //draw green colored button
                    }
                    if(showCorrect2 == true) {
                        g.drawImage(correctAnswer.getImage(), 575, 330, 365, 80, null);
                        g.drawString(B, 585, 380);
                        buttons[LEARNA].setEnabled(false);
                        buttons[LEARNB].setEnabled(false);
                        buttons[LEARNC].setEnabled(false);
                        buttons[LEARND].setEnabled(false);
                        //draw green colored button
                    }
                    if(showCorrect3 == true) {
                        g.drawImage(correctAnswer.getImage(), 205, 420, 365, 80, null);
                        g.drawString(C, 215, 475);
                        buttons[LEARNA].setEnabled(false);
                        buttons[LEARNB].setEnabled(false);
                        buttons[LEARNC].setEnabled(false);
                        buttons[LEARND].setEnabled(false);
                        //draw green colored button
                    }
                    if(showCorrect4 == true) {
                        g.drawImage(correctAnswer.getImage(), 575, 420, 365, 80, null);
                        g.drawString(D, 585, 475);
                        buttons[LEARNA].setEnabled(false);
                        buttons[LEARNB].setEnabled(false);
                        buttons[LEARNC].setEnabled(false);
                        buttons[LEARND].setEnabled(false);
                        //draw green colored button
                    }
                }
            }

            //top bar with settings buttons
            g.setColor(new Color(110, 220, 230));
            g.fillRect(leftBarSize,0,xSIZE-leftBarSize, topBarHeight);
            //draw buttons
            buttons[HOMEBUTTON].drawButton(g);
            buttons[HOMEBUTTON].setEnabled(true);
            buttons[HOMEBUTTON].setVisible(true);
            buttons[EXPORTBUTTON].drawButton(g);
            buttons[EXPORTBUTTON].setEnabled(true);
            buttons[EXPORTBUTTON].setVisible(true);
            buttons[SETTINGSBUTTON].drawButton(g);
            buttons[SETTINGSBUTTON].setEnabled(true);
            buttons[SETTINGSBUTTON].setVisible(true);
            buttons[NEWDECKBUTTON].drawButton(g);
            buttons[NEWDECKBUTTON].setEnabled(true);
            buttons[NEWDECKBUTTON].setVisible(true);

            g.setColor(Color.BLACK);
            g.fillRect(0,topBarHeight, leftBarSize, 3);


            if(numberOfSettingsClicks == 1) //if settings button is clicked, show settings menu
            {
                //draw settings bar
                g.setColor(new Color(220,220,220));
                g.fillRoundRect(xSIZE - (3*topBarHeight) - 60, topBarHeight+5, (3*topBarHeight)+45, 445, 10,10);
                g.setColor(Color.BLACK);
                //Add text to settings bar
                g.setFont(new Font("Helvetica", Font.PLAIN, 15));
                g.drawString("Draw texts here\n for settings options", xSIZE - (3*topBarHeight) - 35, topBarHeight+5 + 20);
                //Above, new line is not working. Not sure why. Need to fix
                for(Button bu : buttons)
                {
                    if(bu.getTitle().equals("home") || bu.getTitle().equals("settings")) //enable home, settings, and buttons within settings only
                        bu.setEnabled(true);
                    else
                        bu.setEnabled(false);
                }
            }
            repaint();
        }
        else //if(screenMode == )
        {
            g.setColor(new Color(0, 0, 0));
            g.fillRect(0,0,xSIZE, ySIZE);

            //draw buttons
            buttons[HOMEBUTTON].drawButton(g);
            buttons[HOMEBUTTON].setEnabled(true);
            buttons[HOMEBUTTON].setVisible(true);

            repaint();
        }
    }

    /**
     *
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        showBoard(g);
    }

    public int getTestScore()
    {
        int score = 0;
     if(question1Answered && trueOrFalse1 == 0 && trueFalse1 == true)
         score++;
        if(question2Answered && trueOrFalse2 == 0 && trueFalse2 == true)
            score++;
        if(question3Answered && trueOrFalse3 == 0 && trueFalse3 == true)
            score++;
        if(question4Answered && trueOrFalse4 == 0 && trueFalse4 == true)
            score++;
        if(question5Answered && trueOrFalse5 == 0 && trueFalse5 == true)
            score++;
     return score;
    }

    /**
     * @param maxCharacters the number of characters wanted in the new String
     * @param original the String that needs to be cut down
     *
     * Returns a new String within a max character count, ending with "..."
     */
    public String limitCharacterCount(int maxCharacters, String original)
    {
        String returnThis = "";
        boolean keepGoing = true;
        int totalLength = 0;
        int max = maxCharacters - 3; //save space for "..."
        String[] split;
       if(original.length()<= maxCharacters) { //we don't need to change anything. original String length is within bounds
           return original;
       }
       else
       {
           split = original.split(" "); //split the string wherever there is a space
           for(int i = 0; i<split.length; i++)
           {
               if(keepGoing == true) {
                   if ((totalLength + split[i].length() <= max)) {
                       returnThis += split[i];
                       totalLength += split[i].length();
                   }
                   else {
                       returnThis += split[i].substring(0, max - totalLength) + "...";
                       keepGoing = false;
                   }
                   if (keepGoing == true && totalLength + 1 <= max) {
                       returnThis += " ";
                       totalLength++;
                   }
                   if (keepGoing == true && totalLength >= max) {
                       returnThis += "...";
                       keepGoing = false;
                   }
               }
           }
       }
       return returnThis;
    }

    public ArrayList<String> addLinesToString(int maxCharacters, String original)
    {
        ArrayList<String> returnThis = new ArrayList<String>();
        int arrayIndex = 0;
        String[] split;

        if(original.length() <= maxCharacters) //we don't need to change anything. original String length is within bounds
        {
            returnThis.add(original);
            return returnThis;
        }
        else //we need to change the string
        {
            split = original.split(" "); //split the string wherever there is a space
            for(int i = 0; i<split.length; i++)
            {
                if(arrayIndex == 0 && i == 0)
                {
                    returnThis.add("" + split[i] + " ");
                }
                else if(returnThis.get(arrayIndex).length() + split[i].length() <= maxCharacters) //there is room to add a new word. Add it to the current line
                {
                    returnThis.set(arrayIndex, returnThis.get(arrayIndex) + split[i] + " ");
                }
                else if(returnThis.get(arrayIndex).length() + split[i].length() > maxCharacters) //add current line to returnThis. start a new line
                {
                    arrayIndex++;
                    returnThis.add("" + split[i] + " ");
                }
            }
        }
        return returnThis;
    }

    /** Setter method for Deck */
    public static boolean setDeck(Deck d) {
        if (d == null) {
            System.out.println("[MainPanel] No Deck Selected");
            return false;
        }
        deck = d;
        System.out.println("[MainPanel] Confirmed " + deck.toString() + " as Selected Deck");
        return true;
    }

    public static void makeTestList()
    {
        int[] numbers = new int[20];
        for(int h = 0; h<20; h++)
            numbers[h] = -1;
        int newnumber = -1;
        boolean equalsAnotherNumber = false;
        Deck copyOfDeck = new Deck();

        if(deck.size()>20)
        {
           for(int i = 0; i<20; i++) {
               newnumber = (int) (Math.random() * deck.size());
               for (int k = 0; k < 20; k++) {
                   if (numbers[k] == newnumber)
                       equalsAnotherNumber = true;
               }
               while (equalsAnotherNumber == true) {
                   newnumber = (int) (Math.random() * deck.size()); //newnumber gets a new random number
                   equalsAnotherNumber = false;
                   for (int j = 0; j < 20; j++) {
                       if (numbers[j] == newnumber)
                           equalsAnotherNumber = true;
                   }
               }
           }
        }
        else
        {
            for (int i = 0; i < deck.size(); i++) {
                copyOfDeck.add(deck.get(i));
            }
        }

        int firstIndex = 0;
        int secondIndex = 0;
        Flashcard temp;

        for(int j = 0; j<250; j++)
        {
            firstIndex = (int)(Math.random()*(copyOfDeck.size()));
            secondIndex = (int)(Math.random()*(copyOfDeck.size()));
            temp = copyOfDeck.get(firstIndex);
            copyOfDeck.set(firstIndex, copyOfDeck.get(secondIndex));
            copyOfDeck.set(secondIndex, temp);
        }

        for(int k = 0; k<copyOfDeck.size(); k++)
            testQuestions.add(copyOfDeck.get(k));
    }

    public static void makeLearnQueue()
    {
        Deck copyOfDeck = new Deck();
        for(int i = 0; i<deck.size(); i++)
        {
            copyOfDeck.add(deck.get(i));
        }

        int firstIndex = 0;
        int secondIndex = 0;
        Flashcard temp;

       for(int j = 0; j<250; j++)
       {
           firstIndex = (int)(Math.random()*(copyOfDeck.size()));
           secondIndex = (int)(Math.random()*(copyOfDeck.size()));
           temp = copyOfDeck.get(firstIndex);
           copyOfDeck.set(firstIndex, copyOfDeck.get(secondIndex));
           copyOfDeck.set(secondIndex, temp);
       }

       for(int k = 0; k<copyOfDeck.size(); k++)
        learnQueue.add(copyOfDeck.get(k));
    }

    /**
     * Controls actions for when the mouse is clicked
     * @param e the event to be processed
     */
    public void mouseClicked(MouseEvent e)
    {
       // int button = e.getButton();
      /*  if (button == MouseEvent.BUTTON1) {
            if (gameMode == TUTORIALSCREEN) {
                for (int i = 0; i < buttons.length; i++) {
                    buttons[i].setEnabled(false);
                }
                tutorialIndex++;
            }
        }*/
        /*else
         { */
         //***BUTTON CODE***actions if clicked on button
            for(Button b:buttons)
            {
               if(b.getShape().contains(mouseX, mouseY) && b.isEnabled()) {
                   //examples
                  /* if(b.getTitle().equals("quit"))
                     System.exit(0);
                  else if(b.getTitle().equals("reset"))
                     resetEnemies();
                  else if(b.getTitle().equals("sound"))
                     Sound.click();*/

                   if (b.getTitle().equals("home"))  //when the home button is clicked, take the user back to the home screen
                   {
                       screenMode = HOMESCREEN;
                       if (numberOfSettingsClicks == 1)
                           numberOfSettingsClicks = 0;
                       learnDisplay = false;
                       testDisplay = false;
                       gravityDisplay = false;
                       gravityStartDisplay = false;
                       flashcardsDisplay = false;
                       homeScreenDisplay = true;
                       matchingDisplay = false;
                   }
                   if (b.getTitle().equals("export")) {
                       data.showDatabaseGUI();
                   }
                   if (b.getTitle().equals("settings")) {
                       if (numberOfSettingsClicks == 0)
                           numberOfSettingsClicks = 1;
                       else //numberOfSettingsClicks == 1
                           numberOfSettingsClicks = 0;
                   }
                   if (b.getTitle().equals("newDeck")) {
                       screenMode = 1; //change this for what to do when newCourse is clicked
                   }
                   if (b.getTitle().equals("flashcards"))
                   {
                       homeScreenDisplay = false;
                       learnDisplay = false;
                       testDisplay = false;
                       gravityDisplay = false;
                       gravityStartDisplay = false;
                       flashcardsDisplay = true;
                       termOddDefEven = 1;
                       matchingDisplay = false;
                   }
                   if (b.getTitle().equals("learn"))
                   {
                       homeScreenDisplay = false;
                       gravityDisplay = false;
                       learnDisplay = true;
                       testDisplay = false;
                       gravityStartDisplay = false;
                       flashcardsDisplay = false;
                       matchingDisplay = false;
                       learnCardNumber = 1;

                       if(deck != null)
                       makeLearnQueue();

                       if(learnQueue.peek() != null) {
                           randomAnswers = (int) (Math.random() * (4)) + 1;

                           randomWrongAnswer1 = (int) (Math.random() * (deck.size()));
                           while (deck.get(randomWrongAnswer1).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
                               randomWrongAnswer1 = (int)(Math.random()*(deck.size()));

                               randomWrongAnswer2 = (int) (Math.random() * (deck.size()));
                               while (deck.get(randomWrongAnswer2).getTerm().trim().equals(deck.get(randomWrongAnswer1).getTerm().trim()) || deck.get(randomWrongAnswer2).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
                                   randomWrongAnswer2 = (int) (Math.random() * (deck.size()));

                           randomWrongAnswer3 = (int) (Math.random() * (deck.size()));
                           while (deck.get(randomWrongAnswer3).getTerm().trim().equals(deck.get(randomWrongAnswer1).getTerm().trim()) || deck.get(randomWrongAnswer3).getTerm().trim().equals(deck.get(randomWrongAnswer2).getTerm().trim()) || deck.get(randomWrongAnswer3).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
                               randomWrongAnswer3 = (int) (Math.random() * (deck.size()));

                           randomWrongAnswer4 = (int) (Math.random() * (deck.size()));
                           while (deck.get(randomWrongAnswer4).getTerm().trim().equals(deck.get(randomWrongAnswer1).getTerm().trim()) || deck.get(randomWrongAnswer4).getTerm().trim().equals(deck.get(randomWrongAnswer2).getTerm().trim()) || deck.get(randomWrongAnswer4).getTerm().trim().equals(deck.get(randomWrongAnswer3).getTerm().trim()) || deck.get(randomWrongAnswer4).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
                               randomWrongAnswer4 = (int) (Math.random() * (deck.size()));
                       }
                   }
                   if (b.getTitle().equals("test"))
                   {
                       homeScreenDisplay = false;
                       gravityDisplay = false;
                       learnDisplay = false;
                       testDisplay = true;
                       gravityStartDisplay = false;
                       flashcardsDisplay = false;
                       matchingDisplay = false;

                       if(deck!= null) {
                           makeTestList();

                           trueFalseWrongAnswer1 = (int)(Math.random()*testQuestions.size());
                           while(trueFalseWrongAnswer1 == 0)
                               trueFalseWrongAnswer1 = (int)(Math.random()*testQuestions.size());
                           trueFalseWrongAnswer2 = (int)(Math.random()*testQuestions.size());
                           while(trueFalseWrongAnswer2 == 0)
                               trueFalseWrongAnswer2 = (int)(Math.random()*testQuestions.size());
                           trueFalseWrongAnswer3 = (int)(Math.random()*testQuestions.size());
                           while(trueFalseWrongAnswer3 == 0)
                               trueFalseWrongAnswer3 = (int)(Math.random()*testQuestions.size());
                           trueFalseWrongAnswer4 = (int)(Math.random()*testQuestions.size());
                           while(trueFalseWrongAnswer4 == 0)
                               trueFalseWrongAnswer4 = (int)(Math.random()*testQuestions.size());
                           trueFalseWrongAnswer5 = (int)(Math.random()*testQuestions.size());
                           while(trueFalseWrongAnswer5 == 0)
                               trueFalseWrongAnswer5 = (int)(Math.random()*testQuestions.size());
                       }
                   }
                   if (b.getTitle().equals("gravity"))
                   {
                       homeScreenDisplay = false;
                       flashcardsDisplay = false;
                       learnDisplay = false;
                       testDisplay = false;
                       gravityDisplay = false;
                       gravityStartDisplay = true;
                       matchingDisplay = false;
                   }
                   if(b.getTitle().equals("gravityStartGame"))
                   {
                       homeScreenDisplay = false;
                       flashcardsDisplay = false;
                       learnDisplay = false;
                       testDisplay = false;
                       gravityStartDisplay = false;
                       gravityDisplay = true;
                       matchingDisplay = false;
                       asteroid1.setY(asteroidY); //resets the asteroid position when the gravity button is clicked
                   }
                   if(b.getTitle().equals("matching"))
                   {
                       homeScreenDisplay = false;
                       flashcardsDisplay = false;
                       learnDisplay = false;
                       testDisplay = false;
                       gravityStartDisplay = false;
                       gravityDisplay = false;
                       matchingDisplay = true;
                   }
                   if(b.getTitle().equals("flashCardFlip"))
                   {
                       flipCard = true;
                       termOddDefEven++;
                   }
                   if(b.getTitle().equals("nextCard"))
                   {
                       if((deck!= null) && (flashcardShownInt + 1 < deck.size()))
                           flashcardShownInt++;
                   }
                   if(b.getTitle().equals("previousCard"))
                   {
                       if((deck != null) && (flashcardShownInt - 1 >= 0))
                           flashcardShownInt--;
                   }
                   if(b.getTitle().equals("trueButton"))
                   {
                       if(currentQuestion == 1) {
                           question1Answered = true;
                           trueFalse1 = true;
                       }
                       if(currentQuestion == 2) {
                           question2Answered = true;
                           trueFalse2 = true;
                       }
                       if(currentQuestion == 3) {
                           question3Answered = true;
                           trueFalse3 = true;
                       }
                       if(currentQuestion == 4) {
                           question4Answered = true;
                           trueFalse4 = true;
                       }
                       if(currentQuestion == 5) {
                           question5Answered = true;
                           trueFalse5 = true;
                       }
                   }
                   if(b.getTitle().equals("falseButton"))
                   {
                       if(currentQuestion == 1) {
                           question1Answered = true;
                           trueFalse1 = false;
                       }
                       if(currentQuestion == 2) {
                           question2Answered = true;
                           trueFalse2 = false;
                       }
                       if(currentQuestion == 3) {
                           question3Answered = true;
                           trueFalse3 = false;
                       }
                       if(currentQuestion == 4) {
                           question4Answered = true;
                           trueFalse4 = false;
                       }
                       if(currentQuestion == 5) {
                           question5Answered = true;
                           trueFalse5 = false;
                       }
                   }
                   if(b.getTitle().equals("nextTestQuestion"))
                   {
                       if((deck.size()<= 20 && currentQuestion + 1 <= deck.size()) || (deck.size()> 20 && currentQuestion + 1 <= 20))
                    currentQuestion++;

                   }
                   if(b.getTitle().equals("previousTestQuestion"))
                   {
                     if(currentQuestion - 1 > 0)
                         currentQuestion--;
                   }
                   if(b.getTitle().equals("learnA"))
                   {
                       if(learnQueue.peek() != null) {
                           currentTimer = frameGravity;
                           changeAnswers = true;
                           if (A.equals(learnQueue.peek().getTerm())) //correct
                           {
//                           learnQueue.remove();
//                           learnCardNumber++;
                               showCorrect1 = true;
                           } else {
                               showIncorrect1 = true;
                           }
                           if (B.equals(learnQueue.peek().getTerm()))
                               showCorrect2 = true;
                           if (C.equals(learnQueue.peek().getTerm()))
                               showCorrect3 = true;
                           if (D.equals(learnQueue.peek().getTerm()))
                               showCorrect4 = true;

//                       if(!learnQueue.isEmpty()) {
//                           randomWrongAnswer1 = (int) (Math.random() * (deck.size()));
//                           while (deck.get(randomWrongAnswer1).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
//                               randomWrongAnswer1 = (int) (Math.random() * (deck.size()));
//
//                           randomWrongAnswer2 = (int) (Math.random() * (deck.size()));
//                           while (deck.get(randomWrongAnswer2).getTerm().trim().equals(deck.get(randomWrongAnswer1).getTerm().trim()) || deck.get(randomWrongAnswer2).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
//                               randomWrongAnswer2 = (int) (Math.random() * (deck.size()));
//
//                           randomWrongAnswer3 = (int) (Math.random() * (deck.size()));
//                           while (deck.get(randomWrongAnswer3).getTerm().trim().equals(deck.get(randomWrongAnswer1).getTerm().trim()) || deck.get(randomWrongAnswer3).getTerm().trim().equals(deck.get(randomWrongAnswer2).getTerm().trim()) || deck.get(randomWrongAnswer3).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
//                               randomWrongAnswer3 = (int) (Math.random() * (deck.size()));
//
//                           randomWrongAnswer4 = (int) (Math.random() * (deck.size()));
//                           while (deck.get(randomWrongAnswer4).getTerm().trim().equals(deck.get(randomWrongAnswer1).getTerm().trim()) || deck.get(randomWrongAnswer4).getTerm().trim().equals(deck.get(randomWrongAnswer2).getTerm().trim()) || deck.get(randomWrongAnswer4).getTerm().trim().equals(deck.get(randomWrongAnswer3).getTerm().trim()) || deck.get(randomWrongAnswer4).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
//                               randomWrongAnswer4 = (int) (Math.random() * (deck.size()));
//                       }
                       }
                   }
                   if(b.getTitle().equals("learnB"))
                   {
                       if(learnQueue.peek() != null) {
                           currentTimer = frameGravity;
                           changeAnswers = true;

                           if (B.equals(learnQueue.peek().getTerm())) //correct
                           {
//                           learnQueue.remove();
//                           learnCardNumber++;
                               showCorrect2 = true;
                           } else {
                               showIncorrect2 = true;
                           }
                           if (A.equals(learnQueue.peek().getTerm()))
                               showCorrect1 = true;
                           if (C.equals(learnQueue.peek().getTerm()))
                               showCorrect3 = true;
                           if (D.equals(learnQueue.peek().getTerm()))
                               showCorrect4 = true;

//                       if(!learnQueue.isEmpty()) {
//                           randomAnswers = (int) (Math.random() * (4)) + 1;
//
//                           randomWrongAnswer1 = (int) (Math.random() * (deck.size()));
//                           while (deck.get(randomWrongAnswer1).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
//                               randomWrongAnswer1 = (int) (Math.random() * (deck.size()));
//
//                           randomWrongAnswer2 = (int) (Math.random() * (deck.size()));
//                           while (deck.get(randomWrongAnswer2).getTerm().trim().equals(deck.get(randomWrongAnswer1).getTerm().trim()) || deck.get(randomWrongAnswer2).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
//                               randomWrongAnswer2 = (int) (Math.random() * (deck.size()));
//
//                           randomWrongAnswer3 = (int) (Math.random() * (deck.size()));
//                           while (deck.get(randomWrongAnswer3).getTerm().trim().equals(deck.get(randomWrongAnswer1).getTerm().trim()) || deck.get(randomWrongAnswer3).getTerm().trim().equals(deck.get(randomWrongAnswer2).getTerm().trim()) || deck.get(randomWrongAnswer3).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
//                               randomWrongAnswer3 = (int) (Math.random() * (deck.size()));
//
//                           randomWrongAnswer4 = (int) (Math.random() * (deck.size()));
//                           while (deck.get(randomWrongAnswer4).getTerm().trim().equals(deck.get(randomWrongAnswer1).getTerm().trim()) || deck.get(randomWrongAnswer4).getTerm().trim().equals(deck.get(randomWrongAnswer2).getTerm().trim()) || deck.get(randomWrongAnswer4).getTerm().trim().equals(deck.get(randomWrongAnswer3).getTerm().trim()) || deck.get(randomWrongAnswer4).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
//                               randomWrongAnswer4 = (int) (Math.random() * (deck.size()));
//                       }
                       }
                   }
                   if(b.getTitle().equals("learnC"))
                   {
                       if(learnQueue.peek() != null) {
                           currentTimer = frameGravity;
                           changeAnswers = true;

                           if (C.equals(learnQueue.peek().getTerm())) //correct
                           {
//                           learnQueue.remove();
//                           learnCardNumber++;
                               showCorrect3 = true;
                           } else {
                               showIncorrect3 = true;
                           }
                           if (A.equals(learnQueue.peek().getTerm()))
                               showCorrect1 = true;
                           if (B.equals(learnQueue.peek().getTerm()))
                               showCorrect2 = true;
                           if (D.equals(learnQueue.peek().getTerm()))
                               showCorrect4 = true;

//                       if(!learnQueue.isEmpty()) {
//                           randomAnswers = (int) (Math.random() * (4)) + 1;
//
//                           randomWrongAnswer1 = (int) (Math.random() * (deck.size()));
//                           while (deck.get(randomWrongAnswer1).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
//                               randomWrongAnswer1 = (int) (Math.random() * (deck.size()));
//
//                           randomWrongAnswer2 = (int) (Math.random() * (deck.size()));
//                           while (deck.get(randomWrongAnswer2).getTerm().trim().equals(deck.get(randomWrongAnswer1).getTerm().trim()) || deck.get(randomWrongAnswer2).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
//                               randomWrongAnswer2 = (int) (Math.random() * (deck.size()));
//
//                           randomWrongAnswer3 = (int) (Math.random() * (deck.size()));
//                           while (deck.get(randomWrongAnswer3).getTerm().trim().equals(deck.get(randomWrongAnswer1).getTerm().trim()) || deck.get(randomWrongAnswer3).getTerm().trim().equals(deck.get(randomWrongAnswer2).getTerm().trim()) || deck.get(randomWrongAnswer3).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
//                               randomWrongAnswer3 = (int) (Math.random() * (deck.size()));
//
//                           randomWrongAnswer4 = (int) (Math.random() * (deck.size()));
//                           while (deck.get(randomWrongAnswer4).getTerm().trim().equals(deck.get(randomWrongAnswer1).getTerm().trim()) || deck.get(randomWrongAnswer4).getTerm().trim().equals(deck.get(randomWrongAnswer2).getTerm().trim()) || deck.get(randomWrongAnswer4).getTerm().trim().equals(deck.get(randomWrongAnswer3).getTerm().trim()) || deck.get(randomWrongAnswer4).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
//                               randomWrongAnswer4 = (int) (Math.random() * (deck.size()));
//                       }
                       }
                   }
                   if(b.getTitle().equals("learnD"))
                   {
                       if(learnQueue.peek() != null) {
                           currentTimer = frameGravity;
                           changeAnswers = true;

                           if (D.equals(learnQueue.peek().getTerm())) //correct
                           {
//                           learnQueue.remove();
//                           learnCardNumber++;
                               showCorrect4 = true;
                           } else {
                               showIncorrect4 = true;
                           }
                           if (A.equals(learnQueue.peek().getTerm()))
                               showCorrect1 = true;
                           if (B.equals(learnQueue.peek().getTerm()))
                               showCorrect2 = true;
                           if (C.equals(learnQueue.peek().getTerm()))
                               showCorrect3 = true;

//                       if(!learnQueue.isEmpty()) {
//                           randomAnswers = (int) (Math.random() * (4)) + 1;
//
//                           randomWrongAnswer1 = (int) (Math.random() * (deck.size()));
//                           while (deck.get(randomWrongAnswer1).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
//                               randomWrongAnswer1 = (int) (Math.random() * (deck.size()));
//
//                           randomWrongAnswer2 = (int) (Math.random() * (deck.size()));
//                           while (deck.get(randomWrongAnswer2).getTerm().trim().equals(deck.get(randomWrongAnswer1).getTerm().trim()) || deck.get(randomWrongAnswer2).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
//                               randomWrongAnswer2 = (int) (Math.random() * (deck.size()));
//
//                           randomWrongAnswer3 = (int) (Math.random() * (deck.size()));
//                           while (deck.get(randomWrongAnswer3).getTerm().trim().equals(deck.get(randomWrongAnswer1).getTerm().trim()) || deck.get(randomWrongAnswer3).getTerm().trim().equals(deck.get(randomWrongAnswer2).getTerm().trim()) || deck.get(randomWrongAnswer3).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
//                               randomWrongAnswer3 = (int) (Math.random() * (deck.size()));
//
//                           randomWrongAnswer4 = (int) (Math.random() * (deck.size()));
//                           while (deck.get(randomWrongAnswer4).getTerm().trim().equals(deck.get(randomWrongAnswer1).getTerm().trim()) || deck.get(randomWrongAnswer4).getTerm().trim().equals(deck.get(randomWrongAnswer2).getTerm().trim()) || deck.get(randomWrongAnswer4).getTerm().trim().equals(deck.get(randomWrongAnswer3).getTerm().trim()) || deck.get(randomWrongAnswer4).getTerm().trim().equals(learnQueue.peek().getTerm().trim()))
//                               randomWrongAnswer4 = (int) (Math.random() * (deck.size()));
//                       }
                       }
                   }
                   if(b.getTitle().equals("tryAgain"))
                   {
                       makeLearnQueue();
                       deckIsComplete = false;
                       learnCardNumber = 1;
                   }
               }
              } //end of buttons for loop
    } //end of mouseClicked

    public void mousePressed( MouseEvent e )
    {}

    public void mouseReleased( MouseEvent e )
    {}

    public void mouseEntered( MouseEvent e )
    {}

    /**
     * Controls actions for when the mouse is moved
     * @param e the event to be processed
     */
    public void mouseMoved( MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        //***BUTTON CODE***highlight button if mouse is on it
        for (Button b : buttons) {
            if (b.getShape().contains(mouseX, mouseY) && b.isEnabled())
                b.highlight();
            else
                b.unHighlight();
        }
        if (numberOfSettingsClicks > 0) {
            buttons[SETTINGSBUTTON].highlight();
        }
        if (gravityDisplay == true || gravityStartDisplay == true) {
            buttons[GRAVITY].highlight();
        }
        if (flashcardsDisplay == true) {
            buttons[FLASHCARDS].highlight();
        }
        if (learnDisplay == true) {
            buttons[LEARN].highlight();
        }
        if (testDisplay == true) {
            buttons[TEST].highlight();
        }
        if (matchingDisplay == true) {
            buttons[MATCHING].highlight();
        }
        if (flashcardShownInt == 0) {
            buttons[PREVIOUSCARD].highlight();
        }
        if ((deck == null) || ((deck != null) && (flashcardShownInt == deck.size() - 1))) {
            buttons[NEXTCARD].highlight();
        }
        //true/false question 1
        if (trueFalse1 == true && currentQuestion == 1 && question1Answered == true) {
            buttons[TRUEBUTTONFORTEST].highlight();
            buttons[FALSEBUTTONFORTEST].unHighlight();
        }
        if (trueFalse1 == false && currentQuestion == 1 && question1Answered == true) {
            buttons[FALSEBUTTONFORTEST].highlight();
            buttons[TRUEBUTTONFORTEST].unHighlight();
        }
        //true/false question 2
        if (trueFalse2 == true && currentQuestion == 2 && question2Answered == true) {
            buttons[TRUEBUTTONFORTEST].highlight();
            buttons[FALSEBUTTONFORTEST].unHighlight();
        }
        if (trueFalse2 == false && currentQuestion == 2 && question2Answered == true) {
            buttons[FALSEBUTTONFORTEST].highlight();
            buttons[TRUEBUTTONFORTEST].unHighlight();
        }
        //true/false question 3
        if (trueFalse3 == true && currentQuestion == 3 && question3Answered == true) {
            buttons[TRUEBUTTONFORTEST].highlight();
            buttons[FALSEBUTTONFORTEST].unHighlight();
        }
        if (trueFalse3 == false && currentQuestion == 3 && question3Answered == true) {
            buttons[FALSEBUTTONFORTEST].highlight();
            buttons[TRUEBUTTONFORTEST].unHighlight();
        }
        //true/false question 4
        if (trueFalse4 == true && currentQuestion == 4 && question4Answered == true) {
            buttons[TRUEBUTTONFORTEST].highlight();
            buttons[FALSEBUTTONFORTEST].unHighlight();
        }
        if (trueFalse4 == false && currentQuestion == 4 && question4Answered == true) {
            buttons[FALSEBUTTONFORTEST].highlight();
            buttons[TRUEBUTTONFORTEST].unHighlight();
        }
        //true/false question 5
        if (trueFalse5 == true && currentQuestion == 5 && question5Answered == true) {
            buttons[TRUEBUTTONFORTEST].highlight();
            buttons[FALSEBUTTONFORTEST].unHighlight();
        }
        if (trueFalse5 == false && currentQuestion == 5 && question5Answered == true) {
            buttons[FALSEBUTTONFORTEST].highlight();
            buttons[TRUEBUTTONFORTEST].unHighlight();
        }
    }

    public void mouseDragged( MouseEvent e)
    {}

    public void mouseExited( MouseEvent e )
    {}

    private class Listener implements ActionListener
    {
      public void actionPerformed(ActionEvent e) //this is called for each timer iteration
      {
          frameGravity++;
          if(frameGravity == Integer.MAX_VALUE)
              frameGravity = 0;
          asteroid1.moveDown(); //decreases y value (position in pixel space) by 1 to have the appearance of the asteroid falling down
          //you could make a method within asteroid that relates to animation delay and the y value
          //new variable numFramesY, divide by animation delay. When numFramesY % animation delay == 0, then decrease the y value by one
          //for harder levels, the animation delay is less to make the asteroids go faster.

          if(flashcard1.continueFlipAnimation() == false)
          {
              flipCard = false;
              frameFlashCard = 0;
          }

          frameFlashCard++;
          if(frameFlashCard == Integer.MAX_VALUE)
              frameFlashCard = 0;

          repaint();
      }
    }
}
