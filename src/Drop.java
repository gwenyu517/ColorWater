import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Drop {
    private int size;
    private Particle[] particles;
    private int numPart;
    private int pRadius = 5;
    private int pDiam = 2*pRadius;

    private BufferedImage image;
    private Graphics2D g2d;

    private Color color = new Color(0,0,0,30);

    private double maxRotation = .5;

    public Drop(int size, BufferedImage image){
        this.size = size;
        this.image = image;

        determineNumOfPart();         // 1 + (size * 6) locations, 9 at each
        particles = new Particle[numPart];

    //    image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();

     //   this.g2d = g2d;

   //     g2d.setColor(Color.WHITE);
     //   g2d.fillRect(0,0,1000,1000);

        g2d.setColor(color);
    }

    private void determineNumOfPart(){
        int num = 6;
        numPart = 9;        // 9 at center
        for (int i = 1; i <= size; ++i){
            numPart += i * num * 9;
            num = 2 * num;
        }
    }

    public BufferedImage getImage(){
        return this.image;
    }

    public void dripAt(Point point){
        double theta;
        double p = pRadius;
        double dTheta = Math.PI / 3;

        double x = point.x;
        double y = point.y;
        int k = 0;

        int num = 6;


        for (int i = 0; i <= size; i++){
            theta = 0;
            if (i == 0){
                drawParticlesAt(x, y, k, theta);
                k += 9;
            }
            else {
                for (int j = 0; j < num; ++j) {
                    System.out.println("i = " + i);
                    System.out.println("j = " + j);
                    x = point.x + p * Math.sin(theta);
                    y = point.y + p * Math.cos(theta);

                    //     particles[k] = new Particle(x, y, theta);
                    //   g2d.fill(new Ellipse2D.Double(particles[k].x, particles[k].y, pDiam, pDiam));
                    drawParticlesAt(x, y, k, theta);
                    theta += dTheta;

                    k += 9;
                }
                p += pRadius;
                num = 2 * num;
                dTheta = dTheta / 2;
            }
        }
    }

    private void drawParticlesAt(double x, double y, int k, double theta){
        for (int i = 0; i < 9; ++i){
            System.out.println(k);
            particles[k] = new Particle(x, y, theta);
            g2d.fill(new Ellipse2D.Double(particles[k].x, particles[k].y, pDiam, pDiam));
            ++k;
        }
    }


}