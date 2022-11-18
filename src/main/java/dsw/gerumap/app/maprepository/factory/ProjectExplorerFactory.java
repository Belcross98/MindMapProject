package dsw.gerumap.app.maprepository.factory;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;

public class ProjectExplorerFactory implements NodeFactory {

    @Override
    public MapNode getNode(String name) {
        return new ProjectExplorer(name);
    }
}
