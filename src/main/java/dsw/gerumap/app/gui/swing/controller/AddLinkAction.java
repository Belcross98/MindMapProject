package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.state.StateManager;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

@Getter
@Setter

public class AddLinkAction extends AbstractGerumapAction{

    public AddLinkAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/link.png")); ///relativna putanja u okviru projekta!
        putValue(NAME, "Add link");
        putValue(SHORT_DESCRIPTION, "Add link");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = MainFrame.getInstance().getProjectView().getStateManager();
             stateManager.setAddLinkState();
    }
}
