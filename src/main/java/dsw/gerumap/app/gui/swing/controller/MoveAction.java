package dsw.gerumap.app.gui.swing.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MoveAction extends AbstractGerumapAction{

    public MoveAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/move.png")); ///relativna putanja u okviru projekta!
        putValue(NAME, "Move");
        putValue(SHORT_DESCRIPTION, "Move");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
