package com.rallydev.intellij.task;

import com.intellij.tasks.config.TaskRepositoryEditor;

import javax.swing.*;

public abstract class RepositoryEditor extends TaskRepositoryEditor {

    protected JPanel errorPanel;
    protected JLabel errorLabel;

    protected JPanel successPanel;
    protected JPanel editorPanel;
    protected JComboBox workspaces;

    protected JTextField testField;

}
