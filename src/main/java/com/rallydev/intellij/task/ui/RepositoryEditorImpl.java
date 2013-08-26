package com.rallydev.intellij.task.ui;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.util.Consumer;
import com.rallydev.intellij.config.RallyConfig;
import com.rallydev.intellij.task.RallyRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RepositoryEditorImpl extends RepositoryEditor {

    Project project;
    RallyRepository repository;
    Consumer<RallyRepository> changeListener;
    private RallyConfig rallyConfig;

    private boolean applying;
    private final Document document;

    public RepositoryEditorImpl(RallyConfig config, Project project, RallyRepository repository, Consumer<RallyRepository> changeListener) {
        this.rallyConfig  = config;
        this.project = project;
        this.repository = repository;
        this.changeListener = changeListener;
        this.serverURLTextField.setText(rallyConfig.getUrl());
        this.loginTextField.setText(rallyConfig.getUserName());
        this.passwordPasswordField.setText(rallyConfig.getPassword());

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
        rallyConfig.setUrl(serverURLTextField.getText());
        rallyConfig.setUserName(loginTextField.getText());
        rallyConfig.setPassword(passwordPasswordField.getText());
        rallyConfig.getState();
        changeListener.consume(repository);
    }

    //from BaseRepositoryEditor
    protected void installListener(JComboBox comboBox) {
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doApply();
            }
        });
    }

    //from BaseRepositoryEditor
//    protected void installListener(JTextField textField) {
//        textField.getDocument().addDocumentListener(new DocumentAdapter() {
//            @Override
//            protected void textChanged(DocumentEvent e) {
//                ApplicationManager.getApplication().invokeLater(new Runnable() {
//                    public void run() {
//                        doApply();
//                    }
//                });
//            }
//        });
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
