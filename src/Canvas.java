import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel{
    private final int WIDTH;
    private final int HEIGHT;

    private WetAreaMask waterLayer;
    private BufferedImage image;
    private Graphics2D g2d;                 // ????

    // UMM???
    private int dropDuration = 20;      // how many steps, for loop in addDropAt()

    public Canvas(int width, int height){
        WIDTH = width;
        HEIGHT = height;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();// ????


        Dimension size = new Dimension(WIDTH, HEIGHT);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0,WIDTH, HEIGHT);
        repaint();

        addMouseListener( new MouseAdapter(){
                    public void mousePressed(MouseEvent event){
                        addDropAt(event.getPoint());
                    }
        });
        addMouseMotionListener( new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent event){

            }
        });
    }

    private void addDropAt(Point point){
        new Thread( new Runnable() {
            public void run(){

                // Todo: setSize() function
                int size = 5;          // given in rounds/layers
                Drop drop = new Drop(size, image);
         //       image = drop.getImage();

                for (int i = 0; i < dropDuration; ++i){
                    if (i == 0)
                        drop.dripAt(point);
                    else
                        drop.spread();

                        image = drop.getImage();

                    SwingUtilities.invokeLater(
                            new Runnable(){
                                public void run(){
                                    repaint();
                                }
                            }
                    );
                }

            }
        }).start();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }



}
