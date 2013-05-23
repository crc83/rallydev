package com.rallydev.intellij.task.ui;

import com.intellij.tasks.config.TaskRepositoryEditor;
import com.rallydev.intellij.wsapi.RallyClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class RepositoryEditor extends TaskRepositoryEditor {

    protected JPanel errorPanel;
    protected JLabel errorLabel;

    protected JPanel successPanel;
    protected JTabbedPane editorPanel;
    protected JComboBox workspaces;

    protected JTextField testField;
    protected JTextField serverURLTextField;
    protected JTextField loginTextField;
    protected JPasswordField passwordPasswordField;

    protected JComboBox projects;
    protected JButton testConnectionButton;
    private JCheckBox useCommitmessageCheckBox;
    private JTextArea textArea1;
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

    RallyClient getClient() throws MalformedURLException {
        return new RallyClient(new URL(serverURLTextField.getText()), loginTextField.getText(), new String(passwordPasswordField.getPassword()));
    }
}
