package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstractGerumapAction{

    public RedoAction(){

        putValue(SMALL_ICON, loadIcon("/images/redo.png")); ///relativna putanja u okviru projekta!
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo Action");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ApplicationFramework.getInstance().getGui().getCommandManager().doCommand();
    }
}
