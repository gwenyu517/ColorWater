import java.awt.*;
import java.awt.image.BufferedImage;

public class WetAreaMask {
    private final int WIDTH;
    private final int HEIGHT;
    private BufferedImage layer;
    private Graphics2D g2d;

    public WetAreaMask(int width, int height){
        WIDTH = width;
        HEIGHT = height;

        layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2d = layer.createGraphics();

        // dry = black; wet = white
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,WIDTH, HEIGHT);
        g2d.setColor(Color.WHITE);
    }

    public void addWater(int particles){

    }

    public boolean isWetAt(double x, double y) {
        if (layer.getRGB((int)x, (int)y) != 0)   // if not black
            return true;                        // then not dry
        else
            return false;
    }
}
