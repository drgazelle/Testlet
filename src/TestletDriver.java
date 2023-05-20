import javax.swing.JFrame;

/** TestletDriver class defines the containing
 *  window for the application
 * @author RMizelle
 * @version V0.1
 */
public class TestletDriver {
    //window dimensions
    public static final int WIDTH = 960;
    public static final int HEIGHT = 540;
    public static MainPanel panel;

    public static void main(String[] args) {
        panel = new MainPanel();
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame();
        // frame attributes
        frame.setContentPane(panel);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(50, 50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}