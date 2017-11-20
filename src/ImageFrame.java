import javax.swing.*;
import java.awt.*;

public class ImageFrame extends JFrame {
    public ImageFrame(int width, int height){
        this.setTitle("ColorWater");
        this.setSize(width, height);

        // Todo: addTabs();

        // Todo: addCanvas();
        addCanvas(width, height);
        repaint();
    }

    private void addCanvas(int width, int height){
        Canvas c = new Canvas(width, height);
        this.getContentPane().add(c, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }
}
