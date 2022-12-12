package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.ProjectView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MoveState extends State{

    private Point originalPosition = null;
    private Title temp = null;

    @Override
    public void mousePressed(Point pos, MapView mapView) {


        System.out.println("BROJ Selektovanih PRE DRAGA =" +mapView.getSelectedPainters().size());
        System.out.println("BROJ UKUPNIH PAINTERA JE " +mapView.getPainters().size());


        for(ElementPainter painter: mapView.getSelectedPainters())
        {
            Title title = (Title) painter.getElement();

            if(painter.elementAt(pos)){

                originalPosition = pos;
                break;
            }

        }


    }

    @Override
    public void mouseDragged(Point pos, MapView mapView) {

        System.out.println(mapView.getSelectedPainters().size()+"Broj selektovanih");

        if(originalPosition == null)
            return;


        for(ElementPainter painter: mapView.getSelectedPainters()){


            temp = (Title) painter.getElement();
            mapView.getPainters().remove(painter);
            double diffX =  (pos.getX()-originalPosition.getX());
            double diffY =  (pos.getY()-originalPosition.getY());

            Point newPosition = new Point();
            newPosition.setLocation(diffX + temp.getPosition().getX(),diffY + temp.getPosition().getY());
            temp.setPosition(newPosition);
            painter.setElement(temp);
            mapView.addPainter(painter);




        }
        originalPosition= pos;
        mapView.repaint();
    }

    @Override
    public void mouseReleased(Point pos, MapView mapView) {

        originalPosition = null;
        temp = null;
    }
}
