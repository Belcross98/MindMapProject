package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Project extends MapNodeComposite {

    private String author;

    public Project(String name, MapNode parent) {
        super(name, parent);
        this.author = author;
    }
    public Project(){

    }
    @Override
    public void addChild(MapNode child) {
        if (child != null &&  child instanceof MindMap){
            MindMap mindMap = (MindMap) child;
            if(!this.getListOfChildren().contains(mindMap)){
                this.getListOfChildren().add(mindMap);
            }
            }
    }

    @Override
    public void removeChild(MapNode child) {
        if(child != null && listOfChildren != null)
        listOfChildren.remove(child);
    }


}
