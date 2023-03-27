import com.jayway.jsonpath.JsonPath;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

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
    private boolean testSubmittedDisplay = false;
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
    private String currentDeckName = "";
    //for test
    private int currentQuestion = 1;
    private boolean nullDeckDuringTest = false;
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
    private int[] correctMatching = {11,12,13,14,15}; //ints are indexes (currentQuestion - 1)
    private int[] userInputMatching = new int[5];

    private int termMouseX = -1;
    private int termMouseY = -1;

    private int defMouseX = -1;
    private int defMouseY = -1;


    //images
    //private ImageIcon startScreen  = new ImageIcon("images/backgrounds/startScreen.png");

    //array of buttons
    private Button[] buttons = new Button[38];

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
    private static final int MCQATESTBUTTON = 23;
    private static final int MCQBTESTBUTTON = 24;
    private static final int MCQCTESTBUTTON = 25;
    private static final int MCQDTESTBUTTON = 26;
    //matching test question buttons
    private static final int MATCHINGTESTDEFINITION1 = 27;
    private static final int MATCHINGTESTDEFINITION2 = 28;
    private static final int MATCHINGTESTDEFINITION3 = 29;
    private static final int MATCHINGTESTDEFINITION4 = 30;
    private static final int MATCHINGTESTDEFINITION5 = 31;
    private static final int MATCHINGTESTTERM1 = 32;
    private static final int MATCHINGTESTTERM2 = 33;
    private static final int MATCHINGTESTTERM3 = 34;
    private static final int MATCHINGTESTTERM4 = 35;
    private static final int MATCHINGTESTTERM5 = 36;
    private static final int RESETMATCHING = 37;

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

    //assigned a value 1 through 4. holds a value for the correct answer choice
    //1 represents A, 2 represents B, 3 represents C, 4 represents D
    private int correctMultipleChoice1 = (int)(Math.random()*4 + 1);
    private int correctMultipleChoice2 = (int)(Math.random()*4 + 1);
    private int correctMultipleChoice3 = (int)(Math.random()*4 + 1);
    private int correctMultipleChoice4 = (int)(Math.random()*4 + 1);
    private int correctMultipleChoice5 = (int)(Math.random()*4 + 1);

    int[] randomAnswersMCQ1 = new int[4];
    int[] randomAnswersMCQ2 = new int[4];
    int[] randomAnswersMCQ3 = new int[4];
    int[] randomAnswersMCQ4 = new int[4];
    int[] randomAnswersMCQ5 = new int[4];

    private int randomAnswerA;
    private int randomAnswerB;
    private int randomAnswerC;
    private int randomAnswerD;

    private boolean randomAnswersSelectedMCQ1 = false;
    private boolean randomAnswersSelectedMCQ2 = false;
    private boolean randomAnswersSelectedMCQ3 = false;
    private boolean randomAnswersSelectedMCQ4 = false;
    private boolean randomAnswersSelectedMCQ5 = false;

    //NEED TO MAKE BUTTONS OR USE LEARN BUTTONS FOR THE MULTIPLE ANSWER CHOICES. buttons will set value of these ints
    private int chosenAnswerMultipleChoice1 = 0;
    private int chosenAnswerMultipleChoice2 = 0;
    private int chosenAnswerMultipleChoice3 = 0;
    private int chosenAnswerMultipleChoice4 = 0;
    private int chosenAnswerMultipleChoice5 = 0;

    private String currentDeck = "";

    //matching
    private int[][] drawLinesHere = new int[4][5]; //used for drawing lines from term button to def button for matching
    //beginning x
    //beginning y
    //ending x
    //ending y
    private int numberOfLinesDrawn = 0;
    private boolean termClicked = false;
    private int termButtonClicked = 0;
    private boolean[] termButtonsClicked = new boolean[5];
    //termButton1    termButton2
    //[    11
    private int[] userMatched = new int[5];
    //def button [1, 2, 3, 4, 5]
    //term button that has been matched with corresponding def button

    //type your own answer
    JTextField typeAnswer16 = new JTextField(1000);
    JTextField typeAnswer17 = new JTextField(1000);
    JTextField typeAnswer18 = new JTextField(1000);
    JTextField typeAnswer19 = new JTextField(1000);
    JTextField typeAnswer20 = new JTextField(1000);

    private String typedAnswer1;
    private String typedAnswer2;
    private String typedAnswer3;
    private String typedAnswer4;
    private String typedAnswer5;

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
    private String quote;

    /** 0-arg constructor */
    public MainPanel() {
        //creates database
        data = new Database();

        quote = getQuote();

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

        Shape r27 = new Rectangle(320, 350, 250, 65);
        ImageIcon mcqAnswerButton1A = new ImageIcon("resources/images/testMcqButton1.png");
        ImageIcon mcqAnswerButton2A = new ImageIcon("resources/images/testMcqButton2.png");
        buttons[MCQATESTBUTTON] = new Button(r27, "testmcqA", mcqAnswerButton1A, mcqAnswerButton2A);

        Shape r28 = new Rectangle(580, 350, 250, 65);
        ImageIcon mcqAnswerButton1B = new ImageIcon("resources/images/testMcqButton1.png");
        ImageIcon mcqAnswerButton2B = new ImageIcon("resources/images/testMcqButton2.png");
        buttons[MCQBTESTBUTTON] = new Button(r28, "testmcqB", mcqAnswerButton1B, mcqAnswerButton2B);

        Shape r29 = new Rectangle(320, 425, 250, 65);
        ImageIcon mcqAnswerButton1C = new ImageIcon("resources/images/testMcqButton1.png");
        ImageIcon mcqAnswerButton2C = new ImageIcon("resources/images/testMcqButton2.png");
        buttons[MCQCTESTBUTTON] = new Button(r29, "testmcqC", mcqAnswerButton1C, mcqAnswerButton2C);

        Shape r30 = new Rectangle(580, 425, 250, 65);
        ImageIcon mcqAnswerButton1D = new ImageIcon("resources/images/testMcqButton1.png");
        ImageIcon mcqAnswerButton2D = new ImageIcon("resources/images/testMcqButton2.png");
        buttons[MCQDTESTBUTTON] = new Button(r30, "testmcqD", mcqAnswerButton1D, mcqAnswerButton2D);

        Shape r31 = new Rectangle(300, 120, 250, 65);
        ImageIcon matchingtestdef1 = new ImageIcon("resources/images/matchingTestQuestion1.png");
        ImageIcon matchingtestdef2 = new ImageIcon("resources/images/matchingTestQuestion2.png");
        buttons[MATCHINGTESTDEFINITION1] = new Button(r31, "matchingTestDef1", matchingtestdef1, matchingtestdef2);

        Shape r32 = new Rectangle(300, 185, 250, 65);
        buttons[MATCHINGTESTDEFINITION2] = new Button(r32, "matchingTestDef2", matchingtestdef1, matchingtestdef2);

        Shape r33 = new Rectangle(300, 250, 250, 65);
        buttons[MATCHINGTESTDEFINITION3] = new Button(r33, "matchingTestDef3", matchingtestdef1, matchingtestdef2);

        Shape r34 = new Rectangle(300, 315, 250, 65);
        buttons[MATCHINGTESTDEFINITION4] = new Button(r34, "matchingTestDef4", matchingtestdef1, matchingtestdef2);

        Shape r35 = new Rectangle(300, 380, 250, 65);
        buttons[MATCHINGTESTDEFINITION5] = new Button(r35, "matchingTestDef5", matchingtestdef1, matchingtestdef2);

        Shape r36 = new Rectangle(610, 120, 250, 65);
        ImageIcon matchingtestterm1 = new ImageIcon("resources/images/matchingTestQuestion1.png");
        ImageIcon matchingtestterm2 = new ImageIcon("resources/images/matchingTestQuestion2.png");
        buttons[MATCHINGTESTTERM1] = new Button(r36, "matchingTestTerm1", matchingtestterm1, matchingtestterm2);

        Shape r37 = new Rectangle(610, 185, 250, 65);
        buttons[MATCHINGTESTTERM2] = new Button(r37, "matchingTestTerm2", matchingtestterm1, matchingtestterm2);

        Shape r38 = new Rectangle(610, 250, 250, 65);
        buttons[MATCHINGTESTTERM3] = new Button(r38, "matchingTestTerm3", matchingtestterm1, matchingtestterm2);

        Shape r39 = new Rectangle(610, 315, 250, 65);
        buttons[MATCHINGTESTTERM4] = new Button(r39, "matchingTestTerm4", matchingtestterm1, matchingtestterm2);

        Shape r40 = new Rectangle(610, 380, 250, 65);
        buttons[MATCHINGTESTTERM5] = new Button(r40, "matchingTestTerm5", matchingtestterm1, matchingtestterm2);

        Shape r41 = new Rectangle(523, 445, 112, 53);
        ImageIcon resetMatching1 = new ImageIcon("resources/images/resetMatching1.png");
        ImageIcon resetMatching2 = new ImageIcon("resources/images/resetMatching2.png");
        buttons[RESETMATCHING] = new Button(r41, "resetMatching", resetMatching1, resetMatching2);

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

            //Activates Menu Buttons
            for(int i = 4; i <= 8; i++) {
                Button b = buttons[i];
                b.drawButton(g);
                b.setEnabled(true);
                b.setVisible(true);
            }

            if(homeScreenDisplay)
            {
                g.drawImage(testletCharacter.getImage(), leftBarSize+30, topBarHeight+30, 313, 410, null);
                g.drawImage(testletCharacterSpeechBubble.getImage(), leftBarSize+30+230, topBarHeight+20, 300, 130, null);
                g.setColor(Color.BLACK);
                g.drawString(quote, leftBarSize+30, TestletDriver.HEIGHT - 40);
            }

            if(gravityStartDisplay)
            {
                showButton(buttons[GRAVITYSTARTGAME], g);

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
                hideButton(buttons[GRAVITYSTARTGAME]);
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
                buttons[TRYAGAIN].setVisible(false);
                buttons[TRYAGAIN].setEnabled(false);
                //activate test buttons
                //buttons[NEXTTESTQUESTION].drawButton(g);
                //buttons[NEXTTESTQUESTION].setEnabled(true);
                buttons[PREVIOUSTESTQUESTION].drawButton(g);
                buttons[PREVIOUSTESTQUESTION].setEnabled(true);


                if(deck == null)
                {
                    g.setColor(Color.RED);
                    g.setFont(new Font("Helvetica", Font.PLAIN, 30));
                    g.drawString("Select a deck!", 490, 260);
                    nullDeckDuringTest = true;
                }
                if(deck != null) {
                    if(nullDeckDuringTest == true)
                    {
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

                        nullDeckDuringTest = false;
                    }
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

                questionsAnsweredBubbles(g);

                if(deck != null && deck.size()>0) { //&& deck.size() >= 20 NEED TO ADD THIS BACK WHEN DONE TESTING!!!!
                    //true/false questions
                    if (currentQuestion == 1 || currentQuestion == 2 || currentQuestion == 3 || currentQuestion == 4 || currentQuestion == 5)
                    {
                        showTermAndDefinition(g);

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
                        buttons[MCQATESTBUTTON].drawButton(g);
                        buttons[MCQATESTBUTTON].setEnabled(true);
                        buttons[MCQBTESTBUTTON].drawButton(g);
                        buttons[MCQBTESTBUTTON].setEnabled(true);
                        buttons[MCQCTESTBUTTON].drawButton(g);
                        buttons[MCQCTESTBUTTON].setEnabled(true);
                        buttons[MCQDTESTBUTTON].drawButton(g);
                        buttons[MCQDTESTBUTTON].setEnabled(true);

                        drawAnswersForMCQ(g);
                    }
                    //matching questions
                    else if(currentQuestion == 11 || currentQuestion == 12 || currentQuestion == 13 || currentQuestion == 14 || currentQuestion == 15)
                    {
                        buttons[RESETMATCHING].drawButton(g);
                        if(testQuestions.size() >= 11) {
                            //need to add if size of testQuestions is equal to or greater than 13. else, this question will be type your own answer
                            currentQuestion = 11;
                            buttons[MATCHINGTESTDEFINITION1].drawButton(g);
                            buttons[MATCHINGTESTTERM1].setEnabled(true);
                            buttons[MATCHINGTESTTERM1].drawButton(g);
                            g.setFont(new Font("Helvetica", Font.PLAIN, 12));
                            int yValue1stDef = topBarHeight + 90; //increase by 23 each time
                            ArrayList<String> lines1 = addLinesToString(32, testQuestions.get(10).getDef());
                            if(lines1.size() > 4) {
                                for (int i = 0; i < 4; i++) { // for (int i = 0; i < lines1.size(); i++) {
                                    g.drawString(lines1.get(i), leftBarSize + 140, yValue1stDef);
                                    yValue1stDef += 13;
                                }
                            }
                            else {
                                for (int i = 0; i < lines1.size(); i++) { // for (int i = 0; i < lines1.size(); i++) {
                                    g.drawString(lines1.get(i), leftBarSize + 140, yValue1stDef);
                                    yValue1stDef += 13;
                                }
                            }
                            yValue1stDef = topBarHeight + 90; //increase by 23 each time
                            ArrayList<String> linest1 = addLinesToString(32, testQuestions.get(correctMatching[0]-1).getTerm());
                            if(linest1.size() > 4) {
                                for (int i = 0; i < 4; i++) { // for (int i = 0; i < lines1.size(); i++) {
                                    g.drawString(linest1.get(i), leftBarSize + 430, yValue1stDef);
                                    yValue1stDef += 13;
                                }
                            }
                            else {
                                for (int i = 0; i < linest1.size(); i++) { // for (int i = 0; i < lines1.size(); i++) {
                                    g.drawString(linest1.get(i), leftBarSize + 430, yValue1stDef);
                                    yValue1stDef += 13;
                                }
                            }

                            //g.drawString(testQuestions.get(10).getDef(), leftBarSize + 40, topBarHeight+ 100); //current question is 11
                        }
                        if(testQuestions.size() >= 12) {
                            currentQuestion = 12;
                            buttons[MATCHINGTESTDEFINITION2].drawButton(g);
                            buttons[MATCHINGTESTTERM2].setEnabled(true);
                            buttons[MATCHINGTESTTERM2].drawButton(g);
                            int yValue2ndDef = topBarHeight + 153; //increase by 23 each time
                            ArrayList<String> lines2 = addLinesToString(32, testQuestions.get(11).getDef());
                            if(lines2.size() > 4) {
                                for (int i = 0; i < 4; i++) { //for (int i = 0; i < lines2.size(); i++) {
                                    g.drawString(lines2.get(i), leftBarSize + 140, yValue2ndDef);
                                    yValue2ndDef += 13;
                                }
                            }
                            else {
                                for (int i = 0; i < lines2.size(); i++) { //for (int i = 0; i < lines2.size(); i++) {
                                    g.drawString(lines2.get(i), leftBarSize + 140, yValue2ndDef);
                                    yValue2ndDef += 13;
                                }
                            }
                            //g.drawString(testQuestions.get(11).getDef(), leftBarSize + 40, topBarHeight+ 100); //current question is 12
                            yValue2ndDef = topBarHeight + 153; //increase by 23 each time
                            ArrayList<String> linest2 = addLinesToString(32, testQuestions.get(correctMatching[1]-1).getTerm());
                            if(linest2.size() > 4) {
                                for (int i = 0; i < 4; i++) { // for (int i = 0; i < lines1.size(); i++) {
                                    g.drawString(linest2.get(i), leftBarSize + 430, yValue2ndDef);
                                    yValue2ndDef += 13;
                                }
                            }
                            else {
                                for (int i = 0; i < linest2.size(); i++) { // for (int i = 0; i < lines1.size(); i++) {
                                    g.drawString(linest2.get(i), leftBarSize + 430, yValue2ndDef);
                                    yValue2ndDef += 13;
                                }
                            }
                        }
                        if(testQuestions.size() >= 13) {
                            currentQuestion = 13;
                            buttons[MATCHINGTESTDEFINITION3].drawButton(g);
                            buttons[MATCHINGTESTTERM3].setEnabled(true);
                            buttons[MATCHINGTESTTERM3].drawButton(g);
                            int yValue3rdDef = topBarHeight + 219; //increase by 23 each time
                            ArrayList<String> lines3 = addLinesToString(32, testQuestions.get(12).getDef());
                            if(lines3.size() > 4) {
                                for (int i = 0; i < 4; i++) { // for (int i = 0; i < lines3.size(); i++) {
                                    g.drawString(lines3.get(i), leftBarSize + 140, yValue3rdDef);
                                    yValue3rdDef += 13;
                                }
                            }
                            else {
                                for (int i = 0; i < lines3.size(); i++) { // for (int i = 0; i < lines3.size(); i++) {
                                    g.drawString(lines3.get(i), leftBarSize + 140, yValue3rdDef);
                                    yValue3rdDef += 13;
                                }
                            }
                            //g.drawString(testQuestions.get(12).getDef(), leftBarSize + 40, topBarHeight+ 100); //current question is 13
                            yValue3rdDef = topBarHeight + 219; //increase by 23 each time
                            ArrayList<String> linest3 = addLinesToString(32, testQuestions.get(correctMatching[2]-1).getTerm());
                            if(linest3.size() > 4) {
                                for (int i = 0; i < 4; i++) { // for (int i = 0; i < lines1.size(); i++) {
                                    g.drawString(linest3.get(i), leftBarSize + 430, yValue3rdDef);
                                    yValue3rdDef += 13;
                                }
                            }
                            else {
                                for (int i = 0; i < linest3.size(); i++) { // for (int i = 0; i < lines1.size(); i++) {
                                    g.drawString(linest3.get(i), leftBarSize + 430, yValue3rdDef);
                                    yValue3rdDef += 13;
                                }
                            }
                        }
                        if(testQuestions.size() >= 14) {
                            currentQuestion = 14;
                            buttons[MATCHINGTESTDEFINITION4].drawButton(g);
                            buttons[MATCHINGTESTTERM4].setEnabled(true);
                            buttons[MATCHINGTESTTERM4].drawButton(g);
                            int yValue4thDef = topBarHeight + 286; //increase by 23 each time
                            ArrayList<String> lines4 = addLinesToString(32, testQuestions.get(13).getDef());
                            if(lines4.size() > 4) {
                                for (int i = 0; i < 4; i++) { //for (int i = 0; i < lines4.size(); i++) {
                                    g.drawString(lines4.get(i), leftBarSize + 140, yValue4thDef);
                                    yValue4thDef += 13;
                                }
                            }
                            else {
                                for (int i = 0; i < lines4.size(); i++) { //for (int i = 0; i < lines4.size(); i++) {
                                    g.drawString(lines4.get(i), leftBarSize + 140, yValue4thDef);
                                    yValue4thDef += 13;
                                }
                            }
                            //g.drawString(testQuestions.get(13).getDef(), leftBarSize + 40, topBarHeight+ 100); //current question is 14
                            yValue4thDef = topBarHeight + 286; //increase by 23 each time
                            ArrayList<String> linest4 = addLinesToString(32, testQuestions.get(correctMatching[3]-1).getTerm());
                            if(linest4.size() > 4) {
                                for (int i = 0; i < 4; i++) { // for (int i = 0; i < lines1.size(); i++) {
                                    g.drawString(linest4.get(i), leftBarSize + 430, yValue4thDef);
                                    yValue4thDef += 13;
                                }
                            }
                            else {
                                for (int i = 0; i < linest4.size(); i++) { // for (int i = 0; i < lines1.size(); i++) {
                                    g.drawString(linest4.get(i), leftBarSize + 430, yValue4thDef);
                                    yValue4thDef += 13;
                                }
                            }
                        }
                        if(testQuestions.size() >= 15) {
                            currentQuestion = 15;
                            buttons[MATCHINGTESTDEFINITION5].drawButton(g);
                            buttons[MATCHINGTESTTERM5].setEnabled(true);
                            buttons[MATCHINGTESTTERM5].drawButton(g);
                            int yValue5thDef = topBarHeight + 350; //increase by 23 each time
                            ArrayList<String> lines5 = addLinesToString(32, testQuestions.get(14).getDef());
                            if(lines5.size() > 4) {
                                for (int i = 0; i < 4; i++) { //for (int i = 0; i < lines5.size(); i++) {
                                    g.drawString(lines5.get(i), leftBarSize + 140, yValue5thDef);
                                    yValue5thDef += 13;
                                }
                            }
                            else
                            {
                                for (int i = 0; i < lines5.size(); i++) { //for (int i = 0; i < lines5.size(); i++) {
                                    g.drawString(lines5.get(i), leftBarSize + 140, yValue5thDef);
                                    yValue5thDef += 13;
                                }
                            }
                            //g.drawString(testQuestions.get(14).getDef(), leftBarSize + 40, topBarHeight+ 100); //current question is 15
                            yValue5thDef = topBarHeight + 350; //increase by 23 each time
                            ArrayList<String> linest5 = addLinesToString(32, testQuestions.get(correctMatching[4]-1).getTerm());
                            if(linest5.size() > 4) {
                                for (int i = 0; i < 4; i++) { // for (int i = 0; i < lines1.size(); i++) {
                                    g.drawString(linest5.get(i), leftBarSize + 430, yValue5thDef);
                                    yValue5thDef += 13;
                                }
                            }
                            else {
                                for (int i = 0; i < linest5.size(); i++) { // for (int i = 0; i < lines1.size(); i++) {
                                    g.drawString(linest5.get(i), leftBarSize + 430, yValue5thDef);
                                    yValue5thDef += 13;
                                }
                            }
                        }

                        if(termClicked == true && termMouseX != -1 && termMouseY != -1)
                        g.drawLine(termMouseX, termMouseY, mouseX, mouseY);

                        if(termClicked == true)
                        {
                            buttons[MATCHINGTESTTERM1].setEnabled(false);
                            buttons[MATCHINGTESTTERM2].setEnabled(false);
                            buttons[MATCHINGTESTTERM3].setEnabled(false);
                            buttons[MATCHINGTESTTERM4].setEnabled(false);
                            buttons[MATCHINGTESTTERM5].setEnabled(false);
                            buttons[MATCHINGTESTDEFINITION1].setEnabled(true);
                            buttons[MATCHINGTESTDEFINITION2].setEnabled(true);
                            buttons[MATCHINGTESTDEFINITION3].setEnabled(true);
                            buttons[MATCHINGTESTDEFINITION4].setEnabled(true);
                            buttons[MATCHINGTESTDEFINITION5].setEnabled(true);
                        }
                        else
                        {
                            buttons[MATCHINGTESTTERM1].setEnabled(true);
                            buttons[MATCHINGTESTTERM2].setEnabled(true);
                            buttons[MATCHINGTESTTERM3].setEnabled(true);
                            buttons[MATCHINGTESTTERM4].setEnabled(true);
                            buttons[MATCHINGTESTTERM5].setEnabled(true);
                            buttons[MATCHINGTESTDEFINITION1].setEnabled(false);
                            buttons[MATCHINGTESTDEFINITION2].setEnabled(false);
                            buttons[MATCHINGTESTDEFINITION3].setEnabled(false);
                            buttons[MATCHINGTESTDEFINITION4].setEnabled(false);
                            buttons[MATCHINGTESTDEFINITION5].setEnabled(false);
                        }

                        for(int i = 0; i<userMatched.length; i++) //we have already used this term or definition. We should not be able to use it again
                        {
                            if(termButtonsClicked[0] == true)
                                buttons[MATCHINGTESTTERM1].setEnabled(false);
                            if(termButtonsClicked[1] == true)
                                buttons[MATCHINGTESTTERM2].setEnabled(false);
                            if(termButtonsClicked[2] == true)
                                buttons[MATCHINGTESTTERM3].setEnabled(false);
                            if(termButtonsClicked[3] == true)
                                buttons[MATCHINGTESTTERM4].setEnabled(false);
                            if(termButtonsClicked[4] == true)
                                buttons[MATCHINGTESTTERM5].setEnabled(false);
                            if(i == 0 && userMatched[i] > 0) //def button 1
                                buttons[MATCHINGTESTDEFINITION1].setEnabled(false);
                            if(i == 1 && userMatched[i] > 0) //def button 2
                                buttons[MATCHINGTESTDEFINITION2].setEnabled(false);
                            if(i == 2 && userMatched[i] > 0) //def button 3
                                buttons[MATCHINGTESTDEFINITION3].setEnabled(false);
                            if(i == 3 && userMatched[i] > 0) //def button 4
                                buttons[MATCHINGTESTDEFINITION4].setEnabled(false);
                            if(i == 4 && userMatched[i] > 0) //def button 5
                                buttons[MATCHINGTESTDEFINITION5].setEnabled(false);
                        }

                        for(int j = 0; j<drawLinesHere[0].length; j++) {
                            if (drawLinesHere[3][j] != -1) {
                                g.drawLine(drawLinesHere[0][j], drawLinesHere[1][j], drawLinesHere[2][j], drawLinesHere[3][j]);
                            }
                        }

                        if(numberOfLinesDrawn >= 1)
                        {
                          question11Answered = true;
                        }
                        if(numberOfLinesDrawn >=2)
                        {
                            question12Answered = true;
                        }
                        if(numberOfLinesDrawn >=3)
                        {
                            question13Answered = true;
                        }
                        if(numberOfLinesDrawn >=4)
                        {
                            question14Answered = true;
                        }
                        if(numberOfLinesDrawn == 5)
                        {
                            question15Answered = true;
                        }


                    }
                    //type your answer questions
                    else if(currentQuestion == 16 || currentQuestion == 17 || currentQuestion == 18 || currentQuestion == 19 || currentQuestion == 20)
                    {
                        drawAnswersForTypeYourAnswerQuestions(g);

                        typedAnswer1 = typeAnswer16.getText();
                        if(typedAnswer1 == null || typedAnswer1.trim().equals("") || typedAnswer1.equals(" "))
                            question16Answered = false;
                        else
                            question16Answered = true;

                        typedAnswer2 = typeAnswer17.getText();
                        if(typedAnswer2 == null || typedAnswer2.trim().equals("") || typedAnswer2.equals(" "))
                            question17Answered = false;
                        else
                            question17Answered = true;

                        typedAnswer3 = typeAnswer18.getText();
                        if(typedAnswer3 == null || typedAnswer3.trim().equals("") || typedAnswer3.equals(" "))
                            question18Answered = false;
                        else
                            question18Answered = true;

                        typedAnswer4 = typeAnswer19.getText();
                        if(typedAnswer4 == null || typedAnswer4.trim().equals("") || typedAnswer4.equals(" "))
                            question19Answered = false;
                        else
                            question19Answered = true;

                        typedAnswer5 = typeAnswer20.getText();
                        if(typedAnswer5 == null || typedAnswer5.trim().equals("") || typedAnswer5.equals(" "))
                            question20Answered = false;
                        else
                            question20Answered = true;
                    }
                }
            }

            if(testSubmittedDisplay == true)
            {
                g.setColor(Color.BLACK);
                g.setFont(new Font("Helvetica", Font.BOLD, 22));
                g.drawString("Score:", 410, 220);
                g.setFont(new Font("Helvetica", Font.PLAIN, 22));
                g.drawString("" + getTestScore() + "/" + testQuestions.size(), 410, 245);
                //testing purposes
                g.drawString("" + correctMatching[0], 410, 300);
                g.drawString("" + correctMatching[1], 440, 300);
                g.drawString("" + correctMatching[2], 470, 300);
                g.drawString("" + correctMatching[3], 500, 300);
                g.drawString("" + correctMatching[4], 530, 300);

                g.drawString("" + userMatched[0], 410, 320);
                g.drawString("" + userMatched[1], 440, 320);
                g.drawString("" + userMatched[2], 470, 320);
                g.drawString("" + userMatched[3], 500, 320);
                g.drawString("" + userMatched[4], 530, 320);
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
                buttons[PREVIOUSCARD].setEnabled(false);
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
                    currentDeckName = deck.getName();
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

                if(deck!= null && !currentDeckName.equals(deck.getName()))
                {
                    currentDeckName = deck.getName();
                    //reset everything. We have a new deck!!

                    learnCardNumber = 1;

                    makeLearnQueue(); //make new queue with new deck terms and definitions

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

    /** Retrieves Random Quote from Zen Quotes API
     *
     * @return random quote with author
     */
    private String getQuote() {
        HttpResponse<JsonNode> response;

        try {
            response = Unirest.get("https://zenquotes.io/api/random/").asJson();
        } catch (Exception e) {
            System.out.println("Failed to Access Webster-Merriam");
            return null;
        }

        String JSON = response.getBody().toString();
        //finds array of definitions
        JSONArray quote = JsonPath.read(JSON, "$..q");
        JSONArray author = JsonPath.read(JSON, "$..a");
        //if not found, return;
        if(quote.size() == 0 || author.size() == 0) {
            return null;
        }
        return "\"" + quote.get(0).toString() + "\" - " + author.get(0).toString();
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

    /** Draws, enables, and shows a given button.
     *
     * @param b button to be enabled
     * @param g graphics passthrough
     */
    public void showButton(Button b, Graphics g) {
        b.drawButton(g);
        b.setEnabled(true);
        b.setVisible(true);
    }

    /** Disables and hides a button.
     *
     * @param b button to be enabled
     */
    public void hideButton(Button b) {
        b.setEnabled(false);
        b.setVisible(false);
    }

    public void randomizeMatchingTerms(int numberOfMatchingQuestions)
    {
        int firstIndex = 0;
        int secondIndex = 0;

        int current;

        for(int j = 0; j<50; j++) //mix up the flashcards of copyOfDeck for randomness!
        {
            firstIndex = (int)(Math.random()*(numberOfMatchingQuestions));
            secondIndex = (int)(Math.random()*(numberOfMatchingQuestions));
            current = correctMatching[firstIndex];
            correctMatching[firstIndex] = correctMatching[secondIndex];
            correctMatching[secondIndex] = current;
        }

//        for(int h = 0; h)
//        {
//            termButtonWithQuestionIndex = correctMatching;
//        }
    }

    /** changes bubbles to show the user which questions they have answered
     *
     */
    public void questionsAnsweredBubbles(Graphics g)
    {
        //shows whether the user has answered question 1 or not
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

        //shows whether the user has answered question 2 or not
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
            g.drawString("7", leftBarSize + 232, topBarHeight + 55);
        }
        else if(deck != null && deck.size()>= 7) {
            g.drawImage(answerBubble1.getImage(), leftBarSize + 220, topBarHeight + 35, 30, 30, null);
            g.setFont(new Font("Helvetica", Font.BOLD, 15));
            g.drawString("7", leftBarSize + 232, topBarHeight + 55);
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

        if(question15Answered == true && deck != null && deck.size()>= 15) {
            g.drawImage(answerBubble2.getImage(), leftBarSize + 500, topBarHeight + 35, 30, 30, null);
            g.setFont(new Font("Helvetica", Font.BOLD, 15));
            g.drawString("15", leftBarSize + 507, topBarHeight + 55);
        }
        else if(deck != null && deck.size()>= 15) {
            g.drawImage(answerBubble1.getImage(), leftBarSize + 500, topBarHeight + 35, 30, 30, null);
            g.setFont(new Font("Helvetica", Font.BOLD, 15));
            g.drawString("15", leftBarSize + 507, topBarHeight + 55);
        }

        if(question16Answered == true && deck != null && deck.size()>= 16) {
            g.drawImage(answerBubble2.getImage(), leftBarSize + 535, topBarHeight + 35, 30, 30, null);
            g.setFont(new Font("Helvetica", Font.BOLD, 15));
            g.drawString("16", leftBarSize + 541, topBarHeight + 55);
        }
        else if(deck != null && deck.size()>= 16) {
            g.drawImage(answerBubble1.getImage(), leftBarSize + 535, topBarHeight + 35, 30, 30, null);
            g.setFont(new Font("Helvetica", Font.BOLD, 15));
            g.drawString("16", leftBarSize + 541, topBarHeight + 55);
        }

        if(question17Answered == true && deck != null && deck.size()>= 17) {
            g.drawImage(answerBubble2.getImage(), leftBarSize + 570, topBarHeight + 35, 30, 30, null);
            g.setFont(new Font("Helvetica", Font.BOLD, 15));
            g.drawString("17", leftBarSize + 577, topBarHeight + 55);
        }
        else if(deck != null && deck.size()>= 17) {
            g.drawImage(answerBubble1.getImage(), leftBarSize + 570, topBarHeight + 35, 30, 30, null);
            g.setFont(new Font("Helvetica", Font.BOLD, 15));
            g.drawString("17", leftBarSize + 577, topBarHeight + 55);
        }

        if(question18Answered == true && deck != null && deck.size()>= 18) {
            g.drawImage(answerBubble2.getImage(), leftBarSize + 605, topBarHeight + 35, 30, 30, null);
            g.setFont(new Font("Helvetica", Font.BOLD, 15));
            g.drawString("18", leftBarSize + 612, topBarHeight + 55);
        }
        else if(deck != null && deck.size()>= 18) {
            g.drawImage(answerBubble1.getImage(), leftBarSize + 605, topBarHeight + 35, 30, 30, null);
            g.setFont(new Font("Helvetica", Font.BOLD, 15));
            g.drawString("18", leftBarSize + 612, topBarHeight + 55);
        }

        if(question19Answered == true && deck != null && deck.size()>= 19) {
            g.drawImage(answerBubble2.getImage(), leftBarSize + 640, topBarHeight + 35, 30, 30, null);
            g.setFont(new Font("Helvetica", Font.BOLD, 15));
            g.drawString("19", leftBarSize + 647, topBarHeight + 55);
        }
        else if(deck != null && deck.size()>= 19) {
            g.drawImage(answerBubble1.getImage(), leftBarSize + 640, topBarHeight + 35, 30, 30, null);
            g.setFont(new Font("Helvetica", Font.BOLD, 15));
            g.drawString("19", leftBarSize + 647, topBarHeight + 55);
        }

        if(question20Answered == true && deck != null && deck.size()>= 20) {
            g.drawImage(answerBubble2.getImage(), leftBarSize + 675, topBarHeight + 35, 30, 30, null);
            g.setFont(new Font("Helvetica", Font.BOLD, 15));
            g.drawString("20", leftBarSize + 682, topBarHeight + 55);
        }
        else if(deck != null && deck.size()>= 20) {
            g.drawImage(answerBubble1.getImage(), leftBarSize + 675, topBarHeight + 35, 30, 30, null);
            g.setFont(new Font("Helvetica", Font.BOLD, 15));
            g.drawString("20", leftBarSize + 682, topBarHeight + 55);
        }
    }

    public void showTermAndDefinition(Graphics g)
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

        if(currentQuestion == 1 && trueOrFalse1 == 0) { //show true definition
            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(currentQuestion - 1).getDef());
            for (int i = 0; i < linesforDefinition.size(); i++) {
                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                yVal += 30;
            }
        }
        else if(currentQuestion == 1 && trueOrFalse1 == 1) { //show false definition
            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(trueFalseWrongAnswer1).getDef());
            for (int i = 0; i < linesforDefinition.size(); i++) {
                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                yVal += 30;
            }
        }
        if(currentQuestion == 2 && trueOrFalse2 == 0) { //show true definition
            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(currentQuestion - 1).getDef());
            for (int i = 0; i < linesforDefinition.size(); i++) {
                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                yVal += 30;
            }
        }
        else if(currentQuestion == 2 && trueOrFalse2 == 1) { //show false definition
            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(trueFalseWrongAnswer2).getDef());
            for (int i = 0; i < linesforDefinition.size(); i++) {
                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                yVal += 30;
            }
        }
        if(currentQuestion == 3 && trueOrFalse3 == 0) { //show true definition
            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(currentQuestion - 1).getDef());
            for (int i = 0; i < linesforDefinition.size(); i++) {
                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                yVal += 30;
            }
        }
        else if(currentQuestion == 3 && trueOrFalse3 == 1) { //show false definition
            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(trueFalseWrongAnswer3).getDef());
            for (int i = 0; i < linesforDefinition.size(); i++) {
                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                yVal += 30;
            }
        }
        if(currentQuestion == 4 && trueOrFalse4 == 0) { //show true definition
            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(currentQuestion - 1).getDef());
            for (int i = 0; i < linesforDefinition.size(); i++) {
                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                yVal += 30;
            }
        }
        else if(currentQuestion == 4 && trueOrFalse4 == 1){ //show false definition
            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(trueFalseWrongAnswer4).getDef());
            for (int i = 0; i < linesforDefinition.size(); i++) {
                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                yVal += 30;
            }
        }
        if(currentQuestion == 5 && trueOrFalse5 == 0) { //show true definition
            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(currentQuestion - 1).getDef());
            for (int i = 0; i < linesforDefinition.size(); i++) {
                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                yVal += 30;
            }
        }
        else if(currentQuestion == 5 && trueOrFalse5 == 1) { //show false definition
            ArrayList<String> linesforDefinition = addLinesToString(40, testQuestions.get(trueFalseWrongAnswer5).getDef());
            for (int i = 0; i < linesforDefinition.size(); i++) {
                g.drawString(linesforDefinition.get(i), leftBarSize + 380, yVal);
                yVal += 30;
            }
        }
    }

    public void drawAnswersForMCQ(Graphics g)
    {
        int yVal = topBarHeight + 130; //increase by 30 each time

            g.setFont(new Font("Helvetica", Font.BOLD, 25));
            g.drawString("Definition:", leftBarSize + 10, topBarHeight + 100);
            g.setFont(new Font("Helvetica", Font.PLAIN, 22));
            //g.drawString(testQuestions.get(currentQuestion-1).getTerm(), leftBarSize + 10, topBarHeight+ 130);
            ArrayList<String> linesforDefinition = addLinesToString(75, testQuestions.get(currentQuestion - 1).getDef());
            for (int i = 0; i < linesforDefinition.size(); i++) {
                g.drawString(linesforDefinition.get(i), leftBarSize + 10, yVal);
                yVal += 30;
                g.setFont(new Font("Helvetica", Font.PLAIN, 22));

                g.setFont(new Font("Helvetica", Font.BOLD, 25));
                g.drawString("Term:", leftBarSize + 125, topBarHeight + 285);
                g.setFont(new Font("Helvetica", Font.PLAIN, 22));
            }

                if(currentQuestion == 6) {
                    if (correctMultipleChoice1 == 1) {
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 330, 390); //A is correct
                        g.drawString(deck.get(randomAnswersMCQ1[1]).getTerm(), 590, 390); //B
                        g.drawString(deck.get(randomAnswersMCQ1[2]).getTerm(), 330, 465); //C
                        g.drawString(deck.get(randomAnswersMCQ1[3]).getTerm(), 590, 465); //D
                    } else if (correctMultipleChoice1 == 2) {
                        g.drawString(deck.get(randomAnswersMCQ1[0]).getTerm(), 330, 390); //A
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 590, 390); //B is correct
                        g.drawString(deck.get(randomAnswersMCQ1[2]).getTerm(), 330, 465); //C
                        g.drawString(deck.get(randomAnswersMCQ1[3]).getTerm(), 590, 465); //D
                    } else if (correctMultipleChoice1 == 3) {
                        g.drawString(deck.get(randomAnswersMCQ1[0]).getTerm(), 330, 390); //A
                        g.drawString(deck.get(randomAnswersMCQ1[1]).getTerm(), 590, 390); //B
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 330, 465); //C is correct
                        g.drawString(deck.get(randomAnswersMCQ1[3]).getTerm(), 590, 465);
                    } else //if(correctMultipleChoice1 == 4)
                    {
                        g.drawString(deck.get(randomAnswersMCQ1[0]).getTerm(), 330, 390); //A
                        g.drawString(deck.get(randomAnswersMCQ1[1]).getTerm(), 590, 390); //B
                        g.drawString(deck.get(randomAnswersMCQ1[2]).getTerm(), 330, 465); //C
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 590, 465); //D is correct
                    }
                }

                if(currentQuestion == 7) {
                    if (correctMultipleChoice2 == 1) {
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 330, 390); //A is correct
                        g.drawString(deck.get(randomAnswersMCQ2[1]).getTerm(), 590, 390); //B
                        g.drawString(deck.get(randomAnswersMCQ2[2]).getTerm(), 330, 465); //C
                        g.drawString(deck.get(randomAnswersMCQ2[3]).getTerm(), 590, 465); //D
                    } else if (correctMultipleChoice2 == 2) {
                        g.drawString(deck.get(randomAnswersMCQ2[0]).getTerm(), 330, 390); //A
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 590, 390); //B is correct
                        g.drawString(deck.get(randomAnswersMCQ2[2]).getTerm(), 330, 465); //C
                        g.drawString(deck.get(randomAnswersMCQ2[3]).getTerm(), 590, 465); //D
                    } else if (correctMultipleChoice2 == 3) {
                        g.drawString(deck.get(randomAnswersMCQ2[0]).getTerm(), 330, 390); //A
                        g.drawString(deck.get(randomAnswersMCQ2[1]).getTerm(), 590, 390); //B
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 330, 465); //C is correct
                        g.drawString(deck.get(randomAnswersMCQ2[3]).getTerm(), 590, 465); //D
                    } else //if(correctMultipleChoice2 == 4)
                    {
                        g.drawString(deck.get(randomAnswersMCQ2[0]).getTerm(), 330, 390); //A
                        g.drawString(deck.get(randomAnswersMCQ2[1]).getTerm(), 590, 390); //B
                        g.drawString(deck.get(randomAnswersMCQ2[2]).getTerm(), 330, 465); //C
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 590, 465); //D is correct
                    }
                }

                if(currentQuestion == 8) {
                    if (correctMultipleChoice3 == 1) {
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 330, 390); //A is correct
                        g.drawString(deck.get(randomAnswersMCQ3[1]).getTerm(), 590, 390); //B
                        g.drawString(deck.get(randomAnswersMCQ3[2]).getTerm(), 330, 465); //C
                        g.drawString(deck.get(randomAnswersMCQ3[3]).getTerm(), 590, 465); //D
                    } else if (correctMultipleChoice3 == 2) {
                        g.drawString(deck.get(randomAnswersMCQ3[0]).getTerm(), 330, 390);
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 590, 390); //B is correct
                        g.drawString(deck.get(randomAnswersMCQ3[2]).getTerm(), 330, 465);
                        g.drawString(deck.get(randomAnswersMCQ3[3]).getTerm(), 590, 465);
                    } else if (correctMultipleChoice3 == 3) {
                        g.drawString(deck.get(randomAnswersMCQ3[0]).getTerm(), 330, 390);
                        g.drawString(deck.get(randomAnswersMCQ3[1]).getTerm(), 590, 390);
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 330, 465); //C is correct
                        g.drawString(deck.get(randomAnswersMCQ3[3]).getTerm(), 590, 465);
                    } else //if(correctMultipleChoice3 == 4)
                    {
                        g.drawString(deck.get(randomAnswersMCQ3[0]).getTerm(), 330, 390);
                        g.drawString(deck.get(randomAnswersMCQ3[1]).getTerm(), 590, 390);
                        g.drawString(deck.get(randomAnswersMCQ3[2]).getTerm(), 330, 465);
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 590, 465); //D is correct
                    }
                }

                if(currentQuestion == 9) {
                    if (correctMultipleChoice4 == 1) {
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 330, 390); //A is correct
                        g.drawString(deck.get(randomAnswersMCQ4[1]).getTerm(), 590, 390); //B
                        g.drawString(deck.get(randomAnswersMCQ4[2]).getTerm(), 330, 465); //C
                        g.drawString(deck.get(randomAnswersMCQ4[3]).getTerm(), 590, 465); //D
                    } else if (correctMultipleChoice4 == 2) {
                        g.drawString(deck.get(randomAnswersMCQ4[0]).getTerm(), 330, 390);
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 590, 390); //B is correct
                        g.drawString(deck.get(randomAnswersMCQ4[2]).getTerm(), 330, 465);
                        g.drawString(deck.get(randomAnswersMCQ4[3]).getTerm(), 590, 465);
                    } else if (correctMultipleChoice4 == 3) {
                        g.drawString(deck.get(randomAnswersMCQ4[0]).getTerm(), 330, 390);
                        g.drawString(deck.get(randomAnswersMCQ4[1]).getTerm(), 590, 390);
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 330, 465); //C is correct
                        g.drawString(deck.get(randomAnswersMCQ4[3]).getTerm(), 590, 465);
                    } else //if(correctMultipleChoice4 == 4)
                    {
                        g.drawString(deck.get(randomAnswersMCQ4[0]).getTerm(), 330, 390);
                        g.drawString(deck.get(randomAnswersMCQ4[1]).getTerm(), 590, 390);
                        g.drawString(deck.get(randomAnswersMCQ4[2]).getTerm(), 330, 465);
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 590, 465); //D is correct
                    }
                }

                if(currentQuestion == 10) {
                    if (correctMultipleChoice5 == 1) {
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 330, 390); //A is correct
                        g.drawString(deck.get(randomAnswersMCQ5[1]).getTerm(), 590, 390);
                        g.drawString(deck.get(randomAnswersMCQ5[2]).getTerm(), 330, 465);
                        g.drawString(deck.get(randomAnswersMCQ5[3]).getTerm(), 590, 465);
                    } else if (correctMultipleChoice5 == 2) {
                        g.drawString(deck.get(randomAnswersMCQ5[0]).getTerm(), 330, 390);
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 590, 390); //B is correct
                        g.drawString(deck.get(randomAnswersMCQ5[2]).getTerm(), 330, 465);
                        g.drawString(deck.get(randomAnswersMCQ5[3]).getTerm(), 590, 465);
                    } else if (correctMultipleChoice5 == 3) {
                        g.drawString(deck.get(randomAnswersMCQ5[0]).getTerm(), 330, 390);
                        g.drawString(deck.get(randomAnswersMCQ5[1]).getTerm(), 590, 390);
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 330, 465); //C is correct
                        g.drawString(deck.get(randomAnswersMCQ5[3]).getTerm(), 590, 465);
                    } else //if(correctMultipleChoice5 == 4)
                    {
                        g.drawString(deck.get(randomAnswersMCQ5[0]).getTerm(), 330, 390);
                        g.drawString(deck.get(randomAnswersMCQ5[1]).getTerm(), 590, 390);
                        g.drawString(deck.get(randomAnswersMCQ5[2]).getTerm(), 330, 465);
                        g.drawString(testQuestions.get(currentQuestion - 1).getTerm(), 590, 465); //D is correct
                    }
                }
    }

    public void drawAnswersForTypeYourAnswerQuestions(Graphics g) {
        int yVal = topBarHeight + 130; //increase by 30 each time

        g.setFont(new Font("Helvetica", Font.BOLD, 25));
        g.drawString("Definition:", leftBarSize + 10, topBarHeight + 100);
        g.setFont(new Font("Helvetica", Font.PLAIN, 22));
        //g.drawString(testQuestions.get(currentQuestion-1).getTerm(), leftBarSize + 10, topBarHeight+ 130);
        ArrayList<String> linesforDefinition = addLinesToString(75, testQuestions.get(currentQuestion - 1).getDef());
        for (int i = 0; i < linesforDefinition.size(); i++) {
            g.drawString(linesforDefinition.get(i), leftBarSize + 10, yVal);
            yVal += 30;
            g.setFont(new Font("Helvetica", Font.PLAIN, 22));

            g.setFont(new Font("Helvetica", Font.BOLD, 25));
            g.drawString("Term:", leftBarSize + 125, topBarHeight + 285);
            g.setFont(new Font("Helvetica", Font.PLAIN, 22));
        }
       // typeAnswer16.setLocation(400,400);
        //long, int x, int y, int width, int height

      //  typeAnswer16.setFont();
    }

    public int getTestScore()
    {
        int score = 0;
     if(question1Answered && trueOrFalse1 == 0 && trueFalse1 == true)
         score++;
        if(question1Answered && trueOrFalse1 == 1 && trueFalse1 == false)
            score++;
        if(question2Answered && trueOrFalse2 == 0 && trueFalse2 == true)
            score++;
        if(question2Answered && trueOrFalse2 == 1 && trueFalse2 == false)
            score++;
        if(question3Answered && trueOrFalse3 == 0 && trueFalse3 == true)
            score++;
        if(question3Answered && trueOrFalse3 == 1 && trueFalse3 == false)
            score++;
        if(question4Answered && trueOrFalse4 == 0 && trueFalse4 == true)
            score++;
        if(question4Answered && trueOrFalse4 == 1 && trueFalse4 == false)
            score++;
        if(question5Answered && trueOrFalse5 == 0 && trueFalse5 == true)
            score++;
        if(question5Answered && trueOrFalse5 == 1 && trueFalse5 == false)
            score++;

        if(question6Answered && correctMultipleChoice1 == chosenAnswerMultipleChoice1)
            score++;
        if(question7Answered && correctMultipleChoice2 == chosenAnswerMultipleChoice2)
            score++;
        if(question8Answered && correctMultipleChoice3 == chosenAnswerMultipleChoice3)
            score++;
        if(question9Answered && correctMultipleChoice4 == chosenAnswerMultipleChoice4)
            score++;
        if(question10Answered && correctMultipleChoice5 == chosenAnswerMultipleChoice5)
            score++;

//        for(int i = 0; i<userMatched.length; i++) //does not work
//        {
//            if(userMatched[i] == (correctMatching[i]))
//                score++;
//        }
        if(userMatched[0] == 11)
            score++;
        if(userMatched[1] == 12)
            score++;
        if(userMatched[2] == 13)
            score++;
        if(userMatched[3] == 14)
            score++;
        if(userMatched[4] == 15)
            score++;

        if(((typedAnswer1.trim()).toUpperCase()).equals(testQuestions.get(15).getTerm().trim().toUpperCase()))
            score++;
        if(((typedAnswer2.trim()).toUpperCase()).equals(testQuestions.get(16).getTerm().trim().toUpperCase()))
            score++;
        if(((typedAnswer3.trim()).toUpperCase()).equals(testQuestions.get(17).getTerm().trim().toUpperCase()))
            score++;
        if(((typedAnswer4.trim()).toUpperCase()).equals(testQuestions.get(18).getTerm().trim().toUpperCase()))
            score++;
        if(((typedAnswer5.trim()).toUpperCase()).equals(testQuestions.get(19).getTerm().trim().toUpperCase()))
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
        if(testQuestions.size()>0) //get rid of previous testQuestions array
        {
            testQuestions.clear();
        }

        int[] numbers = new int[20];
        for(int h = 0; h<20; h++)
            numbers[h] = -1;
        int newnumber;
        boolean equalsAnotherNumber = false;
        Deck copyOfDeck = new Deck();

        if(deck.size()>20) //chooses random flashcards to use for test and adds them to copyOfDeck
        {
            for(int i = 0; i<numbers.length; i++)
            {
                newnumber = (int)(Math.random()*(deck.size()));
                for(int j = 0; j<numbers.length; j++)
                {
                    while(newnumber == numbers[j]) { //we do not want repeats, assign newnumber a new value
                        newnumber = (int) (Math.random() * (deck.size()));
                    }
                }
                numbers[i] = newnumber;
            }

            for(int k = 0; k<numbers.length; k++)
            {
                copyOfDeck.add(deck.get(numbers[k]));
            }
        }
        else //takes all available flashcards to use for test and adds them to copyOfDeck
        {
            for (int i = 0; i < deck.size(); i++) {
                copyOfDeck.add(deck.get(i));
            }
        }

        int firstIndex = 0;
        int secondIndex = 0;
        Flashcard temp;

        for(int j = 0; j<250; j++) //mix up the flashcards of copyOfDeck for randomness!
        {
            firstIndex = (int)(Math.random()*(copyOfDeck.size()));
            secondIndex = (int)(Math.random()*(copyOfDeck.size()));
            temp = copyOfDeck.get(firstIndex);
            copyOfDeck.set(firstIndex, copyOfDeck.get(secondIndex));
            copyOfDeck.set(secondIndex, temp);
        }

        for(int k = 0; k<copyOfDeck.size(); k++) //testQuestions gets the mixed up flashcards from copyOfDeck
            testQuestions.add(copyOfDeck.get(k));
    }

    public static void makeLearnQueue()
    {
        while(!learnQueue.isEmpty()) //getting rid of old deck
            learnQueue.remove();

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
        mouseX = e.getX();
        mouseY = e.getY();

        if(termClicked == true && termButtonClicked > 0 && buttons[MATCHINGTESTDEFINITION1].getShape().contains(mouseX, mouseY) && buttons[MATCHINGTESTDEFINITION1].isEnabled() && numberOfLinesDrawn <5)
        {
            drawLinesHere[0][numberOfLinesDrawn] = termMouseX;
            drawLinesHere[1][numberOfLinesDrawn] = termMouseY;
            drawLinesHere[2][numberOfLinesDrawn] = mouseX;
            drawLinesHere[3][numberOfLinesDrawn] = mouseY;
            numberOfLinesDrawn++;
            termClicked = false;
            termMouseX = -1;
            termMouseY = -1;
            userMatched[0] = correctMatching[termButtonClicked-1];
            termButtonsClicked[termButtonClicked - 1] = true;
            termButtonClicked = 0;
        }
        if(termClicked == true && termButtonClicked > 0 && buttons[MATCHINGTESTDEFINITION2].getShape().contains(mouseX, mouseY) && buttons[MATCHINGTESTDEFINITION2].isEnabled() && numberOfLinesDrawn <5)
        {
            drawLinesHere[0][numberOfLinesDrawn] = termMouseX;
            drawLinesHere[1][numberOfLinesDrawn] = termMouseY;
            drawLinesHere[2][numberOfLinesDrawn] = mouseX;
            drawLinesHere[3][numberOfLinesDrawn] = mouseY;
            numberOfLinesDrawn++;
            termClicked = false;
            termMouseX = -1;
            termMouseY = -1;
            userMatched[1] = correctMatching[termButtonClicked-1];
            termButtonsClicked[termButtonClicked - 1] = true;
            termButtonClicked = 0;
        }
        if(termClicked == true && termButtonClicked > 0 && buttons[MATCHINGTESTDEFINITION3].getShape().contains(mouseX, mouseY) && buttons[MATCHINGTESTDEFINITION3].isEnabled() && numberOfLinesDrawn <5)
        {
            drawLinesHere[0][numberOfLinesDrawn] = termMouseX;
            drawLinesHere[1][numberOfLinesDrawn] = termMouseY;
            drawLinesHere[2][numberOfLinesDrawn] = mouseX;
            drawLinesHere[3][numberOfLinesDrawn] = mouseY;
            numberOfLinesDrawn++;
            termClicked = false;
            termMouseX = -1;
            termMouseY = -1;
            userMatched[2] = correctMatching[termButtonClicked-1];
            termButtonsClicked[termButtonClicked - 1] = true;
            termButtonClicked = 0;
        }
        if(termClicked == true && termButtonClicked > 0 && buttons[MATCHINGTESTDEFINITION4].getShape().contains(mouseX, mouseY) && buttons[MATCHINGTESTDEFINITION4].isEnabled() && numberOfLinesDrawn <5)
        {
            drawLinesHere[0][numberOfLinesDrawn] = termMouseX;
            drawLinesHere[1][numberOfLinesDrawn] = termMouseY;
            drawLinesHere[2][numberOfLinesDrawn] = mouseX;
            drawLinesHere[3][numberOfLinesDrawn] = mouseY;
            numberOfLinesDrawn++;
            termClicked = false;
            termMouseX = -1;
            termMouseY = -1;
            userMatched[3] = correctMatching[termButtonClicked-1];
            termButtonsClicked[termButtonClicked - 1] = true;
            termButtonClicked = 0;
        }
        if(termClicked == true && termButtonClicked > 0 && buttons[MATCHINGTESTDEFINITION5].getShape().contains(mouseX, mouseY) && buttons[MATCHINGTESTDEFINITION5].isEnabled() && numberOfLinesDrawn <5)
        {
            drawLinesHere[0][numberOfLinesDrawn] = termMouseX;
            drawLinesHere[1][numberOfLinesDrawn] = termMouseY;
            drawLinesHere[2][numberOfLinesDrawn] = mouseX;
            drawLinesHere[3][numberOfLinesDrawn] = mouseY;
            numberOfLinesDrawn++;
            termClicked = false;
            termMouseX = -1;
            termMouseY = -1;
            userMatched[4] = correctMatching[termButtonClicked-1];
            termButtonsClicked[termButtonClicked - 1] = true;
            termButtonClicked = 0;
        }
        if(termClicked == true && !buttons[MATCHINGTESTDEFINITION1].getShape().contains(mouseX, mouseY) && !buttons[MATCHINGTESTDEFINITION2].getShape().contains(mouseX, mouseY) && !buttons[MATCHINGTESTDEFINITION3].getShape().contains(mouseX, mouseY) && !buttons[MATCHINGTESTDEFINITION4].getShape().contains(mouseX, mouseY) && !buttons[MATCHINGTESTDEFINITION5].getShape().contains(mouseX, mouseY))
        {
            termClicked = false;
            termMouseX = -1;
            termMouseY = -1;
            termButtonClicked = 0;
        }

        if(!buttons[MATCHINGTESTTERM1].getShape().contains(mouseX, mouseY) && !buttons[MATCHINGTESTTERM2].getShape().contains(mouseX, mouseY) && !buttons[MATCHINGTESTTERM3].getShape().contains(mouseX, mouseY) && !buttons[MATCHINGTESTTERM4].getShape().contains(mouseX, mouseY) && !buttons[MATCHINGTESTTERM5].getShape().contains(mouseX, mouseY)) {
            termClicked = false;
            termButtonClicked = 0;
        }

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

                   if(b.getTitle().equals("matchingTestTerm1") && b.isEnabled())
                   {
//                       buttons[MATCHINGTESTTERM1].setEnabled(false);
//                       buttons[MATCHINGTESTTERM2].setEnabled(false);
//                       buttons[MATCHINGTESTTERM3].setEnabled(false);
//                       buttons[MATCHINGTESTTERM4].setEnabled(false);
//                       buttons[MATCHINGTESTTERM5].setEnabled(false);

                       termMouseX = mouseX; //use this only for clicking on term buttons
                       termMouseY = mouseY;
                       if(termClicked == true)
                           termClicked = false;
                       else
                           termClicked = true;

//                       termClicked = true;
//                       termMouseX = mouseX; //use this only for clicking on term buttons
//                       termMouseY = mouseY;
                       termButtonClicked = 1;
                   }
                   if(b.getTitle().equals("matchingTestTerm2") && b.isEnabled())
                   {
//                       buttons[MATCHINGTESTTERM1].setEnabled(false);
//                       buttons[MATCHINGTESTTERM2].setEnabled(false);
//                       buttons[MATCHINGTESTTERM3].setEnabled(false);
//                       buttons[MATCHINGTESTTERM4].setEnabled(false);
//                       buttons[MATCHINGTESTTERM5].setEnabled(false);

                       termMouseX = mouseX; //use this only for clicking on term buttons
                       termMouseY = mouseY;
                       if(termClicked == true)
                           termClicked = false;
                       else
                           termClicked = true;

//                       termClicked = true;
//                       termMouseX = mouseX; //use this only for clicking on term buttons
//                       termMouseY = mouseY;
                       termButtonClicked = 2;
                   }
                   if(b.getTitle().equals("matchingTestTerm3") && b.isEnabled())
                   {
                       termMouseX = mouseX; //use this only for clicking on term buttons
                       termMouseY = mouseY;
                       if(termClicked == true)
                           termClicked = false;
                       else
                           termClicked = true;

                       termButtonClicked = 3;
                   }
                   if(b.getTitle().equals("matchingTestTerm4") && b.isEnabled())
                   {
                       termMouseX = mouseX; //use this only for clicking on term buttons
                       termMouseY = mouseY;
                       if(termClicked == true)
                           termClicked = false;
                       else
                           termClicked = true;

                       termButtonClicked = 4;
                   }
                   if(b.getTitle().equals("matchingTestTerm5") && b.isEnabled())
                   {
                       termMouseX = mouseX; //use this only for clicking on term buttons
                       termMouseY = mouseY;
                       if(termClicked == true)
                           termClicked = false;
                       else
                           termClicked = true;

                       termButtonClicked = 5;
                   }
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
                        if(deck!= null)
                        currentDeckName = deck.getName();
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
                       testSubmittedDisplay = false;

                       buttons[MATCHINGTESTDEFINITION1].setEnabled(false);
                       buttons[MATCHINGTESTDEFINITION2].setEnabled(false);
                       buttons[MATCHINGTESTDEFINITION3].setEnabled(false);
                       buttons[MATCHINGTESTDEFINITION4].setEnabled(false);
                       buttons[MATCHINGTESTDEFINITION5].setEnabled(false);

                       for(int i = 0; i<drawLinesHere.length; i++)
                       {
                           for(int j = 0; j<drawLinesHere[0].length; j++)
                           {
                               drawLinesHere[i][j] = -1;
                           }
                       }

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

                       if(testQuestions.size() >= 13) {
                           if(testQuestions.size() == 13)
                                randomizeMatchingTerms(3);
                           else if(testQuestions.size() == 14)
                               randomizeMatchingTerms(4);
                           else //if(testQuestions.size() >= 15)
                               randomizeMatchingTerms(5);
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
                   if(b.getTitle().equals("resetMatching"))
                   {
                       numberOfLinesDrawn = 0;
                       termClicked = false;
                       termButtonClicked = 0;
                       for(int i = 0; i<userMatched.length; i++)
                       {
                           userMatched[i] = 0;
                       }
                       for(int j = 0; j<drawLinesHere.length; j++)
                       {
                           for(int k = 0; k<drawLinesHere[0].length; k++)
                           {
                               drawLinesHere[j][k] = 0;
                           }
                       }
                       question11Answered = false;
                       question12Answered = false;
                       question13Answered = false;
                       question14Answered = false;
                       question15Answered = false;
                       termButtonsClicked[0] = false;
                       termButtonsClicked[1] = false;
                       termButtonsClicked[2] = false;
                       termButtonsClicked[3] = false;
                       termButtonsClicked[4] = false;
                       buttons[MATCHINGTESTTERM1].setEnabled(true);
                       buttons[MATCHINGTESTTERM2].setEnabled(true);
                       buttons[MATCHINGTESTTERM3].setEnabled(true);
                       buttons[MATCHINGTESTTERM4].setEnabled(true);
                       buttons[MATCHINGTESTTERM5].setEnabled(true);
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
                   if(b.getTitle().equals("testmcqA"))
                   {
                       if(currentQuestion == 6) {
                           question6Answered = true;
                           chosenAnswerMultipleChoice1 = 1;
                       }
                       if(currentQuestion == 7){
                           question7Answered = true;
                           chosenAnswerMultipleChoice2 = 1;
                       }
                       if(currentQuestion == 8){
                           question8Answered = true;
                           chosenAnswerMultipleChoice3 = 1;
                       }
                       if(currentQuestion == 9){
                           question9Answered = true;
                           chosenAnswerMultipleChoice4 = 1;
                       }
                       if(currentQuestion == 10){
                           question10Answered = true;
                           chosenAnswerMultipleChoice5 = 1;
                       }
                   }
                   if(b.getTitle().equals("testmcqB"))
                   {
                       if(currentQuestion == 6) {
                           question6Answered = true;
                           chosenAnswerMultipleChoice1 = 2;
                       }
                       if(currentQuestion == 7){
                           question7Answered = true;
                           chosenAnswerMultipleChoice2 = 2;
                       }
                       if(currentQuestion == 8){
                           question8Answered = true;
                           chosenAnswerMultipleChoice3 = 2;
                       }
                       if(currentQuestion == 9){
                           question9Answered = true;
                           chosenAnswerMultipleChoice4 = 2;
                       }
                       if(currentQuestion == 10){
                           question10Answered = true;
                           chosenAnswerMultipleChoice5 = 2;
                       }
                   }
                   if(b.getTitle().equals("testmcqC"))
                   {
                       if(currentQuestion == 6) {
                           question6Answered = true;
                           chosenAnswerMultipleChoice1 = 3;
                       }
                       if(currentQuestion == 7){
                           question7Answered = true;
                           chosenAnswerMultipleChoice2 = 3;
                       }
                       if(currentQuestion == 8){
                           question8Answered = true;
                           chosenAnswerMultipleChoice3 = 3;
                       }
                       if(currentQuestion == 9){
                           question9Answered = true;
                           chosenAnswerMultipleChoice4 = 3;
                       }
                       if(currentQuestion == 10){
                           question10Answered = true;
                           chosenAnswerMultipleChoice5 = 3;
                       }
                   }
                   if(b.getTitle().equals("testmcqD"))
                   {
                       if(currentQuestion == 6) {
                           question6Answered = true;
                           chosenAnswerMultipleChoice1 = 4;
                       }
                       if(currentQuestion == 7){
                           question7Answered = true;
                           chosenAnswerMultipleChoice2 = 4;
                       }
                       if(currentQuestion == 8){
                           question8Answered = true;
                           chosenAnswerMultipleChoice3 = 4;
                       }
                       if(currentQuestion == 9){
                           question9Answered = true;
                           chosenAnswerMultipleChoice4 = 4;
                       }
                       if(currentQuestion == 10){
                           question10Answered = true;
                           chosenAnswerMultipleChoice5 = 4;
                       }
                   }
                   if(b.getTitle().equals("nextTestQuestion"))
                   {
                       if((deck.size()<= 20 && currentQuestion + 1 <= deck.size()) || (deck.size()> 20 && currentQuestion + 1 <= 20))
                    currentQuestion++;

                       if(currentQuestion == 6 && randomAnswersSelectedMCQ1 == false) {
                           if (correctMultipleChoice1 == 1) //A is correct
                           {
                               randomAnswerB = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerB).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerB = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerC = (int) (Math.random() * (deck.size()));
                               while (randomAnswerB == randomAnswerC || (deck.get(randomAnswerC).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerC = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerD = (int) (Math.random() * (deck.size()));
                               while (randomAnswerD == randomAnswerC || randomAnswerD == randomAnswerB || (deck.get(randomAnswerD).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerD = (int) (Math.random() * (deck.size()));
                               }
                           }
                           else if(correctMultipleChoice1 == 2) //B is correct
                           {
                               randomAnswerA = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerA).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerA = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerC = (int) (Math.random() * (deck.size()));
                               while (randomAnswerA == randomAnswerC || (deck.get(randomAnswerC).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerC = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerD = (int) (Math.random() * (deck.size()));
                               while (randomAnswerD == randomAnswerA || randomAnswerD == randomAnswerC || (deck.get(randomAnswerD).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerD = (int) (Math.random() * (deck.size()));
                               }
                           }
                           else if(correctMultipleChoice1 == 3) //C is correct
                           {
                               randomAnswerA = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerA).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerA = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerB = (int) (Math.random() * (deck.size()));
                               while (randomAnswerB == randomAnswerA || (deck.get(randomAnswerB).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerB = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerD = (int) (Math.random() * (deck.size()));
                               while (randomAnswerD == randomAnswerB || randomAnswerD == randomAnswerA || (deck.get(randomAnswerD).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerD = (int) (Math.random() * (deck.size()));
                               }
                           }
                           if (correctMultipleChoice1 == 4) //D is correct
                           {
                               randomAnswerB = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerB).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerB = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerC = (int) (Math.random() * (deck.size()));
                               while (randomAnswerB == randomAnswerC || (deck.get(randomAnswerC).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerC = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerA = (int) (Math.random() * (deck.size()));
                               while (randomAnswerA == randomAnswerC || randomAnswerA == randomAnswerB || (deck.get(randomAnswerA).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerA = (int) (Math.random() * (deck.size()));
                               }
                           }
                           randomAnswersMCQ1[0] = randomAnswerA;
                           randomAnswersMCQ1[1] = randomAnswerB;
                           randomAnswersMCQ1[2] = randomAnswerC;
                           randomAnswersMCQ1[3] = randomAnswerD;
                           randomAnswersSelectedMCQ1 = true;
                       }
                       if(currentQuestion == 7 && randomAnswersSelectedMCQ2 == false) {

                           if (correctMultipleChoice2 == 1) //A is correct
                           {
                               randomAnswerB = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerB).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerB = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerC = (int) (Math.random() * (deck.size()));
                               while (randomAnswerB == randomAnswerC || (deck.get(randomAnswerC).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerC = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerD = (int) (Math.random() * (deck.size()));
                               while (randomAnswerD == randomAnswerC || randomAnswerD == randomAnswerB || (deck.get(randomAnswerD).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerD = (int) (Math.random() * (deck.size()));
                               }
                           }
                           else if(correctMultipleChoice2 == 2) //B is correct
                           {
                               randomAnswerA = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerA).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerA = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerC = (int) (Math.random() * (deck.size()));
                               while (randomAnswerA == randomAnswerC || (deck.get(randomAnswerC).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerC = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerD = (int) (Math.random() * (deck.size()));
                               while (randomAnswerD == randomAnswerA || randomAnswerD == randomAnswerC || (deck.get(randomAnswerD).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerD = (int) (Math.random() * (deck.size()));
                               }
                           }
                           else if(correctMultipleChoice2 == 3) //C is correct
                           {
                               randomAnswerA = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerA).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerA = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerB = (int) (Math.random() * (deck.size()));
                               while (randomAnswerB == randomAnswerA || (deck.get(randomAnswerB).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerB = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerD = (int) (Math.random() * (deck.size()));
                               while (randomAnswerD == randomAnswerB || randomAnswerD == randomAnswerA || (deck.get(randomAnswerD).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerD = (int) (Math.random() * (deck.size()));
                               }
                           }
                           if (correctMultipleChoice2 == 4) //D is correct
                           {
                               randomAnswerB = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerB).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerB = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerC = (int) (Math.random() * (deck.size()));
                               while (randomAnswerB == randomAnswerC || (deck.get(randomAnswerC).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerC = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerA = (int) (Math.random() * (deck.size()));
                               while (randomAnswerA == randomAnswerC || randomAnswerA == randomAnswerB || (deck.get(randomAnswerA).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerA = (int) (Math.random() * (deck.size()));
                               }
                           }
                           randomAnswersMCQ2[0] = randomAnswerA;
                           randomAnswersMCQ2[1] = randomAnswerB;
                           randomAnswersMCQ2[2] = randomAnswerC;
                           randomAnswersMCQ2[3] = randomAnswerD;
                           randomAnswersSelectedMCQ2 = true;
                       }
                       if(currentQuestion == 8 && randomAnswersSelectedMCQ3 == false) {
                           if (correctMultipleChoice3 == 1) //A is correct
                           {
                               randomAnswerB = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerB).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerB = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerC = (int) (Math.random() * (deck.size()));
                               while (randomAnswerB == randomAnswerC || (deck.get(randomAnswerC).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerC = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerD = (int) (Math.random() * (deck.size()));
                               while (randomAnswerD == randomAnswerC || randomAnswerD == randomAnswerB || (deck.get(randomAnswerD).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerD = (int) (Math.random() * (deck.size()));
                               }
                           }
                           else if(correctMultipleChoice3 == 2) //B is correct
                           {
                               randomAnswerA = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerA).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerA = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerC = (int) (Math.random() * (deck.size()));
                               while (randomAnswerA == randomAnswerC || (deck.get(randomAnswerC).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerC = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerD = (int) (Math.random() * (deck.size()));
                               while (randomAnswerD == randomAnswerA || randomAnswerD == randomAnswerC || (deck.get(randomAnswerD).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerD = (int) (Math.random() * (deck.size()));
                               }
                           }
                           else if(correctMultipleChoice3 == 3) //C is correct
                           {
                               randomAnswerA = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerA).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerA = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerB = (int) (Math.random() * (deck.size()));
                               while (randomAnswerB == randomAnswerA || (deck.get(randomAnswerB).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerB = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerD = (int) (Math.random() * (deck.size()));
                               while (randomAnswerD == randomAnswerB || randomAnswerD == randomAnswerA || (deck.get(randomAnswerD).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerD = (int) (Math.random() * (deck.size()));
                               }
                           }
                           if (correctMultipleChoice3 == 4) //D is correct
                           {
                               randomAnswerB = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerB).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerB = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerC = (int) (Math.random() * (deck.size()));
                               while (randomAnswerB == randomAnswerC || (deck.get(randomAnswerC).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerC = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerA = (int) (Math.random() * (deck.size()));
                               while (randomAnswerA == randomAnswerC || randomAnswerA == randomAnswerB || (deck.get(randomAnswerA).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerA = (int) (Math.random() * (deck.size()));
                               }
                           }
                           randomAnswersMCQ3[0] = randomAnswerA;
                           randomAnswersMCQ3[1] = randomAnswerB;
                           randomAnswersMCQ3[2] = randomAnswerC;
                           randomAnswersMCQ3[3] = randomAnswerD;
                           randomAnswersSelectedMCQ3 = true;
                       }
                       if(currentQuestion == 9 && randomAnswersSelectedMCQ4 == false) {
                           if (correctMultipleChoice4 == 1) //A is correct
                           {
                               randomAnswerB = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerB).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerB = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerC = (int) (Math.random() * (deck.size()));
                               while (randomAnswerB == randomAnswerC || (deck.get(randomAnswerC).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerC = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerD = (int) (Math.random() * (deck.size()));
                               while (randomAnswerD == randomAnswerC || randomAnswerD == randomAnswerB || (deck.get(randomAnswerD).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerD = (int) (Math.random() * (deck.size()));
                               }
                           }
                           else if(correctMultipleChoice4 == 2) //B is correct
                           {
                               randomAnswerA = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerA).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerA = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerC = (int) (Math.random() * (deck.size()));
                               while (randomAnswerA == randomAnswerC || (deck.get(randomAnswerC).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerC = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerD = (int) (Math.random() * (deck.size()));
                               while (randomAnswerD == randomAnswerA || randomAnswerD == randomAnswerC || (deck.get(randomAnswerD).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerD = (int) (Math.random() * (deck.size()));
                               }
                           }
                           else if(correctMultipleChoice4 == 3) //C is correct
                           {
                               randomAnswerA = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerA).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerA = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerB = (int) (Math.random() * (deck.size()));
                               while (randomAnswerB == randomAnswerA || (deck.get(randomAnswerB).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerB = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerD = (int) (Math.random() * (deck.size()));
                               while (randomAnswerD == randomAnswerB || randomAnswerD == randomAnswerA || (deck.get(randomAnswerD).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerD = (int) (Math.random() * (deck.size()));
                               }
                           }
                           if (correctMultipleChoice4 == 4) //D is correct
                           {
                               randomAnswerB = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerB).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerB = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerC = (int) (Math.random() * (deck.size()));
                               while (randomAnswerB == randomAnswerC || (deck.get(randomAnswerC).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerC = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerA = (int) (Math.random() * (deck.size()));
                               while (randomAnswerA == randomAnswerC || randomAnswerA == randomAnswerB || (deck.get(randomAnswerA).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerA = (int) (Math.random() * (deck.size()));
                               }
                           }
                           randomAnswersMCQ4[0] = randomAnswerA;
                           randomAnswersMCQ4[1] = randomAnswerB;
                           randomAnswersMCQ4[2] = randomAnswerC;
                           randomAnswersMCQ4[3] = randomAnswerD;
                           randomAnswersSelectedMCQ4 = true;
                       }
                       if(currentQuestion == 10 && randomAnswersSelectedMCQ5 == false) {
                           if (correctMultipleChoice1 == 5) //A is correct
                           {
                               randomAnswerB = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerB).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerB = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerC = (int) (Math.random() * (deck.size()));
                               while (randomAnswerB == randomAnswerC || (deck.get(randomAnswerC).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerC = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerD = (int) (Math.random() * (deck.size()));
                               while (randomAnswerD == randomAnswerC || randomAnswerD == randomAnswerB || (deck.get(randomAnswerD).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerD = (int) (Math.random() * (deck.size()));
                               }
                           }
                           else if(correctMultipleChoice5 == 2) //B is correct
                           {
                               randomAnswerA = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerA).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerA = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerC = (int) (Math.random() * (deck.size()));
                               while (randomAnswerA == randomAnswerC || (deck.get(randomAnswerC).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerC = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerD = (int) (Math.random() * (deck.size()));
                               while (randomAnswerD == randomAnswerA || randomAnswerD == randomAnswerC || (deck.get(randomAnswerD).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerD = (int) (Math.random() * (deck.size()));
                               }
                           }
                           else if(correctMultipleChoice5 == 3) //C is correct
                           {
                               randomAnswerA = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerA).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerA = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerB = (int) (Math.random() * (deck.size()));
                               while (randomAnswerB == randomAnswerA || (deck.get(randomAnswerB).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerB = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerD = (int) (Math.random() * (deck.size()));
                               while (randomAnswerD == randomAnswerB || randomAnswerD == randomAnswerA || (deck.get(randomAnswerD).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerD = (int) (Math.random() * (deck.size()));
                               }
                           }
                           if (correctMultipleChoice5 == 4) //D is correct
                           {
                               randomAnswerB = (int) (Math.random() * (deck.size())); //move this from here to button code so it does not repeat over and over again
                               while ((deck.get(randomAnswerB).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerB = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerC = (int) (Math.random() * (deck.size()));
                               while (randomAnswerB == randomAnswerC || (deck.get(randomAnswerC).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerC = (int) (Math.random() * (deck.size()));
                               }

                               randomAnswerA = (int) (Math.random() * (deck.size()));
                               while (randomAnswerA == randomAnswerC || randomAnswerA == randomAnswerB || (deck.get(randomAnswerA).getTerm()).equals(testQuestions.get(currentQuestion - 1).getTerm())) {
                                   randomAnswerA = (int) (Math.random() * (deck.size()));
                               }
                           }
                           randomAnswersMCQ5[0] = randomAnswerA;
                           randomAnswersMCQ5[1] = randomAnswerB;
                           randomAnswersMCQ5[2] = randomAnswerC;
                           randomAnswersMCQ5[3] = randomAnswerD;
                           randomAnswersSelectedMCQ5 = true;

                       }
                       if(currentQuestion == 16)
                       {
                           typeAnswer16.setBounds(400, 313, 300, 30);
                           this.remove(typeAnswer17);
                           this.remove(typeAnswer18);
                           this.remove(typeAnswer19);
                           this.remove(typeAnswer20);
                           typeAnswer16.setFont(new Font("Helvetica", Font.PLAIN, 22));
                           this.add(typeAnswer16);
                           typeAnswer16.setFocusable(true);
                           typeAnswer16.requestFocus();
                           if(typedAnswer1 != null)
                               typeAnswer16.setText(typedAnswer1);
                       }
                       if(currentQuestion == 17)
                       {
                           typedAnswer1 = typeAnswer16.getText(); //need to create a string to store this
                           this.remove(typeAnswer16);
                           this.remove(typeAnswer18);
                           this.remove(typeAnswer19);
                           this.remove(typeAnswer20);
                           typeAnswer17.setBounds(400, 313, 300, 30);
                           typeAnswer17.setFont(new Font("Helvetica", Font.PLAIN, 22));
                           this.add(typeAnswer17);
                           typeAnswer17.setFocusable(true);
                           typeAnswer17.requestFocus();
                           if(typedAnswer2 != null)
                               typeAnswer17.setText(typedAnswer2);
                       }
                       if(currentQuestion == 18)
                       {
                           typedAnswer2 = typeAnswer17.getText();
                           this.remove(typeAnswer16);
                           this.remove(typeAnswer17);
                           this.remove(typeAnswer19);
                           this.remove(typeAnswer20);
                           typeAnswer18.setBounds(400, 313, 300, 30);
                           typeAnswer18.setFont(new Font("Helvetica", Font.PLAIN, 22));
                           this.add(typeAnswer18);
                           typeAnswer18.setFocusable(true);
                           typeAnswer18.requestFocus();
                           if(typedAnswer3 != null)
                               typeAnswer18.setText(typedAnswer3);
                       }
                       if(currentQuestion == 19)
                       {
                           typedAnswer3 = typeAnswer18.getText();
                           this.remove(typeAnswer16);
                           this.remove(typeAnswer17);
                           this.remove(typeAnswer18);
                           this.remove(typeAnswer20);
                           typeAnswer19.setBounds(400, 313, 300, 30);
                           typeAnswer19.setFont(new Font("Helvetica", Font.PLAIN, 22));
                           this.add(typeAnswer19);
                           typeAnswer19.setFocusable(true);
                           typeAnswer19.requestFocus();
                           if(typedAnswer4 != null)
                               typeAnswer19.setText(typedAnswer4);
                       }
                       if(currentQuestion == 20)
                       {
                           typedAnswer4 = typeAnswer19.getText();
                           this.remove(typeAnswer16);
                           this.remove(typeAnswer17);
                           this.remove(typeAnswer18);
                           this.remove(typeAnswer19);
                           typeAnswer20.setBounds(400, 313, 300, 30);
                           typeAnswer20.setFont(new Font("Helvetica", Font.PLAIN, 22));
                           this.add(typeAnswer20);
                           typeAnswer20.setFocusable(true);
                           typeAnswer20.requestFocus();
                           if(typedAnswer5 != null)
                               typeAnswer20.setText(typedAnswer5);
                       }
                   }
                   if(b.getTitle().equals("submitTest")) //NEEDS MORE CODE HERE
                   {
                       typedAnswer5 = typeAnswer20.getText();
                       homeScreenDisplay = false;
                       gravityDisplay = false;
                       learnDisplay = false;
                       testDisplay = false;
                       gravityStartDisplay = false;
                       flashcardsDisplay = false;
                       matchingDisplay = false;
                       testSubmittedDisplay = true;

                   }
                   if(b.getTitle().equals("previousTestQuestion"))
                   {
                         this.remove(typeAnswer16);
                         this.remove(typeAnswer17);
                         this.remove(typeAnswer18);
                         this.remove(typeAnswer19);
                         this.remove(typeAnswer20);

                       if(currentQuestion == 16) //currentQuestion will be 16
                       {
                           typedAnswer1 = typeAnswer16.getText();
                       }
                     if(currentQuestion == 17) //currentQuestion will be 16
                     {
                         typedAnswer2 = typeAnswer17.getText();
                         this.add(typeAnswer16);
                         if(typedAnswer1 != null)
                         typeAnswer16.setText(typedAnswer1);
                     }
                       if(currentQuestion == 18) //currentQuestion will be 17
                       {
                           typedAnswer3 = typeAnswer18.getText();
                           this.add(typeAnswer17);
                           if(typedAnswer2 != null)
                               typeAnswer17.setText(typedAnswer2);
                       }
                       if(currentQuestion == 19) //currentQuestion will be 18
                       {
                           typedAnswer4 = typeAnswer19.getText();
                           this.add(typeAnswer18);
                           if(typedAnswer3 != null)
                               typeAnswer18.setText(typedAnswer3);
                       }
                       if(currentQuestion == 20) //currentQuestion will be 19
                       {
                           typedAnswer5 = typeAnswer20.getText();
                           this.add(typeAnswer19);
                           if (typedAnswer4 != null)
                               typeAnswer19.setText(typedAnswer4);
                       }

                       if(currentQuestion == 11 || currentQuestion == 12 || currentQuestion == 13 || currentQuestion == 14 || currentQuestion == 15)
                           currentQuestion = 10;
                       else if(currentQuestion - 1 > 0)
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
    {


    }

    public void mouseReleased( MouseEvent e )
    {
        mouseX = e.getX();
        mouseY = e.getY();

       /* for (Button b : buttons) {
            if (b.getShape().contains(mouseX, mouseY) && b.isEnabled())
            {
                defMouseX = mouseX;
                defMouseY = mouseY;
                termMouseX = -1;
                termMouseY = -1;
            }
                b.unHighlight();
        }*/
    }

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
        //multiple choice question 1. User selected A
        if(correctMultipleChoice1 == 1 && currentQuestion == 6 && question6Answered == true){
            buttons[MCQATESTBUTTON].highlight();
            buttons[MCQBTESTBUTTON].unHighlight();
            buttons[MCQCTESTBUTTON].unHighlight();
            buttons[MCQDTESTBUTTON].unHighlight();
        }
        //multiple choice question 1. User selected B
        if(correctMultipleChoice1 == 2 && currentQuestion == 6 && question6Answered == true){
            buttons[MCQATESTBUTTON].unHighlight();
            buttons[MCQBTESTBUTTON].highlight();
            buttons[MCQCTESTBUTTON].unHighlight();
            buttons[MCQDTESTBUTTON].unHighlight();
        }
        //multiple choice question 1. User selected C
        if(correctMultipleChoice1 == 3 && currentQuestion == 6 && question6Answered == true){
            buttons[MCQATESTBUTTON].unHighlight();
            buttons[MCQBTESTBUTTON].unHighlight();
            buttons[MCQCTESTBUTTON].highlight();
            buttons[MCQDTESTBUTTON].unHighlight();
        }
        //multiple choice question 1. User selected D
        if(correctMultipleChoice1 == 4 && currentQuestion == 6 && question6Answered == true){
            buttons[MCQATESTBUTTON].unHighlight();
            buttons[MCQBTESTBUTTON].unHighlight();
            buttons[MCQCTESTBUTTON].unHighlight();
            buttons[MCQDTESTBUTTON].highlight();
        }
        //multiple choice question 2. User selected A
        if(correctMultipleChoice2 == 1 && currentQuestion == 7 && question7Answered == true){
            buttons[MCQATESTBUTTON].highlight();
            buttons[MCQBTESTBUTTON].unHighlight();
            buttons[MCQCTESTBUTTON].unHighlight();
            buttons[MCQDTESTBUTTON].unHighlight();
        }
        //multiple choice question 2. User selected B
        if(correctMultipleChoice2 == 2 && currentQuestion == 7 && question7Answered == true){
            buttons[MCQATESTBUTTON].unHighlight();
            buttons[MCQBTESTBUTTON].highlight();
            buttons[MCQCTESTBUTTON].unHighlight();
            buttons[MCQDTESTBUTTON].unHighlight();
        }
        //multiple choice question 2. User selected C
        if(correctMultipleChoice2 == 3 && currentQuestion == 7 && question7Answered == true){
            buttons[MCQATESTBUTTON].unHighlight();
            buttons[MCQBTESTBUTTON].unHighlight();
            buttons[MCQCTESTBUTTON].highlight();
            buttons[MCQDTESTBUTTON].unHighlight();
        }
        //multiple choice question 2. User selected D
        if(correctMultipleChoice2 == 4 && currentQuestion == 7 && question7Answered == true){
            buttons[MCQATESTBUTTON].unHighlight();
            buttons[MCQBTESTBUTTON].unHighlight();
            buttons[MCQCTESTBUTTON].unHighlight();
            buttons[MCQDTESTBUTTON].highlight();
        }
        //multiple choice question 3. User selected A
        if(correctMultipleChoice3 == 1 && currentQuestion == 8 && question8Answered == true){
            buttons[MCQATESTBUTTON].highlight();
            buttons[MCQBTESTBUTTON].unHighlight();
            buttons[MCQCTESTBUTTON].unHighlight();
            buttons[MCQDTESTBUTTON].unHighlight();
        }
        //multiple choice question 3. User selected B
        if(correctMultipleChoice3 == 2 && currentQuestion == 8 && question8Answered == true){
            buttons[MCQATESTBUTTON].unHighlight();
            buttons[MCQBTESTBUTTON].highlight();
            buttons[MCQCTESTBUTTON].unHighlight();
            buttons[MCQDTESTBUTTON].unHighlight();
        }
        //multiple choice question 3. User selected C
        if(correctMultipleChoice3 == 3 && currentQuestion == 8 && question8Answered == true){
            buttons[MCQATESTBUTTON].unHighlight();
            buttons[MCQBTESTBUTTON].unHighlight();
            buttons[MCQCTESTBUTTON].highlight();
            buttons[MCQDTESTBUTTON].unHighlight();
        }
        //multiple choice question 3. User selected D
        if(correctMultipleChoice3 == 4 && currentQuestion == 8 && question8Answered == true){
            buttons[MCQATESTBUTTON].unHighlight();
            buttons[MCQBTESTBUTTON].unHighlight();
            buttons[MCQCTESTBUTTON].unHighlight();
            buttons[MCQDTESTBUTTON].highlight();
        }
        //multiple choice question 4. User selected A
        if(correctMultipleChoice4 == 1 && currentQuestion == 9 && question9Answered == true){
            buttons[MCQATESTBUTTON].highlight();
            buttons[MCQBTESTBUTTON].unHighlight();
            buttons[MCQCTESTBUTTON].unHighlight();
            buttons[MCQDTESTBUTTON].unHighlight();
        }
        //multiple choice question 4. User selected B
        if(correctMultipleChoice4 == 2 && currentQuestion == 9 && question9Answered == true){
            buttons[MCQATESTBUTTON].unHighlight();
            buttons[MCQBTESTBUTTON].highlight();
            buttons[MCQCTESTBUTTON].unHighlight();
            buttons[MCQDTESTBUTTON].unHighlight();
        }
        //multiple choice question 4. User selected C
        if(correctMultipleChoice4 == 3 && currentQuestion == 9 && question9Answered == true){
            buttons[MCQATESTBUTTON].unHighlight();
            buttons[MCQBTESTBUTTON].unHighlight();
            buttons[MCQCTESTBUTTON].highlight();
            buttons[MCQDTESTBUTTON].unHighlight();
        }
        //multiple choice question 4. User selected D
        if(correctMultipleChoice4 == 4 && currentQuestion == 9 && question9Answered == true){
            buttons[MCQATESTBUTTON].unHighlight();
            buttons[MCQBTESTBUTTON].unHighlight();
            buttons[MCQCTESTBUTTON].unHighlight();
            buttons[MCQDTESTBUTTON].highlight();
        }
        //multiple choice question 5. User selected A
        if(correctMultipleChoice5 == 1 && currentQuestion == 10 && question10Answered == true){
            buttons[MCQATESTBUTTON].highlight();
            buttons[MCQBTESTBUTTON].unHighlight();
            buttons[MCQCTESTBUTTON].unHighlight();
            buttons[MCQDTESTBUTTON].unHighlight();
        }
        //multiple choice question 5. User selected B
        if(correctMultipleChoice5 == 2 && currentQuestion == 10 && question10Answered == true){
            buttons[MCQATESTBUTTON].unHighlight();
            buttons[MCQBTESTBUTTON].highlight();
            buttons[MCQCTESTBUTTON].unHighlight();
            buttons[MCQDTESTBUTTON].unHighlight();
        }
        //multiple choice question 5. User selected C
        if(correctMultipleChoice5 == 3 && currentQuestion == 10 && question10Answered == true){
            buttons[MCQATESTBUTTON].unHighlight();
            buttons[MCQBTESTBUTTON].unHighlight();
            buttons[MCQCTESTBUTTON].highlight();
            buttons[MCQDTESTBUTTON].unHighlight();
        }
        //multiple choice question 5. User selected D
        if(correctMultipleChoice5 == 4 && currentQuestion == 10 && question10Answered == true){
            buttons[MCQATESTBUTTON].unHighlight();
            buttons[MCQBTESTBUTTON].unHighlight();
            buttons[MCQCTESTBUTTON].unHighlight();
            buttons[MCQDTESTBUTTON].highlight();
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
