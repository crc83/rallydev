package com.rallydev.intellij.config;

import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.AbstractCollection;
import com.intellij.util.xmlb.annotations.Property;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

@State(
        name = "Rally",
        storages = {
                @Storage(id="other", file = "C:/rally.xml" )
        }
)
public class RallyConfigImpl implements PersistentStateComponent<RallyConfig>, RallyConfig {

    @Property
    private String url;
    @Property
    private String userName;
    @Property
    private String password;
    @Property
    private boolean rememberPassword;

    @Property(surroundWithTag = false)
    @AbstractCollection(surroundWithTag = false, elementTag = "workspace")
    public List<String> workspaces;

    //Used when no XML file on disk yet
    public RallyConfigImpl() {
        System.out.println("constructor");
        System.out.println(this);
        setUrl("https://rally1.rallydev.com");
        setRememberPassword(true);
        setUserName("sbelei@softserveinc.com");
        setWorkspaces(new LinkedList<String>());
        getState();
    }

    @Nullable
    @Override
    public RallyConfig getState() {
        System.out.println("save state");
        System.out.println(this);
        Thread.dumpStack();
        return this;
    }

    @Override
    public void loadState(RallyConfig state) {
        System.out.println("read state");
        XmlSerializerUtil.copyBean(state, this);
        System.out.println(this);
    }

    @Override
    public boolean isRememberPassword() {
        return rememberPassword;
    }

    @Override
    public void setRememberPassword(boolean rememberPassword) {
        this.rememberPassword = rememberPassword;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUserName() {
        if (userName != null) {
            return userName;
        } else {
            return "";
        }
    }

    @Override
    public void setUserName(String userName) {
        System.out.println("modified username");
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        if (password != null ) {
            return password;
        } else {
            return "";
        }
    }

    @Override
    public void setPassword(String password) {
        System.out.println("modified password");
        this.password = password;
    }

    @Override
    public List<String> getWorkspaces() {
        return workspaces;
    }

    @Override
    public void setWorkspaces(List<String> workspaces) {
        this.workspaces = workspaces;
    }

    @Override
    public void initComponent() {
        System.out.println("init componenet");
    }

    @Override
    public void disposeComponent() {
        System.out.println("dispose component");
    }

    @NotNull
    @Override
    public String getComponentName() {
        System.out.println("get component name");
        return "Rally configyration";
    }

    @Override
    public String toString() {
        return "RallyConfigImpl{" +
                "password='" + password + '\'' +
                ", url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
