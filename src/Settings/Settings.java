package Settings;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.KeyEvent;


public class Settings extends JPanel {
    public Settings(){
        super(new GridLayout(1,1));

        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent brushPanel = makeBrushPanel();
        tabbedPane.addTab("Brush", brushPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent paintPanel = makePaintPanel();
        tabbedPane.addTab("Paint", paintPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);


    //    JComponent canvasPanel = makeTextPanel("Canvas");
     //   tabbedPane.addTab("Canvas", canvasPanel);
     //   canvasPanel.setPreferredSize(new Dimension(500, 1000));
     //   tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);


        add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    protected JPanel makeBrushPanel(){
        JPanel panel = new JPanel (false);

        final int SIZE_MIN = 0;
        final int SIZE_MAX = 30;
        final int SIZE_INIT = 15;

        //Create the label.
        JLabel sliderLabel = new JLabel("Brush Size", JLabel.LEFT);
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Create the slider.
        JSlider brushSize = new JSlider(JSlider.HORIZONTAL,
                SIZE_MIN, SIZE_MAX, SIZE_INIT);

        brushSize.addChangeListener(new SliderListener());

        //Turn on labels at major tick marks
        brushSize.setMajorTickSpacing(10);
        brushSize.setMinorTickSpacing(1);
        brushSize.setPaintTicks(true);
        brushSize.setPaintLabels(true);
        brushSize.setBorder(
                BorderFactory.createEmptyBorder(0,0,10,0));

        // Put everything together
        panel.add(sliderLabel);
        panel.add(brushSize);
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


        // ToDo: CREATE PREVIEW PANEL

        return panel;
    }

    private JPanel makePaintPanel(){
        JPanel panel = new JPanel();

        // Color section
        JLabel presetLabel = new JLabel("Preset", JLabel.LEFT);

        JComponent newContentPane = new ColorPanel();
        newContentPane.setOpaque(true);
        panel.add(newContentPane);


        return panel;
    }


}



class SliderListener implements ChangeListener {
    public void stateChanged(ChangeEvent e){
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()){
            int val = (int)source.getValue();

        // ToDo: adjust brush size
     //       System.out.println(val);
        }
    }
}

class PresetColorListener implements ChangeListener {
    public void stateChanged(ChangeEvent e){
      //  Color newColor = tcc.getColor();

            // ToDo: adjust brush size
            //       System.out.println(val);

    }
}
