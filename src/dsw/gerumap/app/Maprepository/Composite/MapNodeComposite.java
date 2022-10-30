package dsw.gerumap.app.Maprepository.Composite;


import java.util.ArrayList;
import java.util.List;

public abstract class MapNodeComposite extends MapNode{

    protected List<MapNode> listOfChildren = new ArrayList<>();

    public abstract void addChild(MapNode child);
    public abstract void removeChild(MapNode child);

}
