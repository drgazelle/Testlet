import javax.swing.*;
//import java.util.*;
import java.awt.*;
//import java.awt.event.*;
import java.awt.Shape;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicSplitPaneUI;
//import java.awt.Container;
//import java.util.ArrayList;

/** MainPanel class defines the containing window
 *  for the application
 *  @author ACraig, RMizelle
 *  @version V0.6
 */
public class MainPanel extends JPanel implements MouseListener, MouseMotionListener
{
    //screen size
    private static final int xSIZE = 960;
    private static final int ySIZE = 540;
    private static final int leftBarSize = 200;
    private static final int topBarHeight = 50;

    //images
    //private ImageIcon startScreen  = new ImageIcon("images/backgrounds/startScreen.png");
    //main buttons for screens
    private Button[] buttons = new Button[7];
    private static final int HOMEBUTTON = 0;
    private static final int EXPORTBUTTON = 1;
    private static final int SETTINGSBUTTON = 2;
    private static final int NEWCOURSEBUTTON = 3;
    private static final int UPDATEBUTTON = 4;
    private static final int ARROWUPCOURSES = 5;
    private static final int ARROWDOWNCOURSES = 6;


    //screens
    private static final int HOMESCREEN = 0;

    private static int screenMode;

    private static final int SIZE = 500;

    int numberOfSettingsClicks = 0;

   // private int numberOfCourses = 0;

    //mouse location
    protected static int mouseX;
    protected static int mouseY;

    /** 0-arg constructor */
    public MainPanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        mouseX = SIZE/2;
        mouseY = SIZE/2;

        //add buttons here
        //example below:
        //                        x    y    width   height
        //Shape r1 = new Rectangle(350, 300,   270,    120);
        //ImageIcon startButton1 = new ImageIcon("images/buttons/start1.png");
        //ImageIcon startButton2 = new ImageIcon("images/buttons/start2.png");
        //buttons[STARTBUTTON] = new Button(r1, "start", startButton1, startButton2);

        Shape r1 = new Rectangle(0, 0, leftBarSize, topBarHeight);
        ImageIcon homeButton1 = new ImageIcon("resources/images/home1.png");
        ImageIcon homeButton2 = new ImageIcon("resources/images/home1.png"); //same image as homeButton1
        buttons[HOMEBUTTON] = new Button(r1, "home", homeButton1, homeButton2);

        Shape r2 = new Ellipse2D.Float(xSIZE - (4*topBarHeight) - 80, 2, topBarHeight-5, topBarHeight-5); //circle
        ImageIcon exportButton1 = new ImageIcon("resources/images/home1.png");
        ImageIcon exportButton2 = new ImageIcon("resources/images/home1.png");
        buttons[EXPORTBUTTON] = new Button(r2, "export", exportButton1, exportButton2);

        Shape r3 = new Ellipse2D.Float(xSIZE - (3*topBarHeight) - 60, 2, topBarHeight-5, topBarHeight-5); //circle
        ImageIcon settingsButton1 = new ImageIcon("resources/images/settings1.png");
        ImageIcon settingsButton2 = new ImageIcon("resources/images/settings2.png");
        buttons[SETTINGSBUTTON] = new Button(r3, "settings", settingsButton1, settingsButton2);

        Shape r4 = new Ellipse2D.Float(xSIZE - (2*topBarHeight) - 40, 2, topBarHeight-5, topBarHeight-5); //circle
        ImageIcon newCourseButton1 = new ImageIcon("resources/images/home1.png");
        ImageIcon newCourseButton2 = new ImageIcon("resources/images/home1.png"); //same image as homeButton1
        buttons[NEWCOURSEBUTTON] = new Button(r4, "newCourse", newCourseButton1, newCourseButton2);

        Shape r5 = new Ellipse2D.Float(xSIZE - (topBarHeight) - 20, 2, topBarHeight-5, topBarHeight-5); //circle
        ImageIcon updateButton1 = new ImageIcon("resources/images/home1.png");
        ImageIcon updateButton2 = new ImageIcon("resources/images/home1.png"); //same image as homeButton1
        buttons[UPDATEBUTTON] = new Button(r5, "update", updateButton1, updateButton2);

        Shape r6 = new Rectangle(0, topBarHeight, leftBarSize, topBarHeight-15);
        ImageIcon arrowUpCourses1 = new ImageIcon("resources/images/arrowUp1.png");
        ImageIcon arrowUpCourses2 = new ImageIcon("resources/images/arrowUp1.png"); //same image as homeButton1
        buttons[ARROWUPCOURSES] = new Button(r6, "arrowUpCourses", arrowUpCourses1, arrowUpCourses2);

        Shape r7 = new Rectangle(0, ySIZE - topBarHeight -18, leftBarSize, topBarHeight-15);
        ImageIcon arrowDownCourses1 = new ImageIcon("resources/images/arrowDown1.png");
        ImageIcon arrowDownCourses2 = new ImageIcon("resources/images/arrowDown1.png"); //same image as homeButton1
        buttons[ARROWDOWNCOURSES] = new Button(r7, "arrowDownCourses", arrowDownCourses1, arrowDownCourses2);
//        Shape r6 = new Ellipse2D.Float(0, 0, topBarHeight, topBarHeight); //circle
//        ImageIcon course1Button1 = new ImageIcon("src/home1.png");
//        ImageIcon course1Button2 = new ImageIcon("src/home1.png"); //same image as homeButton1
//        buttons[COURSE1BUTTON] = new Button(r6, "course1", course1Button1, course1Button2);

        //sets the screen mode
        screenMode = HOMESCREEN;

    }

    public void showBoard(Graphics g) {
        if (screenMode == HOMESCREEN) {

            //background image
            //g.drawImage(startScreen.getImage(), 0,0, xSIZE, ySIZE, null);
            g.setColor(Color.WHITE);
            g.fillRect(0,0, xSIZE, ySIZE);

            //left side bar background
            g.setColor(new Color(220,220,220));
            g.fillRect(0,0,leftBarSize, ySIZE);

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
            buttons[NEWCOURSEBUTTON].drawButton(g);
            buttons[NEWCOURSEBUTTON].setEnabled(true);
            buttons[NEWCOURSEBUTTON].setVisible(true);
            buttons[UPDATEBUTTON].drawButton(g);
            buttons[UPDATEBUTTON].setEnabled(true);
            buttons[UPDATEBUTTON].setVisible(true);
            buttons[ARROWUPCOURSES].drawButton(g);
            buttons[ARROWUPCOURSES].setEnabled(true);
            buttons[ARROWUPCOURSES].setVisible(true);
            buttons[ARROWDOWNCOURSES].drawButton(g);
            buttons[ARROWDOWNCOURSES].setVisible(true);
            buttons[ARROWDOWNCOURSES].setEnabled(true);

            if(numberOfSettingsClicks == 1) //if settings button is clicked, show settings menu
            {
                g.setColor(new Color(220,220,220));
                g.fillRoundRect(xSIZE - (3*topBarHeight) - 60, topBarHeight+5, (3*topBarHeight)+45, 445, 10,10);
                for(Button bu : buttons)
                {
                    if(bu.getTitle().equals("home") || bu.getTitle().equals("settings"))
                        bu.setEnabled(true);
                    else
                        bu.setEnabled(false);
                }
            }
            repaint();
        }
        else //if(gameMode == )
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

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        showBoard(g);
    }

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
               if(b.getShape().contains(mouseX, mouseY) && b.isEnabled())
               {
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
                       if(numberOfSettingsClicks == 1)
                           numberOfSettingsClicks = 0;
                   }
                   if(b.getTitle().equals("export"))
                   {
                       screenMode = 1; //change this for what to do when export is clicked
                   }
                   if(b.getTitle().equals("settings"))
                   {
                       if(numberOfSettingsClicks == 0)
                       numberOfSettingsClicks = 1;
                       else //numberOfSettingsClicks == 1
                       numberOfSettingsClicks = 0;
                   }
                   if(b.getTitle().equals("newCourse"))
                   {
                       screenMode = 1; //change this for what to do when newCourse is clicked
                   }
                   if(b.getTitle().equals("update"))
                   {
                       screenMode = 1; //change this for what to do when update is clicked
                   }
                   if(b.getTitle().equals("arrowUpCourses"))
                   {
                       //if there are more courses to show, then yes we can use it
                       //if there are no more courses to show, then do not do anything
                   }
                   if(b.getTitle().equals("arrowDownCourses"))
                   {

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
}
