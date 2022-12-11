package dsw.gerumap.app.gui.swing.grapheditor.painters;

import dsw.gerumap.app.gui.swing.grapheditor.model.DiagramElement;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Ellipse2D;

@Getter
@Setter

public class TitlePainter extends ElementPainter {

    protected Shape shape;

    public TitlePainter(DiagramElement element) {
        super(element);
    }


    @Override
    public void paint(Graphics2D g) {

        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(element.getColor());

        g2.setStroke(new BasicStroke(element.getWidth()));
        System.out.println("++++++++++++++++++++++++++++++++++++" + shape);


        if (element instanceof Title){
            Title  title = (Title) element;
            shape = new Ellipse2D.Float(((Title) element).getPosition().x,((Title) element).getPosition().y,((Title) element).getSize().width,((Title) element).getSize().height);
            ((Title) element).setShape(shape);
            g2.draw(shape);
            g2.drawString(title.getName(), (int)(title.getPosition().getX() + shape.getBounds().getWidth() / 2),
                    ((int)(title.getPosition().getY() + shape.getBounds().getHeight() / 2)));
        }

    }

    @Override
    public boolean elementAt(Point pos) {
        return getShape().contains(pos);
    }
}
