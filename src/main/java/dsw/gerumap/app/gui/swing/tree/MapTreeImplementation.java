package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.gui.swing.command.AbstractCommand;
import dsw.gerumap.app.gui.swing.command.implementation.AddChildCommand;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.maprepository.factory.*;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.Enumeration;
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

    @Override
    public MapTreeView getTree() {
        return treeView;
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
    public void addElement(MapTreeItem parent, MapNode child) {

        if(!(parent.getMapNode() instanceof MapNodeComposite)) {
            AppCore.getInstance().getMessageGenerator().messageGenerate(EventType.NODE_CANNOT_HAVE_CHILDREN);
            return;
        }


        parent.add(new MapTreeItem(child));
        ((MapNodeComposite) parent.getMapNode()).addChild(child);
        child.setParent(((MapNodeComposite) parent.getMapNode()));
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

    }

    @Override
    public MapTreeItem getNode(MapNode child) {

        MapTreeItem root = this.getRoot();
        Enumeration en = root.depthFirstEnumeration();
        while (en.hasMoreElements()) {

            MapTreeItem node = (MapTreeItem) en.nextElement();
            System.out.println(node.toString() + "IM THIS GUY ");
            if(node.getMapNode() == child){
                System.out.println("I FOUND U ");
                return node;
            }

        }

        return null;
    }

    @Override
    public MapTreeItem getRoot() {

        return (MapTreeItem) this.treeModel.getRoot();
    }

    @Override
    public void loadProject(Project node) {
        MapTreeItem loadedProject = new MapTreeItem(node);
        getRoot().add(loadedProject);

        MapNodeComposite mapNode = (MapNodeComposite) getRoot().getMapNode();
        mapNode.addChild(node);

        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
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


    }
    @Override
    public void addChild(MapTreeItem parent) {

        if(!(parent.getMapNode() instanceof MapNodeComposite)) {
            AppCore.getInstance().getMessageGenerator().messageGenerate(EventType.NODE_CANNOT_HAVE_CHILDREN);
            return;
        }




        MapNode child = createChild((MapNodeComposite)parent.getMapNode());
        AbstractCommand command = new AddChildCommand(parent, new MapTreeItem(child));
        child.setParent(((MapNodeComposite) parent.getMapNode()));
        System.out.println(((MapNodeComposite) parent.getMapNode()).getListOfChildren());
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        System.out.println(((MapNodeComposite) parent.getMapNode()).getListOfChildren());
    }




}
