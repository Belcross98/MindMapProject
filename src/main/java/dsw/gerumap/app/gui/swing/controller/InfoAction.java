package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class InfoAction extends AbstractGerumapAction {

    public InfoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/information.png"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION,"Info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Info info = new Info(MainFrame.getInstance(), "Info", false);
        info.setVisible(true);
    }
}
