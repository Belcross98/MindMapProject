package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.MessageGenerator;
import dsw.gerumap.app.core.messagegen.Message;
import dsw.gerumap.app.gui.swing.controller.ActionMenager;
import dsw.gerumap.app.gui.swing.grapheditor.Palette;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.gui.swing.tree.MapTree;
import dsw.gerumap.app.gui.swing.tree.MapTreeImplementation;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.ProjectView;
import dsw.gerumap.app.maprepository.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter

public class MainFrame extends JFrame implements ISubscriber {

    private static MainFrame instance;

    private ActionMenager actionMenager;

    private JMenuBar menu;

    private JToolBar toolBar;

    private MapTree mapTree;

    private MapTreeView projectExplorer;

    private ProjectView projectView;

    private MessageGenerator messageGenerator;

    private Palette palette;

    private MainFrame(){

    }

    private void initialise(){
        actionMenager = new ActionMenager();
        mapTree = new MapTreeImplementation();
        projectView = new ProjectView();
        palette = new Palette();
        initialiseGUI();

    }

    private void initialiseGUI(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize((int)(screenWidth / 1.5), (int)(screenHeight / 1.5));
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
        JSplitPane split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, rightPanel, palette);
        split2.setResizeWeight(1);

        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll, split2);

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

    public void setMessageGenerator(MessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
        messageGenerator.addSubscriber(this);
    }

    @Override
    public void update(Object notification) {
        Message message = (Message)notification;
        JOptionPane.showMessageDialog(this,message.toString(),message.getType().toString(), JOptionPane.ERROR_MESSAGE);

    }


}
