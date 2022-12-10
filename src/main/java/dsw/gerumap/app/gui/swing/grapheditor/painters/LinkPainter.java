package dsw.gerumap.app.gui.swing.grapheditor.painters;

import dsw.gerumap.app.gui.swing.grapheditor.model.DiagramElement;
import dsw.gerumap.app.gui.swing.grapheditor.model.Link;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;

import java.awt.*;

public class LinkPainter extends ElementPainter {

    public LinkPainter(DiagramElement element) {
        super(element);
    }


    @Override
    public void paint(Graphics2D g) {

        g.setPaint(element.getColor());

        g.setStroke(new BasicStroke(element.getWidth()));


        Link link = (Link) element;
        Title from = (Title) link.getFrom();
        Title to = (Title) link.getTo();

        g.drawLine(from.getPosition().x, from.getPosition().y, to.getPosition().x, to.getPosition().y);


    }

    @Override
    public boolean elementAt(Point pos) {
        return false;
    }
}
