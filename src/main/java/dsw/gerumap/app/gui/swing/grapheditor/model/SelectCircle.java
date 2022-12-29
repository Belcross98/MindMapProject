package dsw.gerumap.app.gui.swing.grapheditor.model;

import dsw.gerumap.app.maprepository.observer.IPublisher;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Point2D;
@Getter
@Setter

public class SelectCircle extends DiagramElement implements IPublisher {


    private Dimension size;
    private Point2D Position;
    private Shape shape;

    public SelectCircle(int width, Color color, String description, Dimension size, Point2D pos) {
        super(width, color, description);
        this.size = size;
        this.Position = pos;
    }


    public void setSize(Dimension size) {
        notifySubscribers(this);
        this.size = size;
    }
}
