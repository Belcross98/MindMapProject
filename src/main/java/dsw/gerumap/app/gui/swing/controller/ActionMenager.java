package dsw.gerumap.app.gui.swing.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ActionMenager {

    private ExitAction extitAction;

    private NewProjectAction newProjectAction;

    private InfoAction infoAction;

    private  RemoveAction removeAction;

    private AddAuthorAction addAuthorAction;

    public ActionMenager(){
        initialiseActions();
    }

    private void  initialiseActions(){
        extitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
        infoAction = new InfoAction();
        removeAction = new RemoveAction();
        addAuthorAction = new AddAuthorAction();
    }


}
