package com.rallydev.intellij.task;

import com.intellij.tasks.config.TaskRepositoryEditor;

import javax.swing.*;

public class RallyRepositoryEditorForm extends TaskRepositoryEditor {

    private JPanel editorPanel;
    private JComboBox workspaces;

    @Override
    public JComponent createComponent() {



        return editorPanel;
    }

}
