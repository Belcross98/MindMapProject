package dsw.gerumap.app.gui.swing.grapheditor.model;

import lombok.Getter;
import lombok.Setter;

import javax.swing.text.Position;
import java.awt.*;

@Getter
@Setter

public class Link extends DiagramElement {

    protected DiagramElement from;
    protected DiagramElement to;
    protected Point fromPoint;
    protected Point toPoint;

    public Link(int width, Color color, String description, DiagramElement from, DiagramElement to,Point fromPoint,Point toPoint) {
        super(width, color, description);
        this.from = from;
        this.to = to;
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;

    }
}
