package dsw.gerumap.app.gui.swing.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExitAction extends AbstractGerumapAction {

        public ExitAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/xpic.png"));
        putValue(NAME, "Exit");
        putValue(SHORT_DESCRIPTION,"Exit");
    }

        public void actionPerformed(ActionEvent arg0){
        System.exit(0);
    }
}
