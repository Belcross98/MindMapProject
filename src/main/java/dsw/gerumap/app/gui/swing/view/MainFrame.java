package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.controller.ActionMenager;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.gui.swing.tree.MapTree;
import dsw.gerumap.app.gui.swing.tree.MapTreeImplementation;
import dsw.gerumap.app.gui.swing.workspace.ProjectView;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter

public class MainFrame extends JFrame {

    private static MainFrame instance;

    private ActionMenager actionMenager;

    private JMenuBar menu;

    private JToolBar toolBar;

    private MapTree mapTree;

    private MapTreeView projectExplorer;

    private ProjectView projectView;

    private MainFrame(){

    }

    private void initialise(){
        actionMenager = new ActionMenager();
        mapTree = new MapTreeImplementation();
        projectView = new ProjectView();
        initialiseGUI();

    }

    private void initialiseGUI(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("GeRuMap App");

        menu = new MyMenuBar();
        setJMenuBar(menu);

        toolBar = new ToolBar();
        add(toolBar, BorderLayout.NORTH);

        JTree projectExplorer = mapTree.generateTree(ApplicationFramework.getInstance().getMapRepository().getProjectExplorer());

        JScrollPane scroll=new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200,150));
        JPanel rightPanel = projectView;
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll, rightPanel);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);
    }

    public static MainFrame getInstance(){
        if(instance == null){
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }



    public ActionMenager getActionMenager(){
        return actionMenager;
    }


}
