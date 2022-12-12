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

        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(element.getCurrentColor());

        g2.setStroke(new BasicStroke(element.getWidth()));
      //  g2.setColor(new Color());


        Link link = (Link) element;
        Title from = (Title) link.getFrom();
        Title to = (Title) link.getTo();

        g2.drawLine(from.getPosition().x, from.getPosition().y, to.getPosition().x, to.getPosition().y);


    }

    @Override
    public boolean elementAt(Point pos) {
        Link link = (Link) element;
        Title from = (Title) link.getFrom();
        if(from == null) {
            return false;
        }
        return  true;
    }
}
