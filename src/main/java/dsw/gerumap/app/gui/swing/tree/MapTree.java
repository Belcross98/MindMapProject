package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;


public interface MapTree {

    MapTreeView getTree();

    MapTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(MapTreeItem parent);
    MapTreeItem getSelectedNode();
    void addElement(MapTreeItem parent, MapNode child);
    MapTreeItem getNode(MapNode child);
    MapTreeItem getRoot();

    void removeChild(MapTreeItem parent);
}
