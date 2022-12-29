package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.gui.swing.command.AbstractCommand;
import dsw.gerumap.app.gui.swing.grapheditor.model.Link;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.LinkPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.commands.DeleteCommand;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeleteState extends State{


    @Override
    public void mousePressed(Point pos, MapView mapView) {
        AbstractCommand command = new DeleteCommand(pos);
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
    }

    @Override
    public void mouseDragged(Point pos, MapView mapView) {
        //Nista
    }

    @Override
    public void mouseReleased(Point pos, MapView mapView) {
        //Nista
    }

    public void deleteFromTree(MapNode del){
        MapTreeItem toDelete = MainFrame.getInstance().getMapTree().getNode(del);
        MainFrame.getInstance().getMapTree().removeChild(toDelete);
    }
}
