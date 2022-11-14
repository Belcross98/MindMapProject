package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.maprepository.factory.ElementFactory;
import dsw.gerumap.app.maprepository.factory.MindMapFactory;
import dsw.gerumap.app.maprepository.factory.NodeFactory;
import dsw.gerumap.app.maprepository.factory.ProjectFactory;
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
        MapNodeComposite parent = (MapNodeComposite) child.getMapNode().getParent();
        parent.removeChild(child.getMapNode());
        treeModel.removeNodeFromParent(child);
        SwingUtilities.updateComponentTreeUI(treeView);
    }


    private MapNode createChild(MapNode parent){
        NodeFactory elementFactory = new ElementFactory();
        NodeFactory mindMapFactory = new MindMapFactory();
        NodeFactory projectFactory = new ProjectFactory();

        if(parent instanceof ProjectExplorer){
            MapNode project = projectFactory.getNode();
            project.setName("Project" + new Random().nextInt(100));
            project.setParent(parent);
            return  project;
           //return new Project ("Project" + new Random().nextInt(100),parent);
        }
        if(parent instanceof  Project){
            MapNode mindMap  = mindMapFactory.getNode();
            mindMap.setName("MindMap" + new Random().nextInt(100));
            mindMap.setParent(parent);
            return  mindMap;
         //  return new MindMap("MindMap" +new Random().nextInt(100));
        }
        if(parent instanceof  MindMap){

            MapNode element = elementFactory.getNode();
            element.setName("Element" + new Random().nextInt(100));
            element.setParent(parent);
            return  element;
          // return new Element("Element" +new Random().nextInt(100), parent);
        }

        return null;

    }
    @Override
    public void addChild(MapTreeItem parent) {

        if(!(parent.getMapNode() instanceof MapNodeComposite))
            return;


        MapNode child = createChild(parent.getMapNode());
        parent.add(new MapTreeItem(child));
        ((MapNodeComposite) parent.getMapNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }
}
