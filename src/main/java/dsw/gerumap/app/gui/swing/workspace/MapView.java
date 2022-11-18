package dsw.gerumap.app.gui.swing.workspace;

import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
@Getter
@Setter

public class MapView extends JPanel implements ISubscriber {

    private MindMap mindMap;

    public MapView(MindMap mindMap){

        this.mindMap = mindMap;
    }

    @Override
    public void update(Object notification) {

    }
}
