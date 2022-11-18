package dsw.gerumap.app.gui.swing.tree.view;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class MapTreeCellRenderer  extends DefaultTreeCellRenderer {

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int red,boolean focus){
        super.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,red,focus);
        URL imageURL = null;

        if(((MapTreeItem)value).getMapNode() instanceof ProjectExplorer){
            imageURL = getClass().getResource("/images/tdiagram.png");
        }
        else if(((MapTreeItem)value).getMapNode() instanceof Project){
            imageURL = getClass().getResource("/images/tproject.png");
        }
        else if(((MapTreeItem)value).getMapNode() instanceof MindMap){
            imageURL = getClass().getResource("/images/tmap.png");
        }
        else if (((MapTreeItem)value).getMapNode() instanceof Element) {
          imageURL = getClass().getResource("/images/telement.png");
        }

        Icon icon = null;
        if(imageURL!=null){
            icon = new ImageIcon(imageURL);
        }
        this.setIcon(icon);

        return this;
    }

}
