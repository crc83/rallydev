package com.intellij.task.rally;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.tasks.config.BaseRepositoryEditor;
import com.intellij.tasks.jira.jql.JqlLanguage;
import com.intellij.ui.LanguageTextField;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.Consumer;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.Nullable;
import org.sbelei.rally.domain.BasicEntity;
import org.sbelei.rally.domain.Workspace;

import javax.swing.*;
import java.util.Arrays;

public class RallyRepositoryEditor extends BaseRepositoryEditor<RallyRepository> {

    private JBLabel myWorkspaceLabel;
    private  JComboBox myWorkspaces;

    private JBLabel myProjectLabel;
    private  JComboBox myProjects;

    private JBLabel myIterationLabel;
    private  JComboBox myIterations;
    private JCheckBox myIterationsCheckbox;

    public RallyRepositoryEditor(Project project, RallyRepository repository, Consumer<RallyRepository> changeListener) {
        super(project, repository, changeListener);
    }

    @Nullable
    protected JComponent createCustosmPanel() {
        FormBuilder fb = FormBuilder.createFormBuilder();

        myWorkspaces = new ComboBox(myRepository.getWorkspaces(),640);
        selectByEntityId(myWorkspaces, myRepository.workspaceId);
        installListener(myWorkspaces);
        myWorkspaceLabel = new JBLabel("Workspace:", SwingConstants.RIGHT);
        fb.addLabeledComponent(myWorkspaceLabel, myWorkspaces);

        myProjects = new ComboBox(myRepository.getRallyProjects(), 640);
        selectByEntityId(myProjects, myRepository.projectId);

        installListener(myProjects);
        myProjectLabel = new JBLabel("Project:", SwingConstants.RIGHT);
        fb.addLabeledComponent(myProjectLabel, myProjects);

//        myIterations = new JComboBox(myRepository.getIterations());
//
//        myIterationLabel = new JBLabel("Iteration:", SwingConstants.RIGHT);
//        myIterationsCheckbox = new JCheckBox("use current iteration",myRepository.useCurrentIteration);
//        fb.addLabeledComponent(myIterationLabel, myIterations);
//        fb.addComponent(myIterationsCheckbox);

//        installListener(myIterations);
//        installListener(myIterationsCheckbox);

        return fb.getPanel();
    }

    private void selectByEntityId(JComboBox combo, String id) {
        for (int i=0; i< combo.getItemCount(); i++) {
            BasicEntity enity = (BasicEntity) combo.getItemAt(i);
            if (enity.id.equals(id)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    @Override
    public void apply() {
//        myRepository.setWorkspace(myWorkspaces.getSelectedItem());
        super.apply();
    }

}
