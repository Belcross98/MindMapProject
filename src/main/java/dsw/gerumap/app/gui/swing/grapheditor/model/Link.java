package dsw.gerumap.app.gui.swing.grapheditor.model;

import dsw.gerumap.app.maprepository.observer.IPublisher;
import lombok.Getter;
import lombok.Setter;

import javax.swing.text.Position;
import java.awt.*;
import java.awt.geom.Point2D;

@Getter
@Setter

public class Link extends DiagramElement implements IPublisher {

    protected transient DiagramElement from;
    protected transient DiagramElement to;
    protected  Point2D fromPoint;
    protected  Point2D toPoint;
    protected Shape shape;

    public Link(int width, Color color, String description, DiagramElement from, DiagramElement to,Point2D fromPoint,Point2D toPoint) {
        super(width, color, description);
        this.from = from;
        this.to = to;
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
        this.setName("link");
        notifySubscribers(this);

    }


    public void setToPoint(Point2D toPoint) {

        notifySubscribers(this);
        this.toPoint = toPoint;
    }

    public void setFromPoint(Point2D fromPoint) {
        notifySubscribers(this);
        this.fromPoint = fromPoint;
    }
}
