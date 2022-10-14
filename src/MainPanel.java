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

    private boolean gravityStartDisplay = false;
    private boolean gravityDisplay = false;

    private static final int asteroidTextSize = 25;
    private static int DELAY = 1;
    private static Timer t;
    private static int frame;
    private static int asteroidX, asteroidY;
    private ImageIcon[] asteroidImages = {new ImageIcon("resources/images/asteroid1.png")};
    private Asteroid asteroid1;

    //images
    //private ImageIcon startScreen  = new ImageIcon("images/backgrounds/startScreen.png");

    //array of buttons
    private Button[] buttons = new Button[11];

    //each int represents a different button.
    private static final int HOMEBUTTON = 0;
    private static final int EXPORTBUTTON = 1;
    private static final int SETTINGSBUTTON = 2;
    private static final int NEWDECKBUTTON = 3;
    private static final int UPDATEBUTTON = 4;
//    private static final int LEFTARROWCOURSES = 5;
//    private static final int RIGHTARROWCOURSES = 6;
    private static final int FLASHCARDS = 5;
    private static final int LEARN = 6;
    private static final int TEST = 7;
    private static final int GRAVITY = 8;
    private static final int MATCHING = 9;
    private static final int GRAVITYSTARTGAME = 10;


    //screen modes
    private static final int HOMESCREEN = 0;
    private static final int FLASHCARDMODE = 1;
    private static final int LEARNMODE = 2;

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

    /** 0-arg constructor */
    public MainPanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        mouseX = SIZE/2;
        mouseY = SIZE/2;

        asteroidX = 300;
        asteroidY = -50;

        asteroid1 = new Asteroid(new Flashcard("hello", "5 letter word for hi"), asteroidX, asteroidY, asteroidImages, 400);

        t = new Timer(DELAY, new Listener());
        t.start();
        frame = 0;
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

        Shape r2 = new Ellipse2D.Float(xSIZE - (4*topBarHeight) - 80, 2, topBarHeight, topBarHeight-5); //circle
        ImageIcon exportButton1 = new ImageIcon("resources/images/export1.png");
        ImageIcon exportButton2 = new ImageIcon("resources/images/home1.png");
        buttons[EXPORTBUTTON] = new Button(r2, "export", exportButton1, exportButton2);

        Shape r3 = new Ellipse2D.Float(xSIZE - (3*topBarHeight) - 60, 2, topBarHeight, topBarHeight-5); //circle
        ImageIcon settingsButton1 = new ImageIcon("resources/images/settings1.png");
        ImageIcon settingsButton2 = new ImageIcon("resources/images/settings2.png");
        buttons[SETTINGSBUTTON] = new Button(r3, "settings", settingsButton1, settingsButton2);

        Shape r4 = new Ellipse2D.Float(xSIZE - (2*topBarHeight) - 40, 2, topBarHeight, topBarHeight-5); //circle
        ImageIcon newDeckButton1 = new ImageIcon("resources/images/newDeck1.png");
        ImageIcon newDeckButton2 = new ImageIcon("resources/images/newDeck2.png"); //same image as homeButton1
        buttons[NEWDECKBUTTON] = new Button(r4, "newDeck", newDeckButton1, newDeckButton2);

        Shape r5 = new Ellipse2D.Float(xSIZE - (topBarHeight) - 20, 2, topBarHeight-5, topBarHeight-5); //circle
        ImageIcon updateButton1 = new ImageIcon("resources/images/home1.png");
        ImageIcon updateButton2 = new ImageIcon("resources/images/home1.png"); //same image as homeButton1
        buttons[UPDATEBUTTON] = new Button(r5, "update", updateButton1, updateButton2);

//        Shape r6 = new Rectangle(0, topBarHeight + 3+80 + 80 + 80 + 80 + 80 + 3, leftBarSize/2, ySIZE - (topBarHeight + 3+80 + 80 + 80 + 80 + 80 + 3+33));
//        ImageIcon leftArrowCourses1 = new ImageIcon("resources/images/arrowLeft1.png");
//        ImageIcon leftArrowCourses2 = new ImageIcon("resources/images/arrowLeft2.png");
//        buttons[LEFTARROWCOURSES] = new Button(r6, "leftArrowCourses", leftArrowCourses1, leftArrowCourses2);
//
//        Shape r7 = new Rectangle(leftBarSize/2, topBarHeight + 3+80 + 80 + 80 + 80 + 80 + 3, leftBarSize/2, ySIZE - (topBarHeight + 3+80 + 80 + 80 + 80 + 80 + 3+33));
//        ImageIcon rightArrowCourses1 = new ImageIcon("resources/images/arrowRight1.png");
//        ImageIcon rightArrowCourses2 = new ImageIcon("resources/images/arrowRight2.png");
//        buttons[RIGHTARROWCOURSES] = new Button(r7, "rightArrowCourses", rightArrowCourses1, rightArrowCourses2);

        Shape r8 = new Rectangle(0, topBarHeight+3, leftBarSize, 91);
        ImageIcon flashCardButton1 = new ImageIcon("resources/images/flashcards1.png");
        ImageIcon flashCardButton2 = new ImageIcon("resources/images/flashcards2.png");
        buttons[FLASHCARDS] = new Button(r8, "flashcards", flashCardButton1, flashCardButton2);

        Shape r9 = new Rectangle(0, topBarHeight +3 + 91, leftBarSize, 91);
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

//        Shape r6 = new Ellipse2D.Float(0, 0, topBarHeight, topBarHeight); //circle
//        ImageIcon course1Button1 = new ImageIcon("src/home1.png");
//        ImageIcon course1Button2 = new ImageIcon("src/home1.png"); //same image as homeButton1
//        buttons[COURSE1BUTTON] = new Button(r6, "course1", course1Button1, course1Button2);

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

            if(gravityStartDisplay == true)
            {
                buttons[GRAVITYSTARTGAME].drawButton(g);
                buttons[GRAVITYSTARTGAME].setEnabled(true);
                buttons[GRAVITYSTARTGAME].setVisible(true);
            }

            if(gravityDisplay == true)
            {
                g.drawImage(asteroid1.getPicture().getImage(), asteroid1.getX(), asteroid1.getY(), asteroidTextSize * 5, asteroidTextSize * 5, null);
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
            buttons[UPDATEBUTTON].drawButton(g);
            buttons[UPDATEBUTTON].setEnabled(true);
            buttons[UPDATEBUTTON].setVisible(true);
//            buttons[LEFTARROWCOURSES].drawButton(g);
//            buttons[LEFTARROWCOURSES].setEnabled(true);
//            buttons[LEFTARROWCOURSES].setVisible(true);
//            buttons[RIGHTARROWCOURSES].drawButton(g);
//            buttons[RIGHTARROWCOURSES].setVisible(true);
//            buttons[RIGHTARROWCOURSES].setEnabled(true);

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
                       gravityDisplay = false;
                   }
                   if (b.getTitle().equals("export")) {
                       screenMode = 1; //change this for what to do when export is clicked
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
                   if (b.getTitle().equals("update")) {
                       screenMode = 1; //change this for what to do when update is clicked
                   }
                   if (b.getTitle().equals("flashcards"))
                   {
gravityDisplay = false;
                   }
                   if (b.getTitle().equals("learn"))
                   {
gravityDisplay = false;
                   }
                   if (b.getTitle().equals("test"))
                   {
gravityDisplay = false;
                   }
                   if (b.getTitle().equals("gravity"))
                   {
                       gravityDisplay = false;
                    gravityStartDisplay = true;
                   }
                   if(b.getTitle().equals("gravityStartGame"))
                   {
                       gravityStartDisplay = false;
                       gravityDisplay = true;
                       asteroid1.setY(asteroidY); //resets the asteroid position when the gravity button is clicked
                   }
                   if(b.getTitle().equals("matching"))
                   {
gravityDisplay = false;
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
    }

    public void mouseDragged( MouseEvent e)
    {}

    public void mouseExited( MouseEvent e )
    {}

    private class Listener implements ActionListener
    {
      public void actionPerformed(ActionEvent e) //this is called for each timer iteration
      {
          frame++;
          if(frame == Integer.MAX_VALUE)
              frame = 0;
          asteroid1.moveDown(); //decreases y value (position in pixel space) by 1 to have the appearance of the asteroid falling down
          //you could make a method within asteroid that relates to animation delay and the y value
          //new variable numFramesY, divide by animation delay. When numFramesY % animation delay == 0, then decrease the y value by one
          //for harder levels, the animation delay is less to make the asteroids go faster.
          repaint();
      }
    }
}
