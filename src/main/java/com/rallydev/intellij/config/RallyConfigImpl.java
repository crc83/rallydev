package com.rallydev.intellij.config;

import com.intellij.openapi.components.*;
import com.intellij.openapi.util.PasswordUtil;
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
                @Storage(id="other", file = StoragePathMacros.APP_CONFIG + "/rally.xml" )
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


    //Used when no XML file on disk yet
    public RallyConfigImpl() {
        System.out.println("constructor");
        System.out.println(this);
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
        if (url != null) {
            return url;
        } else {
            return "https://rally1.rallydev.com";
        }
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
