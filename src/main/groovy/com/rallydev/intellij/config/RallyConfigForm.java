package com.rallydev.intellij.config;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.rallydev.intellij.wsapi.RallyClient;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public class RallyConfigForm implements SearchableConfigurable {

    private JPanel configPanel;
    private JTextField url;
    private JTextField userName;
    private JPasswordField password;
    private JButton testConnectionButton;

    private RallyConfig rallyConfig;

    public RallyConfigForm() {
        rallyConfig = RallyConfig.getInstance();
    }

    public JTextField getUrl() {
        return url;
    }

    public JTextField getUserName() {
        return userName;
    }

    public JPasswordField getPassword() {
        return password;
    }

    @Override
    public String getDisplayName() {
        return "Rally";
    }

    public Icon getIcon() {
        return null;
    }

    @NotNull
    @Override
    public String getId() {
        return getHelpTopic();
    }

    @NotNull
    @Override
    public String getHelpTopic() {
        return "reference.idesettings.rally";
    }

    @Override
    public boolean isModified() {
        return !(StringUtils.equals(url.getText(), rallyConfig.url)
                && StringUtils.equals(userName.getText(), rallyConfig.userName)
                && StringUtils.equals(new String(password.getPassword()), rallyConfig.password)
        );
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        setupFromConfig();

        testConnectionButton.addActionListener(new TestConnectionButtonListener(this));

        return configPanel;
    }

    private void setupFromConfig() {
        System.out.println(url);
        System.out.println(rallyConfig);

        url.setText(rallyConfig.url);
        userName.setText(rallyConfig.userName);
        password.setText(rallyConfig.password);
    }

    @Nullable
    @Override
    public Runnable enableSearch(String option) {
        return null;
    }

    @Override
    public void apply() throws ConfigurationException {
        rallyConfig.url = url.getText();
        rallyConfig.userName = userName.getText();
        rallyConfig.password = new String(password.getPassword());
    }

    @Override
    public void reset() {
        setupFromConfig();
    }

    @Override
    public void disposeUIResources() {
    }

    RallyClient getClient() throws MalformedURLException {
        return new RallyClient(new URL(url.getText()), userName.getText(), new String(password.getPassword()));
    }

}
