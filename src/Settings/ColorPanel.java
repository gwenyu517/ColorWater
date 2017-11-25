package Settings;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorPanel extends JPanel
                        implements ChangeListener {
    public JLabel banner;
    public JPanel previewPanel;
    public JColorChooser pcc1;
    public JColorChooser pcc2;

    public ColorPanel(){
        super(new BorderLayout());

        // this.setPreferredSize(new Dimension(300,65);

        previewPanel = new JPanel(new BorderLayout());
        previewPanel.setPreferredSize(new Dimension(300,65));
        previewPanel.setBorder(BorderFactory.createTitledBorder("Preview"));

        pcc1 = new JColorChooser();
        pcc1.getSelectionModel().addChangeListener(this);
        pcc1.setBorder(BorderFactory.createTitledBorder("ehhh"));

        pcc1.setPreviewPanel(previewPanel);

        AbstractColorChooserPanel panels[] = {new PresetColorPanel()};
        pcc1.setChooserPanels(panels);
        pcc1.setColor(previewPanel.getBackground());

        add(previewPanel, BorderLayout.PAGE_START);
        add(pcc1, BorderLayout.PAGE_END);
    }

    public void stateChanged(ChangeEvent e){
        Color newColor = pcc1.getColor();
        previewPanel.setBackground(newColor);
    }
}
