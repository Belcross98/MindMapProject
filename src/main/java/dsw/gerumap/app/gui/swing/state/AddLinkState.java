package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.grapheditor.model.Link;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.LinkPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;


import java.awt.*;
import java.awt.event.MouseEvent;

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
        if(link.getFrom() == null)
           return;
        //hande this with message generator

        linkPainter = new LinkPainter(link);

    }

    @Override
    public void mouseDragged(Point pos, MapView mapView) {

        if(link.getFrom() == null)
            return;

        mapView.removePainter(linkPainter);
        link.setToPoint(pos);
        linkPainter.setElement(link);
        mapView.addPainter(linkPainter);
        mapView.repaint();

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

        link.setFromPoint(from.getPosition());
        link.setToPoint(to.getPosition());
        linkPainter.setElement(link);
        mapView.addPainter(linkPainter);
        mapView.repaint();



    }
}
