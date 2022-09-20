import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.Shape;
//import java.awt.geom.Ellipse2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
//import javax.swing.ImageIcon;
//import java.awt.Container;
//import java.util.ArrayList;

/** TestletPanel class contains code for
 *  Graphics and Testlet Functions
 *  @author ACraig
 *  @since 9/16/2022
 *  @version V0.2
 */
public class TestletPanel extends JPanel implements MouseListener, MouseMotionListener
{
    //screen size
    //private static final int xSIZE = 900;
    //private static final int ySIZE = 600;

    //images
    //private ImageIcon startScreen  = new ImageIcon("images/backgrounds/startScreen.png");

    //main buttons for screens
    private Button[] buttons = new Button[1];
    private static final int STARTBUTTON = 0;

    //screens
    private static final int STARTSCREEN = 0;
    private static int screenMode;

    private static final int SIZE = 500;

    //mouse location
    protected static int mouseX;
    protected static int mouseY;

    public TestletPanel() {
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

        //sets the screen mode
        screenMode = STARTSCREEN;
    }

    public void showBoard(Graphics g) {
        if (screenMode == STARTSCREEN) {

            //background image
            //g.drawImage(startScreen.getImage(), 0,0, xSIZE, ySIZE, null);

            //draw buttons
            //example below:
            //buttons[BATTLEBUTTONFOUR].drawButton(g);
        } else //if(gameMode == )
        {

        }
    }

    public void mouseClicked(MouseEvent e)
    {
       /* int button = e.getButton();
        if (button == MouseEvent.BUTTON1) {
            if (gameMode == TUTORIALSCREEN) {
                for (int i = 0; i < buttons.length; i++) {
                    buttons[i].setEnabled(false);
                }
                tutorialIndex++;
            }
        }*/
        /*else
         {
         //***BUTTON CODE***actions if clicked on button
            for(Button b:buttons)
            {
               if(b.getShape().contains(mouseX, mouseY) && b.isEnabled())
               {
                  /* if(b.getTitle().equals("quit"))
                     System.exit(0);
                  else if(b.getTitle().equals("reset"))
                     resetEnemies();
                  else if(b.getTitle().equals("sound"))
                     Sound.click();*/
         /*
        if(b.getTitle().equals("start")) {
            //insert what we need to do when this button is clicked
        }
        }
        }*/
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
    }

    public void mouseDragged( MouseEvent e)
    {}

    public void mouseExited( MouseEvent e )
    {}

    }
