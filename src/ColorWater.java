import javax.swing.*;

/**
 * Created by GwenYu on 11/20/2017.
 */
public class ColorWater {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;

    public static void main(String[] args){
        SwingUtilities.invokeLater(
                new Runnable(){
                    public void run(){
                        createAndShowGUI();
                    }
                }
        );
    }

    private static void createAndShowGUI(){
        JFrame frame = new ImageFrame(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //    frame.pack();
        frame.setVisible(true);
    }
}
