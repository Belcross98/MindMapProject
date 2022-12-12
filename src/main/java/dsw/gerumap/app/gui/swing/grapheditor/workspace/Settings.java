package dsw.gerumap.app.gui.swing.grapheditor.workspace;

import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        jTextField.setPreferredSize(new Dimension(10, 10));
        northJPanel.add(jTextField);
        northJPanel.add(jButton1);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = new Color(255, 255, 255);
            }
        });
        northJPanel.add(jButton2);
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });

        JPanel centerPanel = new JPanel();
        add(centerPanel, BorderLayout.CENTER);
        jColorChooser = new JColorChooser();
        centerPanel.add(jColorChooser);




    }
}
