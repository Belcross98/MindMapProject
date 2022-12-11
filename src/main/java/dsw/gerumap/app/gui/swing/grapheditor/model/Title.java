package dsw.gerumap.app.gui.swing.grapheditor.model;


import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter

public class Title extends DiagramElement {

    protected Dimension size;

    protected String name;
    protected Point position;
    protected  Shape shape;


    public Title(int width, Color color, String description, Dimension size, Point position, String name, Shape shape) {
        super(width, color, description);
        System.out.println(shape + "////////////////////////");
        this.size = size;
        this.shape = shape;
        this.name = name;
        this.position = position;
    }
}
