package dsw.gerumap.app.gui.swing.grapheditor.painters;

import com.sun.tools.javac.Main;
import dsw.gerumap.app.gui.swing.grapheditor.model.DiagramElement;
import dsw.gerumap.app.gui.swing.grapheditor.model.Link;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.*;
@Getter
@Setter
public class LinkPainter extends ElementPainter {

    private int xOffset  = 10;
    private int yOffset = 10;

    private int negativeX = -xOffset;
    private int negativeY = -yOffset;
    private Shape shape;

    public LinkPainter(DiagramElement element) {
        super(element);
    }


    @Override
    public void paint(Graphics2D g) {

        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(element.getCurrentColor());
        g2.setStroke(new BasicStroke(element.getWidth()));
        Link link = (Link) element;
        shape=new GeneralPath();

        if(link.getToPoint().getY() > link.getFromPoint().getY())
            yOffset = negativeY;
        else
            yOffset = Math.abs(negativeY);
        if(link.getToPoint().getX() > link.getFromPoint().getX())
            xOffset = negativeX;
        else
            xOffset = Math.abs(negativeX);


        ((GeneralPath)shape).moveTo(link.getFromPoint().getX() + xOffset,link.getFromPoint().getY() - yOffset);

        ((GeneralPath)shape).lineTo(link.getToPoint().getX() + xOffset,link.getToPoint().getY()-yOffset);

        ((GeneralPath)shape).lineTo(link.getToPoint().getX() - xOffset,link.getToPoint().getY() + yOffset);

        ((GeneralPath)shape).lineTo(link.getFromPoint().getX() - xOffset,link.getFromPoint().getY() + yOffset);

        ((GeneralPath)shape).closePath();

        link.setShape(shape);
        g2.drawLine((int) link.getFromPoint().getX(), (int) link.getFromPoint().getY(), (int) link.getToPoint().getX(), (int) link.getToPoint().getY());
        //g2.draw(shape);


    }



    @Override
    public boolean elementAt(Point2D pos) {

        Link link = (Link) element;
        AffineTransform affineTransform = MainFrame.getInstance().getProjectView().getMapView().getAffineTransform();
        if(link.getShape().contains(pos))
            return true;

        return  false;
    }
}
