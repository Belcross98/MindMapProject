package dsw.gerumap.app.Maprepository.Implementation;

import dsw.gerumap.app.Maprepository.Composite.MapNode;
import dsw.gerumap.app.Maprepository.Composite.MapNodeComposite;

public class MindMap extends MapNodeComposite {

    @Override
    public void addChild(MapNode child) {
        if(child instanceof Element)
            listOfChildren.add(child);
    }

    @Override
    public void removeChild(MapNode child) {
        listOfChildren.remove(child);
    }
}
