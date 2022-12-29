package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.gui.swing.grapheditor.model.Link;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.LinkPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class AddLinkState extends  State{


    private Link link = null;
    private LinkPainter linkPainter = null;

    @Override
    public void mousePressed(Point pos, MapView mapView) {


        link = new Link(5,Color.BLACK,"description",null,null,pos,pos);

        for(ElementPainter painter:mapView.getPainters()){

            if(painter.elementAt(pos) && painter instanceof TitlePainter){
                link.setFrom(painter.getElement());
                break;
            }

        }
        if(link.getFrom() == null){
            MainFrame.getInstance().getMessageGenerator().messageGenerate(EventType.YOU_DONT_HAVE_INITIAL_POINT);
            return;
        }

        //hande this with message generator

        linkPainter = new LinkPainter(link);

    }

    @Override
    public void mouseDragged(Point pos, MapView mapView) {

        if(link.getFrom() == null)
            return;


        if(link.getSubscribers() != null)
            link.removeSubscriber(mapView);
        link.addSubscriber(mapView);
        mapView.removePainter(linkPainter);
        linkPainter.setElement(link);
        link.setToPoint(pos);
        mapView.addPainter(linkPainter);

    }

    @Override
    public void mouseReleased(Point pos, MapView mapView) {

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



    }
}
