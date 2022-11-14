package dsw.gerumap.app.maprepository.composite;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public abstract class MapNodeComposite extends MapNode{
    protected List<MapNode> listOfChildren;

    public MapNodeComposite(String name, MapNode parent) {
        super(name, parent);
        this.listOfChildren = new ArrayList<>();
    }

    public MapNodeComposite(){
        this.listOfChildren = new ArrayList<>();
    }

    public MapNodeComposite(String name, MapNode parent, List<MapNode> listOfChildren) {
        super(name, parent);
        this.listOfChildren = listOfChildren;
    }

    public abstract void addChild(MapNode child);

    public MapNode getChildrenByName(String name){
        for(MapNode child : this.getListOfChildren()){
            if(name.equals(child.getName())){
                return child;
            }
        }
        return null;
    }

    public abstract void removeChild(MapNode child);



}
