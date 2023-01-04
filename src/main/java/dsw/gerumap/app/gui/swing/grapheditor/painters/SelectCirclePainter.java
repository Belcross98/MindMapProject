package dsw.gerumap.app.gui.swing.grapheditor.painters;

import dsw.gerumap.app.gui.swing.grapheditor.model.DiagramElement;
import dsw.gerumap.app.gui.swing.grapheditor.model.SelectCircle;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class SelectCirclePainter extends ElementPainter{


    public SelectCirclePainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g) {


        Graphics2D g2 = (Graphics2D) g;
        SelectCircle or=(SelectCircle) element;

        g2.setPaint(element.getCurrentColor());
        g2.setStroke(new BasicStroke(element.getWidth()));

        Shape shape;
        shape=new GeneralPath();
        or.setShape(shape);
        ((GeneralPath)shape).moveTo(or.getPosition().getX()+or.getSize().getWidth()/2,or.getPosition().getY());

        ((GeneralPath)shape).quadTo(or.getPosition().getX()+or.getSize().getWidth(), or.getPosition().getY(),
                or.getPosition().getX()+or.getSize().getWidth(), or.getPosition().getY()+or.getSize().getHeight()/2);

        ((GeneralPath)shape).quadTo(or.getPosition().getX()+or.getSize().getWidth(), or.getPosition().getY()+or.getSize().getHeight(),
                or.getPosition().getX()+or.getSize().getWidth()/2, or.getPosition().getY()+or.getSize().getHeight());

        ((GeneralPath)shape).quadTo(or.getPosition().getX(), or.getPosition().getY()+or.getSize().getHeight(),
                or.getPosition().getX(), or.getPosition().getY()+or.getSize().getHeight()/2);


        ((GeneralPath)shape).quadTo(or.getPosition().getX(), or.getPosition().getY(),
                or.getPosition().getX()+or.getSize().getWidth()/2,or.getPosition().getY());


        g2.draw(shape);
    }




    @Override
    public boolean elementAt(Point2D pos) {

        SelectCircle or = (SelectCircle) element;

        if(or.getShape().contains(pos))
            return true;

        return false;
    }
}
