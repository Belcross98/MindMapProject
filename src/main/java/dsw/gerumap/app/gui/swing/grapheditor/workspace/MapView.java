package dsw.gerumap.app.gui.swing.grapheditor.workspace;

import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.maprepository.implementation.Element;
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

    public MapView(MindMap mindMap){
        addMouseListener(new MouseController());
        painters = new ArrayList<>();
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
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        repaint();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(ElementPainter p : painters){
            p.paint((Graphics2D) g);
        }
    }

}
