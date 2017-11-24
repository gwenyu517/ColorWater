import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageFrame extends JFrame {

    Canvas c;

    public ImageFrame(int width, int height){
        this.setTitle("ColorWater");
        this.setSize(width, height);

        // Todo: addTabs();


        // Temp menus
        addMenu();

        // Todo: addCanvas();
        addCanvas(width, height);
        repaint();
    }

    private void addCanvas(int width, int height){
        c = new Canvas(width, height);
        this.getContentPane().add(c, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    private void addMenu(){
        JMenu fileMenu = new JMenu("File");

        JMenuItem waterItem = new JMenuItem("Water mode");
        waterItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                c.setWaterMode(true);
            }
        } );

        fileMenu.add(waterItem);

        JMenuItem colorItem = new JMenuItem("Color mode");
        colorItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                c.setWaterMode(false);
            }
        } );

        fileMenu.add(colorItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }
}
