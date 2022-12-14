package dsw.gerumap.app.gui.swing.grapheditor.workspace;

import dsw.gerumap.app.gui.swing.grapheditor.controller.MouseController;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class MapView extends JPanel implements ISubscriber {

    private MindMap mindMap;

    private List<ElementPainter> painters;
    private List<ElementPainter> selectedPainters;
    private MouseController mouseController;

    private double  zoomFactor = 1;

    AffineTransform affineTransform = new AffineTransform();

    private double xTranslate = 0;

    private double yTranslate = 0;


    public MapView(MindMap mindMap){
        mouseController = new MouseController();
        addMouseListener(mouseController);
        addMouseMotionListener(mouseController);
        painters = new ArrayList<>();
        selectedPainters = new ArrayList<>();
        this.mindMap = mindMap;
        mindMap.addSubscriber(this);

    }

    public void addPainter(ElementPainter painter){
        painters.add(painter);
    }


    public void removePainter(ElementPainter painter){
        painters.remove(painter);
    }

    public void setTransformation(double zoomFactor){
        affineTransform.setToIdentity();
        affineTransform.translate(xTranslate, yTranslate);
        affineTransform.scale(zoomFactor, zoomFactor);
        repaint();
    }

    public ElementPainter elementPainter(Point pos){
        for(ElementPainter painter : painters){
            if(painter.elementAt(pos)){
                return painter;
            }
        }
        return  null;
    }

    public void zoomIn(){
        double  newZoomFactor;
        newZoomFactor = zoomFactor * 1.2;
        if(newZoomFactor > 5){
            newZoomFactor = 5;
        }
        setTransformation(newZoomFactor);
        zoomFactor = newZoomFactor;

    }

    public void zoomOut(){
        double  newZoomFactor;
        newZoomFactor = zoomFactor * 0.8;
        if(newZoomFactor < 0.2){
            newZoomFactor = 0.2;
        }
        setTransformation(newZoomFactor);
        zoomFactor = newZoomFactor;
    }

    @Override
    public void update(Object notification) {
        repaint();
    }

    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g);
        g2.setTransform(affineTransform);
        for(ElementPainter p : painters){
            p.paint(g2);
        }
    }


}
