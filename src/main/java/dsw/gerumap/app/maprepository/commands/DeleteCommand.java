package dsw.gerumap.app.maprepository.commands;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.gui.swing.command.AbstractCommand;
import dsw.gerumap.app.gui.swing.grapheditor.model.DiagramElement;
import dsw.gerumap.app.gui.swing.grapheditor.model.Link;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.LinkPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.Element;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DeleteCommand extends AbstractCommand {

    private Point position;

    private Element currentObject;

    public DeleteCommand(Point position){
        this.position = position;
    }


    @Override
    public void doCommand() {

        MapView mapView = MainFrame.getInstance().getProjectView().getMapView();
        ElementPainter painter = mapView.elementPainter(position);

        if(painter == null){
            AppCore.getInstance().getMessageGenerator().messageGenerate(EventType.NO_ELEMENT_FOUND);
            return;
        }
        currentObject = painter.getElement();

        for(ElementPainter p : mapView.getSelectedPainters()){

            p.getElement().removeSubscriber(mapView);



            if(p instanceof LinkPainter){


                ((Title)((Link)p.getElement()).getFrom()).removeLink((LinkPainter) p);
                ((Title)((Link)p.getElement()).getTo()).removeLink((LinkPainter) p);
                mapView.getPainters().remove(p);
                mapView.getMindMap().removeChild(p.getElement());
                deleteFromTree(p.getElement());



            }


            if(p instanceof TitlePainter) {

                Title title = (Title) p.getElement();
                mapView.removePainter(p);
                mapView.getMindMap().removeChild(p.getElement());
                MapTreeItem toDelete = MainFrame.getInstance().getMapTree().getNode(p.getElement());
                MainFrame.getInstance().getMapTree().removeChild(toDelete);

                if(!(title.getLinks().isEmpty())){

                    HashMap<Title,LinkPainter> linked = new HashMap<>();

                    for(LinkPainter linkPainter:title.getLinks()) {

                        mapView.getPainters().remove(linkPainter);
                        deleteFromTree(linkPainter.getElement());
                        linked.put( ((Title) ((Link)linkPainter.getElement()).getFrom()),linkPainter);
                        linked.put( ((Title) ((Link)linkPainter.getElement()).getTo()),linkPainter);


                    }

                    for(Map.Entry<Title,LinkPainter> entry: linked.entrySet()){

                        entry.getKey().removeLink(entry.getValue());

                    }
                    linked.clear();

                }

            }
        }

        mapView.getSelectedPainters().clear();
    }

    @Override
    public void undoCommand() {

        MapView mapView = MainFrame.getInstance().getProjectView().getMapView();

        if(currentObject instanceof  Title){
            mapView.getMindMap().addChild(currentObject);
            mapView.addPainter(new TitlePainter((DiagramElement) currentObject));
        } else if (currentObject instanceof Link) {
            mapView.getMindMap().addChild(currentObject);
            mapView.addPainter(new LinkPainter((DiagramElement) currentObject));
        }

        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        MainFrame.getInstance().getMapTree().addElement(selected,currentObject);



    }

    public void deleteFromTree(MapNode del){
        MapTreeItem toDelete = MainFrame.getInstance().getMapTree().getNode(del);
        MainFrame.getInstance().getMapTree().removeChild(toDelete);

    }
}
