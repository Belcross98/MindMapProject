package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.gui.swing.command.AbstractCommand;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.tree.MapTreeImplementation;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.commands.AddTitleCommand;
import dsw.gerumap.app.maprepository.implementation.MindMap;

import java.awt.*;
import java.awt.geom.Ellipse2D;



public class AddTittleState extends State{

    @Override
    public void mousePressed(Point pos, MapView mapView) {

        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        if(!(selected.getMapNode() instanceof MindMap)){

            MainFrame.getInstance().getMessageGenerator().messageGenerate(EventType.YOU_HAVE_TO_SELECT_MINDMAP);
            return;

        }
        AbstractCommand abstractCommand = new AddTitleCommand(pos);
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(abstractCommand);

    }

    @Override
    public void mouseDragged(Point pos, MapView mapView) {
        //Nista
    }

    @Override
    public void mouseReleased(Point pos, MapView mapView) {
        //Nista
    }
}
