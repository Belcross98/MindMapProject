package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.maprepository.factory.*;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;

public class MapTreeImplementation implements MapTree{

    private MapTreeView treeView;
    private DefaultTreeModel treeModel;

    public MapTreeImplementation() {
        MapRepository mapRepository = ApplicationFramework.getInstance().getMapRepository();
        ProjectExplorer projectExplorer = mapRepository.getProjectExplorer();
        MapTreeItem mapTreeItem = new MapTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(mapTreeItem);
        this.treeView = new MapTreeView(treeModel);

    }

    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        MapTreeItem root = new MapTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new MapTreeView(treeModel);
        return treeView;
    }

    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public void removeChild(MapTreeItem child) {
        System.out.println(child);
        System.out.println(child.getMapNode());
        MapNodeComposite parent = (MapNodeComposite) child.getMapNode().getParent();
        System.out.println(parent);
        if(child.getMapNode() instanceof ProjectExplorer) {
            AppCore.getInstance().getMessageGenerator().messageGenerate(EventType.TRY_TO_DELETE_PROJECTEXPLORER);
            return;
        }

        parent.removeChild(child.getMapNode());
        treeModel.removeNodeFromParent(child);
        SwingUtilities.updateComponentTreeUI(treeView);
    }


    private MapNode createChild(MapNodeComposite parent){

        NodeFactory nodeFactory = new FactoryUtils().getNodeFactory(parent);
        return nodeFactory.getNode(parent.getChildrenClassName() + " " + (parent.getListOfChildren().size() + 1));

        /* Pogresna imlementacija factory-ja

        NodeFactory elementFactory = new ElementFactory();
        NodeFactory mindMapFactory = new MindMapFactory();
        NodeFactory projectFactory = new ProjectFactory();

        if(parent instanceof ProjectExplorer){
            MapNode project = projectFactory.getNode();
            project.setName("Project" + (((ProjectExplorer) parent).getListOfChildren().size() + 1));
            project.setParent(parent);
            return  project;
           //return new Project ("Project" + new Random().nextInt(100),parent);
        }
        if(parent instanceof  Project){
            MapNode mindMap  = mindMapFactory.getNode();
            mindMap.setName("MindMap" + (((Project) parent).getListOfChildren().size() + 1));
            mindMap.setParent(parent);
            return  mindMap;
         //  return new MindMap("MindMap" +new Random().nextInt(100));
        }
        if(parent instanceof  MindMap){

            MapNode element = elementFactory.getNode();
            element.setName("Element" + (((MindMap) parent).getListOfChildren().size() + 1));
            element.setParent(parent);
            return  element;
          // return new Element("Element" +new Random().nextInt(100), parent);
        }

        return null;
        */

    }
    @Override
    public void addChild(MapTreeItem parent) {

        if(!(parent.getMapNode() instanceof MapNodeComposite)) {
            AppCore.getInstance().getMessageGenerator().messageGenerate(EventType.NODE_CANNOT_HAVE_CHILDREN);
            return;
        }


        MapNode child = createChild((MapNodeComposite)parent.getMapNode());
        parent.add(new MapTreeItem(child));
        System.out.println(child.getName());
        ((MapNodeComposite) parent.getMapNode()).addChild(child);
        child.setParent(((MapNodeComposite) parent.getMapNode()));
        System.out.println(((MapNodeComposite) parent.getMapNode()).getListOfChildren());
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        System.out.println(((MapNodeComposite) parent.getMapNode()).getListOfChildren());
    }
}
