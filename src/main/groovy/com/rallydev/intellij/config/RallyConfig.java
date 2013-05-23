package com.rallydev.intellij.config;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

@State(
        name = "Rally",
        storages = @Storage(file = "$APP_CONFIG$/rally.xml")
)
public class RallyConfig implements PersistentStateComponent<RallyConfig> {

    public String url;
    public String userName;
    public String password;
    public boolean rememberPassword;

    public List<String> workspaces;

    //Used when no XML file on disk yet
    public RallyConfig() {
        workspaces = new LinkedList<String>();
    }

    public static RallyConfig getInstance() {
        return ServiceManager.getService(RallyConfig.class);
    }

    @Nullable
    @Override
    public RallyConfig getState() {
        return this;
    }

    @Override
    public void loadState(RallyConfig state) {
        XmlSerializerUtil.copyBean(state, this);
    }

}
