package com.rallydev.intellij.task

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.project.Project
import com.intellij.ui.DocumentAdapter
import com.intellij.util.Consumer
import com.rallydev.intellij.config.RallyConfig
import com.rallydev.intellij.wsapi.queries.WorkspacesQuery
import com.rallydev.intellij.wsapi.typedefs.Workspace

import javax.swing.*
import javax.swing.event.DocumentEvent
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class RepositoryEditorImpl extends RepositoryEditor {

    Project project
    RallyRepository repository
    Consumer<RallyRepository> changeListener
    private RallyConfig rallyConfig;

    private boolean applying
    private final Document document

    public RepositoryEditorImpl(Project project, RallyRepository repository, Consumer<RallyRepository> changeListener) {
        this.rallyConfig  = RallyConfig.getInstance()
        this.project = project
        this.repository = repository
        this.changeListener = changeListener
        this.serverURLTextField.text = rallyConfig.url
        this.loginTextField.text = rallyConfig.userName
        this.passwordPasswordField.text = rallyConfig.password

        document = EditorFactory.getInstance().createDocument(repository.getCommitMessageFormat())
        document.addDocumentListener(new com.intellij.openapi.editor.event.DocumentAdapter() {
            @Override
            public void documentChanged(com.intellij.openapi.editor.event.DocumentEvent e) {
                doApply()
            }
        })

        errorPanel.setVisible(false)
        loadWorkspaces(repository)

        selectWorkspaceFromConfig()

        installListener(workspaces)
        installListener(testField)

        testField.visible = false
    }

    private void loadWorkspaces(RallyRepository repository) {
        workspaces.clear()
        workspaces.addItem(new Workspace(name: "Select Workspace", objectId: ""))
        try {
            new WorkspacesQuery(repository.getClient()).findAllWorkspaces().each {
                workspaces.addItem(it)
            }
        } catch (Exception e) {
            e.printStackTrace()
            displayError()
        }
    }

    private void selectWorkspaceFromConfig() {
        testField.text = repository.testField
        (0..(workspaces.itemCount - 1)).each { i ->
            if (repository.workspaceId == workspaces.getItemAt(i).objectId) {
                workspaces.selectedIndex = i
            }
        }
    }

    @Override
    public JComponent createComponent() {
        return editorPanel
    }

    private void displayError() {
        errorLabel.text = "Unable to connect to Rally servers"
        errorPanel.visible = true
    }

    public void apply() {
        repository.testField = testField.text
        repository.workspaceId = workspaces.selectedItem.objectId
        rallyConfig.url = serverURLTextField.text
        rallyConfig.userName = loginTextField.text
        rallyConfig.password = passwordPasswordField.text
        changeListener.consume(repository)
    }

    //from BaseRepositoryEditor
    protected void installListener(JComboBox comboBox) {
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doApply()
            }
        })
    }

    //from BaseRepositoryEditor
    protected void installListener(JTextField textField) {
        textField.document.addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(DocumentEvent e) {
                ApplicationManager.application.invokeLater(new Runnable() {
                    public void run() {
                        doApply()
                    }
                })
            }
        })
    }

    //from BaseRepositoryEditor
    private void doApply() {
        if (!applying) {
            try {
                applying = true
                apply()
            }
            finally {
                applying = false
            }
        }
    }

}
