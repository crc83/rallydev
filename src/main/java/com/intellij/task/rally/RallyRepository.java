package com.intellij.task.rally;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.tasks.Task;
import com.intellij.tasks.impl.BaseRepository;
import com.intellij.tasks.impl.BaseRepositoryImpl;
import com.intellij.util.xmlb.annotations.Tag;
import com.rallydev.rest.RallyRestApi;
import org.jetbrains.annotations.Nullable;
import org.sbelei.rally.domain.BasicEntity;
import org.sbelei.rally.domain.Project;
import org.sbelei.rally.domain.Workspace;
import org.sbelei.rally.provider.ProviderFasade;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

@Tag("Rally")
public class RallyRepository extends BaseRepositoryImpl {
    private static final Logger LOG = Logger.getInstance("#com.intellij.tasks.rally.RallyRepository");


    public String workspaceId;
    public String projectId;
//    public String workspaceId = "41593629";
//    public String projectId = "9216950819";
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
            refreshProvider();
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

    private void refreshProvider() {
        if (provider == null) {
            try {
                URI uri = new URI(getUrl());
                client = new RallyRestApi(
                        uri,
                        myUsername,
                        myPassword
                );
                provider = new ProviderFasade(client);
                provider.setUserLogin(myUsername);

            } catch (URISyntaxException uie) {
                LOG.error("Wrong URL", uie);
            }
        }
        if (provider != null) {
            provider.setUseCurrentIteration(true);
            provider.setOnlyMine(true);

            provider.setWorkspaceId(workspaceId);
            provider.setProjectId(projectId);
        } else {
            LOG.error("Provider is not initialized properly");
        }
    }

    @Nullable
    @Override
    public Task findTask(String id) throws Exception {
        return null;
    }

    @Override
    public void testConnection() throws Exception {
        refreshProvider();
        provider.fetchWorkspaces().toArray();
    }

    @Override
    public BaseRepository clone() {
        return new RallyRepository(this);
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public Object[] getWorkspaces() {
        refreshProvider();
        List<Workspace> ws = provider.fetchWorkspaces();
        if (ws == null) {
            return null;
        } else {
            return ws.toArray();
        }
    }

    public void setWorkspace(Object selectedItem) {
        Workspace ws = (Workspace) selectedItem;
        workspaceId = ws.id;
    }

    public Object[] getProjects() {
        refreshProvider();
        List<Project> projects = provider.fetchProjects();
        if (projects == null) {
            return null;
        } else {
            return projects.toArray();
        }
    }

    public void setProject(Object selectedItem) {
        Project project = (Project) selectedItem;
        projectId = project.id;
    }
}
