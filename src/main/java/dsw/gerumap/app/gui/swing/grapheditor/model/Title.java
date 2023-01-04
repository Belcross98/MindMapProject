package dsw.gerumap.app.gui.swing.grapheditor.model;


import dsw.gerumap.app.gui.swing.grapheditor.painters.LinkPainter;
import dsw.gerumap.app.gui.swing.view.MainFrame;
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
    protected  Point2D position;
    protected transient ArrayList<Link> links;

    protected Shape shape;

    public Title(int width, Color color, String description, Dimension size, Point2D position, String helloWorld) {
        super(width, color, description);
        this.size = size;
        this.position = position;
        links = new ArrayList<>();
        this.setName("Title");
        notifySubscribers(this);
    }


    public void setSize(Dimension size) {
        notifySubscribers(this);
        setChanged(true);
        this.size = size;
    }

    public void addLink(Link link){
        notifySubscribers(this);
        setChanged(true);
        links.add(link);
    }

    public void removeLink(Link link){
        notifySubscribers(this);
        setChanged(true);
        links.remove(link);
    }


    public void setPosition(Point2D position) {
        notifySubscribers(this);
        setChanged(true);
        this.position = position;
    }
}
