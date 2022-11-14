package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MindMap extends MapNodeComposite {

    public MindMap(String name) {
        super(name, null);
    }

    public MindMap(){

    }

    @Override
    public void addChild(MapNode child) {
        if (child != null &&  child instanceof Element){
            Element element = (Element) child;
            if (!this.getListOfChildren().contains(element)){
                this.getListOfChildren().add(element);
            }
        }
    }

    @Override
    public void removeChild(MapNode child) {
        if(child != null && listOfChildren != null)
        listOfChildren.remove(child);

    }

}
