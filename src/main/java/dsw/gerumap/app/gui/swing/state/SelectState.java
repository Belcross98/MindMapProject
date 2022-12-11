package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

public class SelectState extends State{

    private HashMap<ElementPainter,Color> rememberColor = new HashMap<>();
    private TitlePainter selected;

    @Override
    public void mousePressed(Point pos, MapView mapView) {

        for(Map.Entry<ElementPainter,Color> entry: rememberColor.entrySet()){

            entry.getKey().getElement().setColor(entry.getValue());
            mapView.getSelectedPainters().removeAll(mapView.getSelectedPainters());
            mapView.repaint();

        }


        for (ElementPainter p: mapView.getPainters())

        {

            if(p.elementAt(pos)){

                if(!(rememberColor.containsKey(p)))
                {
                    rememberColor.put(p,p.getElement().getColor());
                    mapView.getSelectedPainters().add(p);
                }

                p.getElement().setColor(Color.BLUE);
                mapView.repaint();

            }

        }



    }

    @Override
    public void mouseDragged(Point pos, MapView mapView) {

            System.out.println("WHY U NO ENTER HEREEEE");
            Title select = new Title(5, Color.BLACK, "", new Dimension(30, 30), pos, "", new Ellipse2D.Float(50, 50, 50, 50));
            int width = (int) select.getSize().getWidth();
            int height = (int) select.getSize().getHeight();
            Dimension newDimension = new Dimension(width - pos.x,height-pos.y);
            select.setSize(newDimension);
            selected = new TitlePainter(select);
            mapView.addPainter(selected);
            mapView.repaint();

    }

    @Override
    public void mouseReleased(Point pos, MapView mapView) {

        System.out.println("Pusten sam");

    }


}
