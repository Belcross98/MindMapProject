package dsw.gerumap.app.gui.swing.command.implementation;

import dsw.gerumap.app.gui.swing.command.AbstractCommand;
import dsw.gerumap.app.gui.swing.tree.MapTree;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;

public class AddChildCommand extends AbstractCommand {

    private MapTreeItem parent;

    private  MapTreeItem child;

    public AddChildCommand(MapTreeItem parent, MapTreeItem child){
      this.parent = parent;
      this.child = child;
    }

    @Override
    public void doCommand() {

        if(child == null || parent == null)
            return;
        parent.add(child);
        ((MapNodeComposite)parent.getMapNode()).addChild(child.getMapNode());

    }

    @Override
    public void undoCommand() {

        if(child == null || parent == null)
            return;
        child.removeFromParent();
        ((MapNodeComposite)(parent.getMapNode())).removeChild(child.getMapNode());

    }
}
