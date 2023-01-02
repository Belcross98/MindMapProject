package dsw.gerumap.app.maprepository.commands;

import dsw.gerumap.app.gui.swing.command.AbstractCommand;
import dsw.gerumap.app.gui.swing.grapheditor.model.Link;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.LinkPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.composite.MapNode;

import java.awt.*;
import java.awt.geom.Point2D;

public class AddLinkCommand extends AbstractCommand {

    private LinkPainter linkPainter;

    private Link link;

    private Point pos;

    private Title titleFrom;

    private Title titleTo;

    public AddLinkCommand(LinkPainter linkPainter, Point pos, Link link, Title titleFrom){
        this.linkPainter = linkPainter;
        this.pos = pos;
        this.link = link;
        this.titleFrom = titleFrom;
    }
    @Override
    public void doCommand() {

        MapView mapView = MainFrame.getInstance().getProjectView().getMapView();

        if(link.getFrom() == null)
            return;

        for(ElementPainter painter: mapView.getPainters()){

            if(painter.elementAt(pos) && painter.getElement()!= link.getFrom() && painter instanceof TitlePainter){

                mapView.removePainter(linkPainter);
                link.setTo(painter.getElement());
                break;
            }

        }

        if(link.getTo() == null){

            mapView.getPainters().remove(linkPainter);
            return;
        }


        Title from = (Title) link.getFrom();
        Title to = (Title) link.getTo();

        Point2D a = from.getPosition();
        Point2D b = to.getPosition();

        int xFOffset = (int) (from.getSize().getWidth()/2);
        int yFOffset = (int) (from.getSize().getHeight()/2);
        int xTOffset = (int) (to.getSize().getWidth()/2);
        int yTOffset = (int) (to.getSize().getHeight()/2);

        Point2D c = new Point((int) (a.getX()+xFOffset), (int) (a.getY()+yFOffset));
        Point2D d = new Point((int) (b.getX()+xTOffset), (int) (b.getY()+yTOffset));


        link.setFromPoint(c);
        link.setToPoint(d);



        linkPainter.setElement(link);
        ((Title) link.getFrom()).addLink(linkPainter);
        ((Title) link.getTo()).addLink(linkPainter);
        mapView.addPainter(linkPainter);
        link.addSubscriber(mapView);

        //isto kao kod addTitle error handler
        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        MainFrame.getInstance().getMapTree().addElement(selected,link);

         titleTo = (Title) link.getTo();

    }

    @Override
    public void undoCommand() {

        MapView mapView = MainFrame.getInstance().getProjectView().getMapView();

        mapView.removePainter(linkPainter);

        mapView.getMindMap().removeChild(link);

        deleteFromTree(link);

        titleFrom.getLinks().remove(link);

        titleTo.getLinks().remove(link);


    }

    public void deleteFromTree(MapNode del){
        MapTreeItem toDelete = MainFrame.getInstance().getMapTree().getNode(del);
        MainFrame.getInstance().getMapTree().removeChild(toDelete);

    }
}
