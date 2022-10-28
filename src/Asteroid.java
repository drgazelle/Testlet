import javax.swing.*;

public class Asteroid {
    private Flashcard flashcard;
    private int x, y; //position in pixel space that the asteroid will be drawn
    private ImageIcon[] pictures; //array of animation frames
    private int animationIndex; //the current index of pictures that we will display
    private int animationDelay; //the higher the delay, the slower the animation will be
    private int numFrames; //the total number of frames that have passed, used to control animationDelay

    /**
     * 5-arg constructor that instantiates an Asteroid object
     * with its matching flashcard and specifics for animation
     * @param f flashcard that is used to display the term
     * @param dx x location in pixel space
     * @param dy y location in pixel space
     * @param p array of images used for animation
     * @param ad delay in animation
     */
    public Asteroid(Flashcard f, int dx, int dy, ImageIcon[] p, int ad)
    {
        flashcard = f;
        x = dx;
        y = dy;
        pictures = p;
        animationDelay = ad;
        animationIndex = 0;
        numFrames = 0;
    }

    /**
     * Accessor method for x value
     * @return x
     */
    public int getX()
    {
        return x;
    }

    /**
     * Accessor method for y value
     * @return y
     */
    public int getY()
    {
        return y;
    }

    /**
     * Accessor method for which picture should be displayed to the screen
     * based on animation delay and array of ImageIcons
     * @return ImageIcon that should be displayed to screen
     */
    public ImageIcon getPicture()
    {
        if(animationIndex < 0 || animationIndex >= pictures.length)
            return null;
        ImageIcon temp = pictures[animationIndex]; //the current picture we will show
        if(numFrames == Integer.MAX_VALUE)
            numFrames = 0;
        numFrames++; //advance the number of frames
        if(numFrames % animationDelay == 0)
            animationIndex = (animationIndex + 1) % pictures.length;
        return temp;
    }

    /**
     * Setter method for y value in pixel space
     * @param yVal new y value
     */
    public void setY(int yVal)
    {
        y = yVal;
    }

    /**
     * Setter method for x value in pixel space
     * @param xVal new x value
     */
    public void setX(int xVal)
    {
        x = xVal;
    }

    /**
     * Increases y-value for pixel space by one
     * Used for the asteroid to appear like it is falling
     */
    public void moveDown()
    {
        y++;
    }

}
