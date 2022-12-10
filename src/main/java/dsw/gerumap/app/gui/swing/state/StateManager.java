package dsw.gerumap.app.gui.swing.state;

public class StateManager {

    private State currentState;

    private AddTittleState addTittleState;

    private  AddLinkState addLinkState;

    private DeleteState deleteState;

    private SelectState selectState;

    public StateManager(){
        initialiseStates();
    }

    private void initialiseStates(){
        addTittleState = new AddTittleState();
        addLinkState = new AddLinkState();
        deleteState = new DeleteState();
        selectState = new SelectState();
        currentState = addTittleState;
    }

    public State getCurrentState(){
        return currentState;
    }

    public void setAddTittleState() {
        currentState = addTittleState;
    }

    public void setAddLinkState() {
        currentState = addLinkState;
    }

    public void setDeleteState() {
        currentState = deleteState;
    }

    public void setSelectState() {
        currentState = selectState;
    }
}
