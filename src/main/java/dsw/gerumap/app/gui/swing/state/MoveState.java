package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.grapheditor.model.Link;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.LinkPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.ProjectView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class MoveState extends State{

    private Point2D originalPosition = null;
    private Title temp = null;

    @Override
    public void mousePressed(Point pos, MapView mapView) {


        for(ElementPainter painter: mapView.getSelectedPainters())
        {

            if(painter instanceof LinkPainter)
                continue;

            Title title = (Title) painter.getElement();

            if(painter.elementAt(pos)){

                originalPosition = pos;
                break;
            }

        }


    }

    @Override
    public void mouseDragged(Point pos, MapView mapView) {



        if(originalPosition == null)
            return;


        for(ElementPainter painter: mapView.getSelectedPainters()){

            if(painter instanceof LinkPainter)
                continue;


            if(painter instanceof TitlePainter) {

                temp = (Title) painter.getElement();
                mapView.getPainters().remove(painter);
                double diffX = (pos.getX() - originalPosition.getX());
                double diffY = (pos.getY() - originalPosition.getY());

                Point newPosition = new Point();
                newPosition.setLocation(diffX + temp.getPosition().getX(), diffY + temp.getPosition().getY());
                temp.setPosition(newPosition);
                painter.setElement(temp);
                mapView.addPainter(painter);


                if(!(temp.getLinks().isEmpty())){

                    for(LinkPainter l : temp.getLinks()){

                        mapView.getPainters().remove(l);
                        Point2D a = temp.getPosition();
                        int xFOffset = (int) (temp.getSize().getWidth()/2);
                        int yFOffset = (int) (temp.getSize().getHeight()/2);
                        Point2D b = new Point((int) (a.getX()+xFOffset), (int) (a.getY()+yFOffset));
                        Link link = (Link) l.getElement();

                        if(link.getFrom() == temp)
                            link.setFromPoint(b);
                        else
                            link.setToPoint(b);

                        l.setElement(link);
                        mapView.getPainters().add(l);

                    }


                }

            }


        }
        originalPosition= pos;
    }

    @Override
    public void mouseReleased(Point pos, MapView mapView) {

        originalPosition = null;
        temp = null;
    }
}