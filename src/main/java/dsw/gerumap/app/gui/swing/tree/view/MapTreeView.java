package dsw.gerumap.app.gui.swing.tree.view;

import dsw.gerumap.app.gui.swing.tree.controller.MapTreeCellEditor;
import dsw.gerumap.app.gui.swing.tree.controller.MapTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class MapTreeView extends JTree {

    public MapTreeView(DefaultTreeModel defaultTreeModel){

        this.setModel(defaultTreeModel);
        MapTreeCellRenderer ruTreeCellRenderer = new MapTreeCellRenderer();
        this.setCellRenderer(ruTreeCellRenderer);
        this.addTreeSelectionListener(new MapTreeSelectionListener());
        this.setCellEditor(new MapTreeCellEditor(this,ruTreeCellRenderer));
        this.setEditable(true);
    }
}
