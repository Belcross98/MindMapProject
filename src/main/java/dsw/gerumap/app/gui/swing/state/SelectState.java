package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class SelectState extends State{

    private HashMap<ElementPainter,Color> restoreColor = new HashMap<>();
    @Override
    public void mousePressed(Point pos, MapView mapView) {


        for(Map.Entry<ElementPainter,Color> entry:restoreColor.entrySet()){

            entry.getKey().getElement().setColor(entry.getValue());
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

                p.getElement().setColor(Color.BLUE);
                p.getElement().setSelected(true);

            }

        }

    }

    @Override
    public void mouseDragged(Point pos, MapView mapView) {
        //Nista
    }

    @Override
    public void mouseReleased(Point pos, MapView mapView) {
        //Nista
    }


}
