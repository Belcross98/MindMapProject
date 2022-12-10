package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.state.StateManager;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SelectAction extends AbstractGerumapAction{


    public SelectAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/select.png")); ///relativna putanja u okviru projekta!
        putValue(NAME, "Select");
        putValue(SHORT_DESCRIPTION, "Select");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = MainFrame.getInstance().getProjectView().getStateManager();
        stateManager.setSelectState();
    }
}
