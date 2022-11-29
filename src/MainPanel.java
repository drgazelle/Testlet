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
//import java.util.ArrayList;

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

    //images
    //private ImageIcon startScreen  = new ImageIcon("images/backgrounds/startScreen.png");

    //array of buttons
    private Button[] buttons = new Button[17];

    //each int represents a different button.
    private static final int HOMEBUTTON = 0;
    private static final int EXPORTBUTTON = 1;
    private static final int SETTINGSBUTTON = 2;
    private static final int NEWDECKBUTTON = 3;
   // private static final int UPDATEBUTTON = 4;
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
                buttons[GRAVITYSTARTGAME].setEnabled(false);
                buttons[GRAVITYSTARTGAME].setVisible(false);

                buttons[FLASHCARDFLIP].drawButton(g);
                buttons[FLASHCARDFLIP].setEnabled(true);
                buttons[FLASHCARDFLIP].setVisible(false);
                buttons[PREVIOUSCARD].drawButton(g);
                buttons[PREVIOUSCARD].setEnabled(true);
                buttons[PREVIOUSCARD].setVisible(true);
                buttons[NEXTCARD].drawButton(g);
                buttons[NEXTCARD].setEnabled(true);
                buttons[NEXTCARD].setVisible(true);

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
                    if(termOddDefEven % 2 == 0) //even, show the definition
                     {
                         g.setColor(Color.BLACK);
                         g.setFont(new Font("Helvetica", Font.PLAIN, 30));
                         g.drawString("Definition", 350, 150);
                         if(deck != null)
                         g.drawString(deck.get(flashcardShownInt).getDef(), 530, 260);
                     }
                     else //termOddDefEven is odd, show term
                     {
                         g.setColor(Color.BLACK);
                         g.setFont(new Font("Helvetica", Font.PLAIN, 30));
                         g.drawString("Term", 350, 150);
                         if(deck != null)
                         g.drawString(deck.get(flashcardShownInt).getTerm(), 530, 260);
                     }
                }
            }

            if(learnDisplay == true)
            {
                buttons[GRAVITYSTARTGAME].setEnabled(false);
                buttons[GRAVITYSTARTGAME].setVisible(false);

                buttons[LEARNA].drawButton(g);
                buttons[LEARNA].setEnabled(true);
                buttons[LEARNB].drawButton(g);
                buttons[LEARNB].setEnabled(true);
                buttons[LEARNC].drawButton(g);
                buttons[LEARNC].setEnabled(true);
                buttons[LEARND].drawButton(g);
                buttons[LEARND].setEnabled(true);

                if(deck == null)
                {
                    g.setColor(Color.RED);
                    g.setFont(new Font("Helvetica", Font.PLAIN, 30));
                    g.drawString("Select a deck!", 490, 260);
                }
                else
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
                    g.drawString(learnQueue.peek().getDef(), 215, 150);
                    g.setFont(new Font("SansSerif", Font.BOLD, 20));
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

                    if(randomAnswers == 3)
                    {
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
                           while (deck.get(randomWrongAnswer1).getTerm().equals(learnQueue.peek().getTerm())) //NEED TO EDIT HERE
                               //randomWrongAnswer2 = (int)(Math.random()*(deck.size()));
                               while (deck.get(randomWrongAnswer2).getTerm().equals(deck.get(randomWrongAnswer1).getTerm()) || deck.get(randomWrongAnswer2).getTerm().equals(learnQueue.peek().getTerm()))
                                   randomWrongAnswer2 = (int) (Math.random() * (deck.size()));
                           while (deck.get(randomWrongAnswer3).getTerm().equals(deck.get(randomWrongAnswer1).getTerm()) || deck.get(randomWrongAnswer3).getTerm().equals(deck.get(randomWrongAnswer2).getTerm()) || deck.get(randomWrongAnswer3).getTerm().equals(learnQueue.peek().getTerm()))
                               randomWrongAnswer3 = (int) (Math.random() * (deck.size()));
                           randomWrongAnswer4 = (int) (Math.random() * (deck.size()));
                           while (deck.get(randomWrongAnswer4).getTerm().equals(deck.get(randomWrongAnswer1).getTerm()) || deck.get(randomWrongAnswer4).getTerm().equals(deck.get(randomWrongAnswer2).getTerm()) || deck.get(randomWrongAnswer4).getTerm().equals(deck.get(randomWrongAnswer3).getTerm()) || deck.get(randomWrongAnswer4).getTerm().equals(learnQueue.peek().getTerm()))
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
                   if(b.getTitle().equals("prevCard"))
                   {
                       if((deck != null) && (flashcardShownInt - 1 >= 0))
                           flashcardShownInt--;
                   }
                   if(b.getTitle().equals("learnA"))
                   {
                       randomAnswers = (int)(Math.random()*(4))+1;

                       randomWrongAnswer1 = (int)(Math.random()*(deck.size()));
                       while(deck.get(randomWrongAnswer1).getTerm().equals(learnQueue.peek().getTerm())) //NEED TO EDIT HERE
                       //randomWrongAnswer2 = (int)(Math.random()*(deck.size()));
                       while(deck.get(randomWrongAnswer2).getTerm().equals(deck.get(randomWrongAnswer1).getTerm()) || deck.get(randomWrongAnswer2).getTerm().equals(learnQueue.peek().getTerm()))
                           randomWrongAnswer2 = (int)(Math.random()*(deck.size()));
                       while(deck.get(randomWrongAnswer3).getTerm().equals(deck.get(randomWrongAnswer1).getTerm()) || deck.get(randomWrongAnswer3).getTerm().equals(deck.get(randomWrongAnswer2).getTerm()) || deck.get(randomWrongAnswer3).getTerm().equals(learnQueue.peek().getTerm()))
                           randomWrongAnswer3 = (int)(Math.random()*(deck.size()));
                       randomWrongAnswer4 = (int)(Math.random()*(deck.size()));
                       while(deck.get(randomWrongAnswer4).getTerm().equals(deck.get(randomWrongAnswer1).getTerm()) || deck.get(randomWrongAnswer4).getTerm().equals(deck.get(randomWrongAnswer2).getTerm()) || deck.get(randomWrongAnswer4).getTerm().equals(deck.get(randomWrongAnswer3).getTerm()) || deck.get(randomWrongAnswer4).getTerm().equals(learnQueue.peek().getTerm()))
                           randomWrongAnswer4 = (int)(Math.random()*(deck.size()));

                       if(A.equals(learnQueue.peek().getTerm())) //correct
                       {
                           learnQueue.remove();
                       }
                   }
                   if(b.getTitle().equals("learnB"))
                   {
                       randomAnswers = (int)(Math.random()*(4))+1;

                       randomWrongAnswer1 = (int)(Math.random()*(deck.size()));
                       while(deck.get(randomWrongAnswer1).getTerm().equals(learnQueue.peek().getTerm())) //NEED TO EDIT HERE
                           //randomWrongAnswer2 = (int)(Math.random()*(deck.size()));
                           while(deck.get(randomWrongAnswer2).getTerm().equals(deck.get(randomWrongAnswer1).getTerm()) || deck.get(randomWrongAnswer2).getTerm().equals(learnQueue.peek().getTerm()))
                               randomWrongAnswer2 = (int)(Math.random()*(deck.size()));
                       while(deck.get(randomWrongAnswer3).getTerm().equals(deck.get(randomWrongAnswer1).getTerm()) || deck.get(randomWrongAnswer3).getTerm().equals(deck.get(randomWrongAnswer2).getTerm()) || deck.get(randomWrongAnswer3).getTerm().equals(learnQueue.peek().getTerm()))
                           randomWrongAnswer3 = (int)(Math.random()*(deck.size()));
                       randomWrongAnswer4 = (int)(Math.random()*(deck.size()));
                       while(deck.get(randomWrongAnswer4).getTerm().equals(deck.get(randomWrongAnswer1).getTerm()) || deck.get(randomWrongAnswer4).getTerm().equals(deck.get(randomWrongAnswer2).getTerm()) || deck.get(randomWrongAnswer4).getTerm().equals(deck.get(randomWrongAnswer3).getTerm()) || deck.get(randomWrongAnswer4).getTerm().equals(learnQueue.peek().getTerm()))
                           randomWrongAnswer4 = (int)(Math.random()*(deck.size()));

                       if(B.equals(learnQueue.peek().getTerm())) //correct
                       {
                           learnQueue.remove();
                       }
                   }
                   if(b.getTitle().equals("learnC"))
                   {
                       randomAnswers = (int)(Math.random()*(4))+1;

                       randomWrongAnswer1 = (int)(Math.random()*(deck.size()));
                       while(deck.get(randomWrongAnswer1).getTerm().equals(learnQueue.peek().getTerm())) //NEED TO EDIT HERE
                           //randomWrongAnswer2 = (int)(Math.random()*(deck.size()));
                           while(deck.get(randomWrongAnswer2).getTerm().equals(deck.get(randomWrongAnswer1).getTerm()) || deck.get(randomWrongAnswer2).getTerm().equals(learnQueue.peek().getTerm()))
                               randomWrongAnswer2 = (int)(Math.random()*(deck.size()));
                       while(deck.get(randomWrongAnswer3).getTerm().equals(deck.get(randomWrongAnswer1).getTerm()) || deck.get(randomWrongAnswer3).getTerm().equals(deck.get(randomWrongAnswer2).getTerm()) || deck.get(randomWrongAnswer3).getTerm().equals(learnQueue.peek().getTerm()))
                           randomWrongAnswer3 = (int)(Math.random()*(deck.size()));
                       randomWrongAnswer4 = (int)(Math.random()*(deck.size()));
                       while(deck.get(randomWrongAnswer4).getTerm().equals(deck.get(randomWrongAnswer1).getTerm()) || deck.get(randomWrongAnswer4).getTerm().equals(deck.get(randomWrongAnswer2).getTerm()) || deck.get(randomWrongAnswer4).getTerm().equals(deck.get(randomWrongAnswer3).getTerm()) || deck.get(randomWrongAnswer4).getTerm().equals(learnQueue.peek().getTerm()))
                           randomWrongAnswer4 = (int)(Math.random()*(deck.size()));

                       if(C.equals(learnQueue.peek().getTerm())) //correct
                       {
                           learnQueue.remove();
                       }
                   }
                   if(b.getTitle().equals("learnD"))
                   {
                       randomAnswers = (int)(Math.random()*(4))+1;

                       randomWrongAnswer1 = (int)(Math.random()*(deck.size()));
                       while(deck.get(randomWrongAnswer1).getTerm().equals(learnQueue.peek().getTerm())) //NEED TO EDIT HERE
                           //randomWrongAnswer2 = (int)(Math.random()*(deck.size()));
                           while(deck.get(randomWrongAnswer2).getTerm().equals(deck.get(randomWrongAnswer1).getTerm()) || deck.get(randomWrongAnswer2).getTerm().equals(learnQueue.peek().getTerm()))
                               randomWrongAnswer2 = (int)(Math.random()*(deck.size()));
                       while(deck.get(randomWrongAnswer3).getTerm().equals(deck.get(randomWrongAnswer1).getTerm()) || deck.get(randomWrongAnswer3).getTerm().equals(deck.get(randomWrongAnswer2).getTerm()) || deck.get(randomWrongAnswer3).getTerm().equals(learnQueue.peek().getTerm()))
                           randomWrongAnswer3 = (int)(Math.random()*(deck.size()));
                       randomWrongAnswer4 = (int)(Math.random()*(deck.size()));
                       while(deck.get(randomWrongAnswer4).getTerm().equals(deck.get(randomWrongAnswer1).getTerm()) || deck.get(randomWrongAnswer4).getTerm().equals(deck.get(randomWrongAnswer2).getTerm()) || deck.get(randomWrongAnswer4).getTerm().equals(deck.get(randomWrongAnswer3).getTerm()) || deck.get(randomWrongAnswer4).getTerm().equals(learnQueue.peek().getTerm()))
                           randomWrongAnswer4 = (int)(Math.random()*(deck.size()));

                       if(D.equals(learnQueue.peek().getTerm())) //correct
                       {
                           learnQueue.remove();
                       }
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
    public void mouseMoved( MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
        //***BUTTON CODE***highlight button if mouse is on it
        for (Button b : buttons) {
            if (b.getShape().contains(mouseX, mouseY) && b.isEnabled())
                b.highlight();
            else
                b.unHighlight();
        }
        if(numberOfSettingsClicks >0)
        {
            buttons[SETTINGSBUTTON].highlight();
        }
        if(gravityDisplay == true || gravityStartDisplay == true)
        {
            buttons[GRAVITY].highlight();
        }
        if(flashcardsDisplay == true)
        {
            buttons[FLASHCARDS].highlight();
        }
        if(learnDisplay == true)
        {
            buttons[LEARN].highlight();
        }
        if(testDisplay == true)
        {
            buttons[TEST].highlight();
        }
        if(matchingDisplay == true)
        {
            buttons[MATCHING].highlight();
        }
        if(flashcardShownInt == 0)
        {
            buttons[PREVIOUSCARD].highlight();
        }
        if((deck == null) || ((deck != null) && (flashcardShownInt == deck.size()-1)))
        {
            buttons[NEXTCARD].highlight();
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
