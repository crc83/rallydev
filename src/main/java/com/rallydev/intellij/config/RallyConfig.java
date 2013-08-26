package com.rallydev.intellij.config;

import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by User on 23.08.13.
 */
public interface RallyConfig extends ApplicationComponent{

    @Nullable
    RallyConfig getState();

    void loadState(RallyConfig state);

    boolean isRememberPassword();

    void setRememberPassword(boolean rememberPassword);

    String getUrl();

    void setUrl(String url);

    String getUserName();

    void setUserName(String userName);

    String getPassword();

    void setPassword(String password);

    List<String> getWorkspaces();

    void setWorkspaces(List<String> workspaces);
}
