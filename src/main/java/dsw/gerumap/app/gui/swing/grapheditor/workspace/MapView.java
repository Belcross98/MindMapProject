package dsw.gerumap.app.gui.swing.grapheditor.workspace;

import dsw.gerumap.app.gui.swing.grapheditor.controller.MouseController;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class MapView extends JPanel implements ISubscriber {

    private MindMap mindMap;

    private List<ElementPainter> painters;
    private List<ElementPainter> selectedPainters;
    private MouseController mouseController;

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

    public ElementPainter elementPainter(Point pos){
        for(ElementPainter painter : painters){
            if(painter.elementAt(pos)){
                return painter;
            }
        }
        return  null;
    }

    @Override
    public void update(Object notification) {
        repaint();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(ElementPainter p : painters){
            p.paint((Graphics2D) g);
        }
    }

}
