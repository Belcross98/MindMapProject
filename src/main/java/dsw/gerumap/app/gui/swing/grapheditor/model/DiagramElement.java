package dsw.gerumap.app.gui.swing.grapheditor.model;

import dsw.gerumap.app.maprepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter

public class DiagramElement extends Element {

    int width;
    Color color;
    protected String description;

    public DiagramElement(int width, Color color, String description){
        this.width = width;
        this.color = color;
        this.description = description;


    }

}
