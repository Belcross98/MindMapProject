package dsw.gerumap.app.gui.swing.workspace;

import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;

public class WorkspaceImplementation implements Workspace{

    private ProjectView projectView;

    @Override
    public ProjectView generateWorkspace() {
        this.projectView = new ProjectView();
        return projectView;
    }
}
