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

    protected Shape shape;

    public Title(int width, Color color, String description, Dimension size, Point position, String name) {
        super(width, color, description);
        this.size = size;
        this.name = name;
        this.position = position;
    }
}
