package dsw.gerumap.app.gui.swing.controller;

import java.awt.event.ActionEvent;

public class UndoAction extends AbstractGerumapAction{

    public UndoAction(){

        putValue(SMALL_ICON, loadIcon("/images/undo.png")); ///relativna putanja u okviru projekta!
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo Action");
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
