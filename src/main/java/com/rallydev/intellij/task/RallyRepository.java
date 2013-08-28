package com.rallydev.intellij.task;

import com.intellij.openapi.util.PasswordUtil;
import com.intellij.tasks.Task;
import com.intellij.tasks.TaskRepositoryType;
import com.intellij.tasks.impl.BaseRepository;
import com.intellij.tasks.impl.BaseRepositoryImpl;
import com.intellij.util.xmlb.annotations.Tag;
import com.rallydev.intellij.config.RallyConfig;
import com.rallydev.intellij.config.RallyConfigImpl;
import com.rallydev.rest.RallyRestApi;
import org.jetbrains.annotations.Nullable;
import org.sbelei.rally.domain.BasicEntity;
import org.sbelei.rally.provider.ProviderFasade;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

@Tag("Rally")
public class RallyRepository extends BaseRepositoryImpl {


    public String workspaceId = "41593629";
    public String projectId = "9216950819";
//    public String iterationId;
//    private boolean filterByProject;
//    private boolean filterByWorkspace;

    public RallyRestApi client;
    private RallyConfig config;
    ProviderFasade provider;

    @SuppressWarnings("unused")
    public RallyRepository(RallyConfig config) {
        this.config = config;
        loadSettingsFromConfig();
    }

    @Override
    public Task[] getIssues(@Nullable String query, int max, long since) throws Exception {
        loadSettingsFromConfig();

        Collection<BasicEntity> rallyTasks = provider.fetchStoriesAndDefects();
        Task[] result = new Task[rallyTasks.size()];
        int i = 0;
        for (BasicEntity entity : rallyTasks) {
            Task task = new RallyTask(entity);
            result[i] = task;
            i++;
        }
        return result;
    }

    private void loadSettingsFromConfig() {
        URI uri = null;
        try {
            uri = new URI( config.getUrl() );
        } catch (URISyntaxException uie) {
            uie.printStackTrace();
        }
        client = new RallyRestApi(
                uri,
                config.getUserName(),
                config.getPassword()
        );
        provider = new ProviderFasade(client);
        provider.setProjectId(projectId);
        provider.setWorkspaceId(workspaceId);
        provider.setUserLogin(config.getUserName());
        provider.setUseCurrentIteration(true);
        provider.setOnlyMine(true);
    }

    @Nullable
    @Override
    public Task findTask(String id) throws Exception {
        return null;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void testConnection() throws Exception {
//        new ConnectionTest(getClient()).doTest();
    }

    public RallyRestApi getClient() throws MalformedURLException {
        return client;
    }

    //Url is used in the server list, overriding to return a display name instead.
    @Override
    public String getUrl() {
        return config.getUrl();
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
        config.setUserName(username);
    }

    @Override
    public void setUrl(String url) {
        super.setUrl(url);
        config.setUrl(url);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
        config.setPassword(password);
    }

    @Override
    public BaseRepository clone() {
        return new RallyRepository(config);
    }

    @Override
    public TaskRepositoryType getRepositoryType() {
        return new RallyRepositoryType(config);
    }
}
