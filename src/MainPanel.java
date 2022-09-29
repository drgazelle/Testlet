import javax.swing.*;
//import java.util.*;
import java.awt.*;
//import java.awt.event.*;
import java.awt.Shape;
import java.awt.Color;
//import java.awt.geom.Ellipse2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import javax.swing.ImageIcon;
//import java.awt.Container;
//import java.util.ArrayList;

/** MainPanel class defines the containing window
 *  for the application
 *  @author ACraig, RMizelle
 *  @version V0.4
 */
public class MainPanel extends JPanel implements MouseListener, MouseMotionListener
{
    //screen size
    private static final int xSIZE = 960;
    private static final int ySIZE = 540;

    //scrollPane for left side of screen
   // private JScrollPane leftScrollPane;

    //images
    //private ImageIcon startScreen  = new ImageIcon("images/backgrounds/startScreen.png");
    //main buttons for screens
    private Button[] buttons = new Button[1];
    private static final int HOMEBUTTON = 0;

    //screens
    private static final int HOMESCREEN = 0;
    private static int screenMode;

    private static final int SIZE = 500;

    //mouse location
    protected static int mouseX;
    protected static int mouseY;

    /** 0-arg constructor */
    public MainPanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        mouseX = SIZE/2;
        mouseY = SIZE/2;

        //leftScrollPane = new JScrollPane(xSIZE/4, ySIZE);
        //leftScrollPane.setCorner(UPPER_LEFT_CORNER, )

        //add buttons here
        //example below:
        //                        x    y    width   height
        //Shape r1 = new Rectangle(350, 300,   270,    120);
        //ImageIcon startButton1 = new ImageIcon("images/buttons/start1.png");
        //ImageIcon startButton2 = new ImageIcon("images/buttons/start2.png");
        //buttons[STARTBUTTON] = new Button(r1, "start", startButton1, startButton2);

        Shape r1 = new Rectangle(0, 0, 120, 50);
        ImageIcon homeButton1 = new ImageIcon("src/home1.png");
        ImageIcon homeButton2 = new ImageIcon("src/home1.png"); //same image as homeButton1
        buttons[HOMEBUTTON] = new Button(r1, "home", homeButton1, homeButton2);

        //sets the screen mode
        screenMode = HOMESCREEN;
    }

    public void showBoard(Graphics g) {
        if (screenMode == HOMESCREEN) {

            //background image
            //g.drawImage(startScreen.getImage(), 0,0, xSIZE, ySIZE, null);
            g.setColor(Color.WHITE);
            g.fillRect(0,0, xSIZE, ySIZE);

            //top bar with settings buttons
            g.setColor(new Color(110, 220, 230));
            g.fillRect(120,0,xSIZE-120, 50);

            //draw buttons
            buttons[HOMEBUTTON].drawButton(g);
            buttons[HOMEBUTTON].setEnabled(true);
            buttons[HOMEBUTTON].setVisible(true);
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
    }

    public void mouseDragged( MouseEvent e)
    {}

    public void mouseExited( MouseEvent e )
    {}
}
