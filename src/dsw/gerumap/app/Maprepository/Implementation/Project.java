package dsw.gerumap.app.Maprepository.Implementation;

import dsw.gerumap.app.Maprepository.Composite.MapNode;
import dsw.gerumap.app.Maprepository.Composite.MapNodeComposite;

public class Project extends MapNodeComposite {


    @Override
    public void addChild(MapNode child) {
        if(child instanceof MindMap)
            listOfChildren.add(child);
    }

    @Override
    public void removeChild(MapNode child) {
        listOfChildren.remove(child);
    }
}
