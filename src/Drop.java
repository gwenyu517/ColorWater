import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Drop {
    private int size;
    private Particle[] particles;
    private int numPart;
    private int pRadius = 5;
    private int pDiam = 2*pRadius;

    private int depth = 7;

    private Point location;
    private boolean colorMode = true;

    private BufferedImage wetAreaMask;
    private BufferedImage image;
    private Graphics2D g2d;

    private Color color = Color.BLACK;

    private double maxRotation = .5;

    public Drop(int size, BufferedImage image){
        this.size = size;
        this.image = image;


        location = new Point();

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
        numPart = depth;        // 9 at center
        for (int i = 1; i <= size; ++i){
            numPart += num * depth;
            num = 2 * num;
        }
    }

    public BufferedImage getImage(){
        return this.image;
    }

    public void setWetAreaMask(BufferedImage wetAreaMask){
        this.wetAreaMask = wetAreaMask;
    }

    public void dripAt(Point point){
        location.setLocation(point);
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
                k += depth;
            }
            else {
                for (int j = 0; j < num; ++j) {
                    x = point.x + p * Math.sin(theta);
                    y = point.y + p * Math.cos(theta);

                    drawParticlesAt(x, y, k, theta);
                    theta += dTheta;

                    k += depth;
                }
                p += pRadius;
                num = 2 * num;
                dTheta = dTheta / 2;
            }
        }
    }

    private void drawParticlesAt(double x, double y, int k, double theta){
        for (int i = 0; i < depth; ++i){
            particles[k] = new Particle(x, y, theta);
            g2d.fill(new Ellipse2D.Double(particles[k].x, particles[k].y, pDiam, pDiam));
            ++k;
        }
    }

    public void setColorMode(boolean mode){
        colorMode = mode;
    }


    public void spread(){
        double x;
        double y;

        for (int i = 0; i < numPart; ++i){
  //          g2d.setColor(Color.WHITE);
     //       System.out.println(i);
    //        g2d.fill(new Ellipse2D.Double(particles[i].x, particles[i].y, 10, 10));
            g2d.setColor(color);

            particles[i].determineBias();
            particles[i].determineDirection();

            particles[i].theta += maxRotation * randomValue() * particles[i].direction;

            x = 5*Math.cos(particles[i].theta) + particles[i].x;
            y = 5* -1 * Math.sin(particles[i].theta) + particles[i].y;

            if (withinWetMask(x, y)){
                g2d.fill(new Ellipse2D.Double(x, y, 10, 10));
                particles[i].x = x;
                particles[i].y = y;
            }
            else{
                g2d.fill(new Ellipse2D.Double(particles[i].x, particles[i].y, 10, 10));
            }
        }
    }

    private boolean withinWetMask(double x, double y){
        if (colorMode == false)
            return true;

        if (wetAreaMask.getRGB((int)x, (int)y) != Color.WHITE.getRGB())
            return true;
        else
            return false;

/*
        double i = location.x - x;
        double j = location.y - y;


        if ((i*i) + (j*j) < (200*200))
            return true;
        else return false;*/
    }

    public double randomValue(){
        Random r = new Random();
        return r.nextDouble();
    }

    public void randomColor(){
        color = new Color((int)(randomValue()*255), (int)(randomValue()*255), (int)(randomValue()*255), 30);
    }

}
