package dsw.gerumap.app.gui.swing.grapheditor.workspace;

import dsw.gerumap.app.gui.swing.grapheditor.controller.CancelAction;
import dsw.gerumap.app.gui.swing.grapheditor.controller.SaveAction;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Getter
@Setter

public class Settings extends JDialog {

    private JColorChooser jColorChooser;

    private  JTextField jTextField;

    private  JTextField jTextField2;


    public Settings(Frame parent){

        setTitle("Settings");
        setSize(600, 500);
        setLocationRelativeTo(parent);
        jTextField = new JTextField();
        jTextField2 = new JTextField();
        JButton jButton1 = new JButton("Save");
        JButton jButton2 = new JButton("Cancel");
        jButton1.setPreferredSize(new Dimension(100, 50));
        jButton2.setPreferredSize(new Dimension(100, 50));
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        JPanel northJPanel = new JPanel();
        add(northJPanel, BorderLayout.NORTH);
        BoxLayout boxLayout = new BoxLayout(northJPanel, BoxLayout.X_AXIS);
        northJPanel.setLayout(boxLayout);
        jTextField.setMaximumSize(new Dimension( 100, 50));
        northJPanel.add(jTextField);
        northJPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        northJPanel.add(jButton1);
        northJPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        northJPanel.add(jButton2);
        jButton2.addActionListener(new CancelAction());
        jButton1.addActionListener(new SaveAction());


        JPanel centerPanel = new JPanel();
        add(centerPanel, BorderLayout.CENTER);
        jColorChooser = new JColorChooser();
        centerPanel.add(jColorChooser);

        JPanel southPanel = new JPanel();
        add(southPanel, BorderLayout.SOUTH);
        southPanel.add(jTextField2);
        jTextField2.setPreferredSize(new Dimension(50, 30));



    }
}
