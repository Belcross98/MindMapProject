package dsw.gerumap.app.gui.swing.grapheditor.model;


import dsw.gerumap.app.gui.swing.grapheditor.painters.LinkPainter;
import dsw.gerumap.app.maprepository.observer.IPublisher;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

@Getter
@Setter

public class Title extends DiagramElement implements IPublisher {

    protected Dimension size;
    protected String name;
    protected Point2D position;
    protected ArrayList<LinkPainter> links;

    protected Shape shape;

    public Title(int width, Color color, String description, Dimension size, Point2D position, String name) {
        super(width, color, description);
        this.size = size;
        this.name = name;
        this.position = position;
        links = new ArrayList<>();
    }


    public void setSize(Dimension size) {
        notifySubscribers(this);
        this.size = size;
    }

    public void addLink(LinkPainter link){
        notifySubscribers(this);
        links.add(link);
    }

    public void removeLink(LinkPainter link){
        notifySubscribers(this);
        links.remove(link);
    }


    public void setPosition(Point2D position) {
        notifySubscribers(this);
        this.position = position;
    }
}
