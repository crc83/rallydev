package com.intellij.task.rally;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.tasks.config.BaseRepositoryEditor;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.Consumer;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.Nullable;
import org.sbelei.rally.domain.BasicEntity;

import javax.swing.*;

public class RallyRepositoryEditor extends BaseRepositoryEditor<RallyRepository> {

    private JBLabel myWorkspaceLabel;
    private ComboBox myWorkspaces;
    private JButton loadProjects;

    private JBLabel myProjectLabel;
    private ComboBox myProjects;
    private JButton loadIterations;

    private JBLabel myIterationLabel;
    private ComboBox myIterations;
    private JCheckBox myIterationsCheckbox;
    private JCheckBox myShowCompleatedCheckbox;

    public RallyRepositoryEditor(Project project, RallyRepository repository, Consumer<RallyRepository> changeListener) {
        super(project, repository, changeListener);
    }

    @Override
    @Nullable
    protected JComponent createCustomPanel() {
        FormBuilder fb = FormBuilder.createFormBuilder();

        myWorkspaces = new ComboBox(myRepository.fetchWorkspaces(), 440);
        selectByEntityId(myWorkspaces, myRepository.getWorkspaceId());
        installListener(myWorkspaces);
        myWorkspaceLabel = new JBLabel("Workspace:", SwingConstants.RIGHT);
        fb.addLabeledComponent(myWorkspaceLabel, myWorkspaces);
        loadProjects = new JButton("Load projects");
        fb.addComponent(loadProjects);

        myProjects = new ComboBox(myRepository.fetchProjects(), 440);
        selectByEntityId(myProjects, myRepository.getProjectId());
        installListener(myProjects);
        myProjectLabel = new JBLabel("Project:", SwingConstants.RIGHT);
        fb.addLabeledComponent(myProjectLabel, myProjects);
        loadIterations = new JButton("Iterations");
        fb.addComponent(loadIterations);

        myIterations = new ComboBox(myRepository.fetchIterations(), 440);
        selectIteration();
        installListener(myIterations);
        myIterationLabel = new JBLabel("Iteration:", SwingConstants.RIGHT);
        myIterationsCheckbox = new JCheckBox("use current iteration");
        myIterationsCheckbox.setSelected(myRepository.isUseCurrentIteration());
        fb.addLabeledComponent(myIterationLabel, myIterations);
        fb.addComponent(myIterationsCheckbox);
        installListener(myIterationsCheckbox);

        myShowCompleatedCheckbox = new JCheckBox("show compleated tasks");
        myShowCompleatedCheckbox.setSelected(myRepository.isShowCompleatedTasks());
        installListener(myShowCompleatedCheckbox);

        return fb.getPanel();
    }

    private void selectIteration() {
        if (!myRepository.isUseCurrentIteration()) {
            selectByEntityId(myIterations, myRepository.getIterationId());
        }
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

    private void loadItemsToCombobx(JComboBox combo, Object[] items) {
        combo.removeAllItems();
        for (Object item : items) {
            combo.addItem(item);
        }
    }

    @Override
    public void apply() {
        super.apply();
        myRepository.applyWorkspace(myWorkspaces.getSelectedItem());
        myRepository.applyProject(myProjects.getSelectedItem());
        myRepository.setUseCurrentIteration(myIterationsCheckbox.isSelected());
        if (!myRepository.isUseCurrentIteration()) {
            myRepository.applyIteration(myIterations.getSelectedItem());
        }
        myRepository.setShowCompleatedTasks(myShowCompleatedCheckbox.isSelected());
    }

    @Override
    protected void afterTestConnection(boolean connectionSuccessful) {
        super.afterTestConnection(connectionSuccessful);
        if (connectionSuccessful) {
            //load workspaces/projects/iterations
            loadItemsToCombobx(myWorkspaces, myRepository.fetchWorkspaces());
            selectByEntityId(myWorkspaces, myRepository.getWorkspaceId());
            loadItemsToCombobx(myProjects, myRepository.fetchWorkspaces());
            selectByEntityId(myProjects, myRepository.getProjectId());
            loadItemsToCombobx(myIterations, myRepository.fetchIterations());
            selectIteration();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        myRepository.cleanup();
    }
}
