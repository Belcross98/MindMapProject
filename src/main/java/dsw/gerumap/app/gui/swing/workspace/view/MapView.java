package dsw.gerumap.app.gui.swing.workspace.view;

import dsw.gerumap.app.maprepository.implementation.MindMap;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
@Getter
@Setter

public class MapView extends JPanel {

    private MindMap mindMap;

    public MapView(MindMap mindMap){

        this.mindMap = mindMap;
    }
}
