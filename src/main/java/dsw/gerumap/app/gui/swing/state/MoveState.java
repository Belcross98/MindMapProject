package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.command.AbstractCommand;
import dsw.gerumap.app.gui.swing.grapheditor.model.Link;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.LinkPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.ProjectView;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.commands.AddLinkCommand;
import dsw.gerumap.app.maprepository.commands.MoveCommand;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class MoveState extends State{

    private Point2D originalPosition = null;
    private Title temp = null;

    @Override
    public void mousePressed(Point pos, MapView mapView) {

        AffineTransform affineTransform = MainFrame.getInstance().getProjectView().getMapView().getAffineTransform();

        originalPosition = new Point((int) ((pos.x - affineTransform.getTranslateX()) / affineTransform.getScaleX()), (int) ((pos.y - affineTransform.getTranslateX()) / affineTransform.getScaleY()));


        for(ElementPainter painter: mapView.getSelectedPainters())
        {

            if(painter instanceof LinkPainter)
                continue;

            Title title = (Title) painter.getElement();

            if(painter.elementAt(pos)){

                //originalPosition = pos;
                temp = title;
                break;
            }

        }


    }

    @Override
    public void mouseDragged(Point pos, MapView mapView) {

        AffineTransform affineTransform = MainFrame.getInstance().getProjectView().getMapView().getAffineTransform();

        if(originalPosition == null)
            return;

        if(temp == null){
            ProjectView projectView = MainFrame.getInstance().getProjectView();
            MapView currMapView = (MapView) projectView.getTabbedPane().getSelectedComponent();
            currMapView.pan(pos.getX() - originalPosition.getX(), pos.getY() - originalPosition.getY());
            System.out.println("ABCDEFG");
            return;
        }


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

                    System.out.println(temp.getLinks().size()+" Aaaaaaaaaaaaa");
                    System.out.println("Broj Paintera jee" +mapView.getPainters().size());

                    for(Link link : temp.getLinks()){


                        LinkPainter l = (LinkPainter) mapView.getPainterFor(link);
                        mapView.getPainters().remove(l);
                        Point2D a = temp.getPosition();
                        int xFOffset = (int) (temp.getSize().getWidth()/2);
                        int yFOffset = (int) (temp.getSize().getHeight()/2);
                        Point2D b = new Point((int) (a.getX()+xFOffset), (int) (a.getY()+yFOffset));
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

        AbstractCommand abstractCommand = new MoveCommand(originalPosition, pos, mapView.getSelectedPainters());
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(abstractCommand);

        originalPosition = null;
        temp = null;

    }
}