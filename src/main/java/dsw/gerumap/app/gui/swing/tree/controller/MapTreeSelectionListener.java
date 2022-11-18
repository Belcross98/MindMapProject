package dsw.gerumap.app.gui.swing.tree.controller;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.maprepository.implementation.Project;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class MapTreeSelectionListener implements TreeSelectionListener {

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        MapTreeItem treeItemSelected = (MapTreeItem)path.getLastPathComponent();
        if(treeItemSelected.getMapNode() instanceof Project){
        MainFrame.getInstance().getProjectView().refreshWorkspace(treeItemSelected.getMapNode());
        }
        System.out.println("Selektovani cvor je: " + treeItemSelected.getMapNode().getName());
        System.out.println("getPath: " + e.getPath());

    }
}
