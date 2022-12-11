package dsw.gerumap.app.gui.swing.grapheditor.workspace;

import javax.swing.*;
import java.awt.*;

public class Settings extends JDialog {

    private JColorChooser jColorChooser;

    public Settings(Frame parent){

        setTitle("Settings");
        setSize(900, 800);
        setLocationRelativeTo(parent);
        JTextField jTextField = new JTextField();
        jTextField.setPreferredSize(new Dimension(20, 20));
        JButton jButton1 = new JButton("Save");
        JButton jButton2 = new JButton("Cancel");
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        JPanel northJPanel = new JPanel();
        add(northJPanel, BorderLayout.NORTH);
        northJPanel.setLayout(new BoxLayout(northJPanel, BoxLayout.X_AXIS));
        northJPanel.add(jTextField);
        northJPanel.add(jButton1);
        northJPanel.add(jButton2);

        JPanel centerPanel = new JPanel();
        add(centerPanel, BorderLayout.CENTER);
        jColorChooser = new JColorChooser();
        centerPanel.add(jColorChooser);

    }
}
