package dsw.gerumap.app.maprepository.factory;


import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.Element;

public class ElementFactory implements NodeFactory {


    @Override
    public MapNode getNode(String name) {
        //return new Element(name, null);
        return null;
    }


}
