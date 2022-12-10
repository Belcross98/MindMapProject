package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.state.StateManager;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddTitleAction extends AbstractGerumapAction{


    public AddTitleAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/create.png")); ///relativna putanja u okviru projekta!
        putValue(NAME, "Add title");
        putValue(SHORT_DESCRIPTION, "Add title");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = MainFrame.getInstance().getProjectView().getStateManager();
         stateManager.setAddTittleState();

    }
}
