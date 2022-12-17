package dsw.gerumap.app.gui.swing.grapheditor.model;

import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.maprepository.observer.IPublisher;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter

public class DiagramElement extends Element implements IPublisher {

    int width;
    Color color;
    Color currentColor;
    protected String description;

    public DiagramElement(int width, Color color, String description){
        this.currentColor = color;
        this.width = width;
        this.color = color;
        this.description = description;


    }

    public void setColor(Color color) {
        notifySubscribers(this);
        this.color = color;
    }
}
