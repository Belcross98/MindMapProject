package dsw.gerumap.app.gui.swing.controller;

public class ActionMenager {

    private ExitAction extitAction;

    private NewProjectAction newProjectAction;

    public ActionMenager(){
        initialiseActions();
    }

    private void  initialiseActions(){
        extitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
    }

    public ExitAction getExtitAction() {
        return extitAction;
    }

    public NewProjectAction getNewProjectAction() {
        return newProjectAction;
    }

    public void setExtitAction(ExitAction extitAction) {
        this.extitAction = extitAction;
    }

    public void setNewProjectAction(NewProjectAction newProjectAction) {
        this.newProjectAction = newProjectAction;
    }
}
