package com.intellij.task.rally;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.tasks.Task;
import com.intellij.tasks.TaskRepositoryType;
import com.intellij.tasks.impl.BaseRepository;
import com.intellij.tasks.impl.BaseRepositoryImpl;
import com.intellij.util.xmlb.annotations.Tag;
import com.rallydev.rest.RallyRestApi;
import org.jetbrains.annotations.Nullable;
import org.sbelei.rally.domain.BasicEntity;
import org.sbelei.rally.provider.ProviderFasade;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

@Tag("Rally")
public class RallyRepository extends BaseRepositoryImpl {
    private static final Logger LOG = Logger.getInstance("#com.intellij.tasks.rally.RallyRepository");


    public String workspaceId = "41593629";
    public String projectId = "9216950819";
//    public String iterationId;
//    private boolean filterByProject;
//    private boolean filterByWorkspace;


    private RallyRestApi client;
    private ProviderFasade provider;


    @SuppressWarnings("unused")
    public RallyRepository() {
        super();
        LOG.info("created RallyRepository()");
    }

    public RallyRepository(RallyRepositoryType type) {
        super(type);
        setUrl("https://rally1.rallydev.com");
        LOG.info("created from type RallyRepository()");
    }

    public RallyRepository(RallyRepository rallyRepository) {
        super(rallyRepository);
        LOG.info("created copy of RallyRepository(repository)");
    }

    @Override
    public boolean isConfigured() {
        return super.isConfigured();
    }

    @Override
    public Task[] getIssues(@Nullable String query, int max, long since) throws Exception {
        if (provider == null) {
            loadSettingsFromConfig();
        }

        Task[] result = null;
        try {
            Collection<BasicEntity> rallyTasks = provider.fetchStoriesAndDefects();
            result = new Task[rallyTasks.size()];
            int i = 0;
            for (BasicEntity entity : rallyTasks) {
                Task task = new RallyTask(entity);
                result[i] = task;
                i++;
            }
        } catch (Exception e) {
            LOG.error("Can\'t fetch rally issues", e);
        }

        return result;
    }

    private void loadSettingsFromConfig() {
        URI uri = null;
        try {
            uri = new URI(getUrl());
        } catch (URISyntaxException uie) {
            uie.printStackTrace();
        }
        client = new RallyRestApi(
                uri,
                myUsername,
                myPassword
        );
        provider = new ProviderFasade(client);
        provider.setProjectId(projectId);
        provider.setWorkspaceId(workspaceId);
        provider.setUserLogin(myUsername);
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

    @Override
    public BaseRepository clone() {
        return new RallyRepository(this);
    }

    @Override
    public TaskRepositoryType getRepositoryType() {
        return new RallyRepositoryType();
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }
}
