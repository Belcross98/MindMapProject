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

        int stringWidth = g2.getFontMetrics().stringWidth(element.getName());

        g2.setPaint(element.getCurrentColor());

        g2.setStroke(new BasicStroke(element.getWidth()));
        System.out.println("++++++++++++++++++++++++++++++++++++" + shape);


        if (element instanceof Title){
            Title  title = (Title) element;
            //title.setSize(new Dimension(10 +stringWidth * 4, title.getSize().height));
            String name = title.getName();
            shape = new Ellipse2D.Float(title.getPosition().x,title.getPosition().y,title.getSize().width,title.getSize().height);
            title.setShape(shape);
            g2.draw(shape);
            g2.drawString(name, (int)(title.getPosition().getX() + shape.getBounds().getWidth() / 2 - stringWidth / 2),
                    ((int)(title.getPosition().getY() + shape.getBounds().getHeight() / 2)));
        }

    }

    @Override
    public boolean elementAt(Point pos) {
        return getShape().contains(pos);
    }
}
