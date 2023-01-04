package dsw.gerumap.app.maprepository.commands;

import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.gui.swing.command.AbstractCommand;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.MindMap;

import java.awt.*;

public class AddTitleCommand extends AbstractCommand {

    private Point position;

    private Title title;

    private  TitlePainter titlePainter;

    public AddTitleCommand(Point position){

        this.position = position;

    }
    @Override
    public void doCommand() {

        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        if(!(selected.getMapNode() instanceof MindMap)){
            MainFrame.getInstance().getMessageGenerator().messageGenerate(EventType.YOU_HAVE_TO_SELECT_MINDMAP);
            return;

        }

        String name = "Title";
        if(name == null || name.isEmpty())
            return;
        title = new Title(5, Color.BLACK, name, new Dimension(120, 60), position, name);
        titlePainter = new TitlePainter(title);
        MapView mapView = MainFrame.getInstance().getProjectView().getMapView();
        mapView.addPainter(titlePainter);
        mapView.getMindMap().addChild(title);
        title.addSubscriber(mapView);
        //error handler ( selectovani node mora da bude mind map )

        MainFrame.getInstance().getMapTree().addElement(selected,title);
    }

    @Override
    public void undoCommand() {

        MapView mapView = MainFrame.getInstance().getProjectView().getMapView();
        mapView.removePainter(titlePainter);
        mapView.getMindMap().removeChild(title);
        title.removeSubscriber(mapView);
        deleteFromTree(title);

    }

    public void deleteFromTree(MapNode del){
        MapTreeItem toDelete = MainFrame.getInstance().getMapTree().getNode(del);
        MainFrame.getInstance().getMapTree().removeChild(toDelete);
    }
}
