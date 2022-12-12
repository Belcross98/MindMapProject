package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.state.StateManager;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteItemAction extends  AbstractGerumapAction{

    public DeleteItemAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/deleteItem.png")); ///relativna putanja u okviru projekta!
        putValue(NAME, "Delete item");
        putValue(SHORT_DESCRIPTION, "Delete item");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        MainFrame.getInstance().getProjectView().startDeleteState();
    }
}
