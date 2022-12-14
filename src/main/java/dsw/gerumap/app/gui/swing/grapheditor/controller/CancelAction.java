package dsw.gerumap.app.gui.swing.grapheditor.controller;

import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().getSettings().setVisible(false);
        MainFrame.getInstance().getProjectView().getSettings().getJTextField().setText("");
        MainFrame.getInstance().getProjectView().getSettings().getJTextField2().setText("");
        MainFrame.getInstance().getProjectView().getMapView().repaint();
    }
}
