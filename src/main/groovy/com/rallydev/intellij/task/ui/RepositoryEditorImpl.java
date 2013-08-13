package com.rallydev.intellij.task.ui;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.util.Consumer;
import com.rallydev.intellij.config.RallyConfig;
import com.rallydev.intellij.task.RallyRepository;

import javax.swing.*;

public class RepositoryEditorImpl extends RepositoryEditor {

    Project project;
    RallyRepository repository;
    Consumer<RallyRepository> changeListener;
    private RallyConfig rallyConfig;

    private boolean applying;
    private final Document document;

    public RepositoryEditorImpl(Project project, RallyRepository repository, Consumer<RallyRepository> changeListener) {
        this.rallyConfig  = RallyConfig.getInstance();
        this.project = project;
        this.repository = repository;
        this.changeListener = changeListener;
        this.serverURLTextField.setText(rallyConfig.url);
        this.loginTextField.setText(rallyConfig.userName);
        this.passwordPasswordField.setText(rallyConfig.password);

        document = EditorFactory.getInstance().createDocument(repository.getCommitMessageFormat());
        document.addDocumentListener(new com.intellij.openapi.editor.event.DocumentAdapter() {
            @Override
            public void documentChanged(com.intellij.openapi.editor.event.DocumentEvent e) {
                doApply();
            }
        });

        errorPanel.setVisible(false);
        loadWorkspaces(repository);
        selectWorkspaceFromConfig();

//        loadProjects(repository);
        selectProjectFromConfig();

//        installListener(workspaces)
//        installListener(projects)
//        installListener(serverURLTextField)
//        installListener(loginTextField)
//        installListener(passwordPasswordField)

//        testConnectionButton.addActionListener(new TestConnectionButtonListener(this));
    }

//    private RepositoryEditorImpl(RallyRepository repository, Document document) {
////        projects.clear();
////        projects.addItem(new com.rallydev.intellij.wsapi.typedefs.Project(name: "Select project", objectId: ""))
////        try {
////            new ProjectsQuery(repository.getClient()).findAllProjects().each {
////                projects.addItem(it)
////            }
////        } catch (Exception e) {
////            e.printStackTrace()
////            displayError()
////        }
//
//        this.document = document;
//    }

    private void loadWorkspaces(RallyRepository repository) {
//        workspaces.clear()
//        workspaces.addItem(new Workspace(name: "Select Workspace", objectId: ""))
//        try {
//            new WorkspacesQuery(repository.getClient()).findAllWorkspaces().each {
//                workspaces.addItem(it)
//            }
//        } catch (Exception e) {
//            e.printStackTrace()
//            displayError()
//        }
    }

    private void selectWorkspaceFromConfig() {
//        (0..(workspaces.itemCount - 1)).each { i ->
//            if (repository.workspaceId == workspaces.getItemAt(i).objectId) {
//                workspaces.selectedIndex = i
//                repository.workspaceName = workspaces.getItemAt(i).name
//            }
//        }
    }

    private void selectProjectFromConfig() {
//        (0..(projects.itemCount - 1)).each { i ->
//            if (repository.projectId == projects.getItemAt(i).objectId) {
//                projects.selectedIndex = i
//                repository.projectName = projects.getItemAt(i).name
//            }
//        }
    }

    @Override
    public JComponent createComponent() {
        return editorPanel;
    }

    private void displayError() {
        errorLabel.setText("Unable to connect to Rally servers");
        errorPanel.setVisible(true);
    }

    public void apply() {
//        repository.workspaceId = workspaces.selectedItem.objectId
//        repository.workspaceName = workspaces.selectedItem.name
//        repository.projectId = projects.selectedItem.objectId
//        repository.projectName = projects.selectedItem.name
//        repository.filterByProject = projectCheckBox.selected
//        repository.filterByWorkspace = workspaceCheckBox.selected
//
        rallyConfig.url = serverURLTextField.getText();
        rallyConfig.userName = loginTextField.getText();
        rallyConfig.password = passwordPasswordField.getText();
        changeListener.consume(repository);
    }

    //from BaseRepositoryEditor
//    protected void installListener(JComboBox comboBox) {
//        comboBox.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                doApply()
//            }
//        })
//    }

    //from BaseRepositoryEditor
//    protected void installListener(JTextField textField) {
//        textField.document.addDocumentListener(new DocumentAdapter() {
//            @Override
//            protected void textChanged(DocumentEvent e) {
//                ApplicationManager.application.invokeLater(new Runnable() {
//                    public void run() {
//                        doApply()
//                    }
//                })
//            }
//        })
//    }

    //from BaseRepositoryEditor
    private void doApply() {
        if (!applying) {
            try {
                applying = true;
                apply();
            }
            finally {
                applying = false;
            }
        }
    }

}
