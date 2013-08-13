package com.rallydev.intellij.taskold.ui;

import com.intellij.tasks.config.TaskRepositoryEditor;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class RepositoryEditor extends TaskRepositoryEditor {

    protected JPanel errorPanel;
    protected JLabel errorLabel;

    protected JPanel successPanel;
    protected JTabbedPane editorPanel;
    protected JComboBox workspaces;

    protected JTextField serverURLTextField;
    protected JTextField loginTextField;
    protected JPasswordField passwordPasswordField;

    protected JComboBox projects;
    protected JButton testConnectionButton;
    private JCheckBox useCommitMessageCheckBox;
    protected JTextArea document;
    protected JCheckBox workspaceCheckBox;
    protected JCheckBox projectCheckBox;

    public RepositoryEditor() {

    }

    public void showErrorDialog(String message, String title) {
        errorLabel.setText(title+":"+message);
        errorPanel.setVisible(true);
    }

    public void showMessageDialog(String message, String title) {
        errorPanel.setVisible(false);
    }

//    RallyClient getClient() throws MalformedURLException {
//        return new RallyClient(new URL(serverURLTextField.getText()), loginTextField.getText(), new String(passwordPasswordField.getPassword()));
//    }
}
