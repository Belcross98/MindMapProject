package dsw.gerumap.app.gui.swing.grapheditor.painters;

import dsw.gerumap.app.gui.swing.grapheditor.model.DiagramElement;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Point2D;

@Getter
@Setter

public abstract class ElementPainter {

    protected DiagramElement element;

    public ElementPainter(DiagramElement element){
        this.element = element;
    }

    public abstract void paint(Graphics2D g);

    public abstract boolean elementAt(Point2D pos);
}
