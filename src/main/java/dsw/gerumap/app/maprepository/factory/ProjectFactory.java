package dsw.gerumap.app.maprepository.factory;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.Project;

public class ProjectFactory implements NodeFactory{
    @Override
    public MapNode getNode(String name) {
        return new Project(name, null);
    }
}
