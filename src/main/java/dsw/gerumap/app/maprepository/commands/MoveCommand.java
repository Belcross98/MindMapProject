package dsw.gerumap.app.maprepository.commands;

import com.sun.tools.javac.Main;
import dsw.gerumap.app.gui.swing.command.AbstractCommand;
import dsw.gerumap.app.gui.swing.grapheditor.model.DiagramElement;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class MoveCommand extends AbstractCommand {

    private Point2D originalPosition = null;

    private  Point2D endingPosititon = null;

    private List<ElementPainter> selectedPainters;



    public MoveCommand(Point2D originalPosition, Point2D endingPosititon, List<ElementPainter> selectedPainters){
        this.originalPosition = originalPosition;
        this.endingPosititon = endingPosititon;
        this.selectedPainters = new ArrayList<>(selectedPainters);
    }

    @Override
    public void doCommand() {


    }

    @Override
    public void undoCommand() {

        MapView mapView = MainFrame.getInstance().getProjectView().getMapView();

        Point2D position = new Point2D.Float((float) (endingPosititon.getX() - originalPosition.getX()), (float) (endingPosititon.getY()-originalPosition.getY()));


        for(ElementPainter painter : selectedPainters) {
            if (painter.getElement() instanceof Title) {
               Title  title = (Title) painter.getElement();
                System.out.println(new Point2D.Float((float) (title.getPosition().getX() - position.getX()), (float) (title.getPosition().getY()-position.getY())));
                title.setPosition(new Point2D.Float((float) (title.getPosition().getX() - position.getX()), (float) (title.getPosition().getY()-position.getY())));
            }

             }
            mapView.repaint();
        }
}
