package dsw.gerumap.app.gui.swing.grapheditor.painters;

import dsw.gerumap.app.gui.swing.grapheditor.model.DiagramElement;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

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


        if (element instanceof Title){
            Title  title = (Title) element;
            String name = title.getName();
            shape = new Ellipse2D.Float((float) title.getPosition().getX(), (float) title.getPosition().getY(), (float) title.getSize().width, (float) title.getSize().height);
            title.setShape(shape);
            g2.draw(shape);
            g2.drawString(name, (int)(title.getPosition().getX() + shape.getBounds().getWidth() / 2 - stringWidth / 2),
                    ((int)(title.getPosition().getY() + shape.getBounds().getHeight() / 2)));
        }

    }

    @Override
    public boolean elementAt(Point2D pos) {
        AffineTransform affineTransform = MainFrame.getInstance().getProjectView().getMapView().getAffineTransform();
        return getShape().contains(pos.getX() , pos.getY());
    }
}
