package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.state.StateManager;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SettingsAction extends AbstractGerumapAction{

    public SettingsAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/settings.png")); ///relativna putanja u okviru projekta!
        putValue(NAME, "Settings");
        putValue(SHORT_DESCRIPTION, "Settings");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().getSettings().setVisible(true);

    }
}
