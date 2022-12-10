package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;

import java.awt.*;

public class DeleteState extends State{

    @Override
    public void mousePressed(Point pos, MapView mapView) {
        ElementPainter painter = mapView.elementPainter(pos);
        if(painter == null){
            AppCore.getInstance().getMessageGenerator().messageGenerate(EventType.NO_ELEMENT_FOUND);
            return;
        }
        mapView.removePainter(painter);
        mapView.getMindMap().removeChild(painter.getElement());
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
