import javax.swing.JFrame;

/** TestletDriver class defines the containing
 *  window for the application
 * @author RMizelle
 * @version V0.1
 */
public class TestletDriver {
    //window dimensions
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;

    public static TestletPanel screen;

    public static void main(String[] args) {
        screen = new TestletPanel();
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Testlet");
        // frame attributes
        frame.setContentPane(screen);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(100, 50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}