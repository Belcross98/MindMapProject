package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddAuthorAction extends AbstractGerumapAction{

    public AddAuthorAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/tauthor.png"));
        putValue(NAME, "Add author");
        putValue(SHORT_DESCRIPTION,"Add author");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        if(selected == null){
            AppCore.getInstance().getMessageGenerator().messageGenerate(EventType.NODE_NOT_SELECTED);
            return;
        }
        if(selected.getMapNode() instanceof Project){
            String newAuthor = JOptionPane.showInputDialog("Enter author name:");
            ((Project)selected.getMapNode()).setAuthor(newAuthor);
        }

    }
}
