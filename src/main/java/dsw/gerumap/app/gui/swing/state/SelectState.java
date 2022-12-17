package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.LinkPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;


public class SelectState extends State{

    private HashMap<ElementPainter,Color> restoreColor = new HashMap<>();
    private Title first = null;
    private Point2D position;
    private TitlePainter titlePainter = null;

    @Override
    public void mousePressed(Point pos, MapView mapView) {


        for(Map.Entry<ElementPainter,Color> entry:restoreColor.entrySet()){

            entry.getKey().getElement().setCurrentColor(entry.getKey().getElement().getColor());
            entry.getKey().getElement().setSelected(false);
            mapView.getSelectedPainters().removeAll(mapView.getSelectedPainters());

        }
        restoreColor.clear();
        for(ElementPainter p:mapView.getPainters()){



            if(p.elementAt(pos)){

                if(!(restoreColor.containsKey(p))){

                    restoreColor.put(p,p.getElement().getColor());
                    mapView.getSelectedPainters().add(p);
                }

                p.getElement().setCurrentColor(Color.BLUE);
                p.getElement().setSelected(true);


            }

        }

    }

    @Override
    public void mouseDragged(Point pos, MapView mapView) {

        int width = 0;
        int height = 0;

        if(first == null){
            first = new Title(5,Color.BLACK, "", new Dimension(30, 15), pos, "");
            position = new Point2D.Double(pos.getX(),pos.getY());
        }

        if(titlePainter == null)
            titlePainter = new TitlePainter(first);
        if(titlePainter.getElement().getSubscribers()!=null)
            titlePainter.getElement().removeSubscriber(mapView);
        titlePainter.getElement().addSubscriber(mapView);
        width = (int)  first.getSize().getWidth();
        height = (int) first.getSize().getHeight();
        mapView.removePainter(titlePainter);
        Dimension newDimension = new Dimension((int) (width + (pos.x - position.getX())/100), (int) (height + (pos.y - position.getY()/2)/100));
        first.setSize(newDimension);
        titlePainter.setElement(first);
        mapView.addPainter(titlePainter);



    }

    @Override
    public void mouseReleased(Point pos, MapView mapView) {

        if(titlePainter == null)
            return;

        for(ElementPainter painter:mapView.getPainters()){

            if(painter == titlePainter || painter instanceof LinkPainter)
                continue;

            if(titlePainter.getShape() == null){

                mapView.removePainter(titlePainter);
                first = null;
                return;
            }

            Title title = (Title) painter.getElement();


            if(titlePainter.getShape().intersects(title.getPosition().getX(),title.getPosition().getY(),title.getSize().width,title.getSize().height)){
                if(!(restoreColor.containsKey(painter))){

                    restoreColor.put(painter,painter.getElement().getColor());
                    mapView.getSelectedPainters().add(painter);
                    }
                painter.getElement().setCurrentColor(Color.BLUE);
                painter.getElement().setSelected(true);

            }


        }

        mapView.removePainter(titlePainter);
        ((Title)titlePainter.getElement()).setSize(null);
        titlePainter.getElement().removeSubscriber(mapView);
        titlePainter = null;
        first = null;
    }


}
