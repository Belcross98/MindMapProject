package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;

import java.awt.*;

public abstract class State {

    public abstract void mousePressed(Point pos, MapView mapView);
    public abstract void mouseDragged(Point pos, MapView mapView);
    public abstract void mouseReleased(Point pos, MapView mapView);

}
