package dsw.gerumap.app.gui.swing.workspace.view;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectView extends JPanel {

    private String projectName;
    private String authorName;

    private JLabel projectName1;

    private  JLabel authorName1;

    private MapNode project;

    private JTabbedPane tabbedPane;
    private List<MapView> tabs;

   public ProjectView(){
       this.projectName1 = new JLabel();
       this.authorName1 = new JLabel();
       this.tabbedPane = new JTabbedPane();
       this.tabs = new ArrayList<>();
       add(projectName1);
       add(authorName1);
       add(tabbedPane);
   }


    public void refreshWorkspace(MapNode selectedProject){
        //uklanjamo stare tabove
        tabs.clear();
        tabbedPane.removeAll();

        //dodajemo poljima vrednosti iz projekta
        this.project = selectedProject;
        authorName = ((Project)selectedProject).getAuthor();
        projectName = ((Project)selectedProject).getName();


        //prolazimo kroz decu selektovanog projekta i pravimo wraper klasu - MapView

        //zatim taj MapView tab dodajemo u listu tabova -> for each petlja
        for(MapNode child: ((Project) selectedProject).getListOfChildren()) {
            MapView tab = new MapView((MindMap) child);
            tabs.add(tab);
        }
        //prolazimo kroz listu "tabovi" i svaki tab dodajemo na tabbedPane
        for(MapView tab : tabs)
          tabbedPane.addTab(tab.getMindMap().getName(), tab);

    }
}
