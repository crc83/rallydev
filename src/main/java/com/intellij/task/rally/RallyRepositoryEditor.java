package com.intellij.task.rally;

import com.intellij.openapi.project.Project;
import com.intellij.tasks.config.BaseRepositoryEditor;
import com.intellij.tasks.jira.jql.JqlLanguage;
import com.intellij.ui.LanguageTextField;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.Consumer;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class RallyRepositoryEditor extends BaseRepositoryEditor<RallyRepository> {

    protected JTabbedPane editorPanel;
    protected JComboBox workspaces;

    protected JComboBox projects;
    protected JCheckBox workspaceCheckBox;
    protected JCheckBox projectCheckBox;
    private JPanel filterPanel;

    private JBLabel myWorkspaceLabel;
    private  JComboBox myWorkspaces;

    private JBLabel myProjectLabel;
    private  JComboBox myProjects;

    public RallyRepositoryEditor(Project project, RallyRepository repository, Consumer<RallyRepository> changeListener) {
        super(project, repository, changeListener);
    }

    @Nullable
    @Override
    protected JComponent createCustomPanel() {
        FormBuilder fb = FormBuilder.createFormBuilder();

        myWorkspaces = new JComboBox(myRepository.getWorkspaces());
        installListener(myWorkspaces);
        myWorkspaceLabel = new JBLabel("Workspace:", SwingConstants.RIGHT);
        fb.addLabeledComponent(myWorkspaceLabel, myWorkspaces);

        myProjects = new JComboBox(myRepository.getProjects());
        installListener(myProjects);
        myProjectLabel = new JBLabel("Project:", SwingConstants.RIGHT);
        fb.addLabeledComponent(myProjectLabel, myProjects);

        return fb.getPanel();
    }

    @Override
    public void apply() {
        myRepository.setWorkspace(myWorkspaces.getSelectedItem());
        super.apply();
    }

}
