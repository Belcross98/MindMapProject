package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.ProjectView;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.Random;


public class AddTittleState extends State{

    @Override
    public void mousePressed(Point pos, MapView mapView) {
        String name = "Title";//JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter your tiitle");
        if(name == null || name.isEmpty())
            return;
        Title title = new Title(5, Color.BLACK, name, new Dimension(30, 30), pos, name, new Ellipse2D.Float(50, 50, 50, 50));
        TitlePainter titlePainter = new TitlePainter(title);
        mapView.addPainter(titlePainter);
        mapView.getMindMap().addChild(title);
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
