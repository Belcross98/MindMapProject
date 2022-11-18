package dsw.gerumap.app.maprepository.factory;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.MindMap;

public class MindMapFactory implements NodeFactory{


    @Override
    public MapNode getNode(String name) {
        return new MindMap(name);
    }
}
