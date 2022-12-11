package dsw.gerumap.app.maprepository.factory;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;


public  class FactoryUtils {

    public FactoryUtils(){
        initialise();
    }

    private ProjectExplorerFactory projectExplorerFactory;

    private ProjectFactory projectFactory;

    private MindMapFactory mindMapFactory;

    private ElementFactory elementFactory;

    private void initialise() {
        initialiseActions();
    }

    private void  initialiseActions() {
        projectExplorerFactory = new ProjectExplorerFactory();
        projectFactory = new ProjectFactory();
        mindMapFactory = new MindMapFactory();
        elementFactory = new ElementFactory();
    }

    public NodeFactory getNodeFactory(MapNode node){
          if (node instanceof MindMap) {
            return elementFactory;
        } else if (node instanceof Project) {
            return mindMapFactory;

        } else if (node instanceof ProjectExplorer) {
            return projectFactory;
        }else{
            return projectExplorerFactory;
        }
    }




}
