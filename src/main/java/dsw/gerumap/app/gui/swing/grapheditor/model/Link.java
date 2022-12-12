package dsw.gerumap.app.gui.swing.grapheditor.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter

public class Link extends DiagramElement {

    protected DiagramElement from;
    protected DiagramElement to;

    public Link(int width, Color color, String description, DiagramElement from, DiagramElement to) {
        super(width, color, description);
        this.from = from;
        this.to = to;

    }
}
