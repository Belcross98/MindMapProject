package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.grapheditor.controller.CancelAction;
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

    private AddTitleAction addTitleAction;

    private AddLinkAction addLinkAction;

    private  SelectAction selectAction;

    private  DeleteItemAction deleteItemAction;

    private  SettingsAction settingsAction;

    private MoveAction moveAction;

    private ZoomInAction zoomInAction;

    private ZoomOutAction zoomOutAction;

    private RedoAction redoAction;

    private UndoAction undoAction;

    private SaveAsPictureAction saveAsPictureAction;

    private SaveAction saveAction;

    private OpenAction openAction;

    private OpenTemplateAction openTemplateAction;

    private SaveTemplate saveTemplate;


    public ActionMenager(){
        initialiseActions();
    }

    private void  initialiseActions(){
        extitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
        infoAction = new InfoAction();
        removeAction = new RemoveAction();
        addAuthorAction = new AddAuthorAction();
        addTitleAction = new AddTitleAction();
        addLinkAction = new AddLinkAction();
        selectAction = new SelectAction();
        deleteItemAction = new DeleteItemAction();
        settingsAction = new SettingsAction();
        moveAction = new MoveAction();
        zoomInAction = new ZoomInAction();
        zoomOutAction = new ZoomOutAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        saveAsPictureAction = new SaveAsPictureAction();
        saveAction = new SaveAction();
        openAction = new OpenAction();
        openTemplateAction = new OpenTemplateAction();
        saveTemplate = new SaveTemplate();
    }


}
