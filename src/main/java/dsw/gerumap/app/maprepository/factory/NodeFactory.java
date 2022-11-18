package dsw.gerumap.app.maprepository.factory;

import dsw.gerumap.app.maprepository.composite.MapNode;


public interface NodeFactory {

    public default NodeFactory getNode(String name,MapNode parent){
        MapNode node = getNode(name);
        node.setName(name);
        node.setParent(parent);
        return (NodeFactory) node;
    }

    abstract MapNode getNode(String name);

}
