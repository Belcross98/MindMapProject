package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.tree.MapTreeImplementation;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.*;
import java.awt.geom.Ellipse2D;



public class AddTittleState extends State{

    @Override
    public void mousePressed(Point pos, MapView mapView) {
        String name = "Title";//JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter your tiitle");
        if(name == null || name.isEmpty())
            return;
        Title title = new Title(5,Color.BLACK, name, new Dimension(120, 60), pos, name);
        TitlePainter titlePainter = new TitlePainter(title);
        mapView.addPainter(titlePainter);
        mapView.getMindMap().addChild(title);
        title.addSubscriber(mapView);
        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        MainFrame.getInstance().getMapTree().addChild(selected);

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
