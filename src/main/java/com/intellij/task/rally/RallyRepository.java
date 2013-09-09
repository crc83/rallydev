package com.intellij.task.rally;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.tasks.Task;
import com.intellij.tasks.impl.BaseRepository;
import com.intellij.tasks.impl.BaseRepositoryImpl;
import com.intellij.util.xmlb.annotations.Tag;
import com.intellij.util.xmlb.annotations.Transient;
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


    private String workspaceId;
    private String projectId;
//    public String iterationId;
//    public boolean useCurrentIteration;
//    public String workspaceId = "41593629";
//    public String projectId = "9216950819";

    private RallyRestApi client;
    private ProviderFasade provider;
//    private List<Workspace> workspaces;
//    private List<Project> projects;
//    private List<BasicEntity> iterations;


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
        workspaceId = rallyRepository.getWorkspaceId();
        projectId = rallyRepository.getProjectId();
        LOG.info("created copy of RallyRepository(repository)");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RallyRepository)) return false;
        if (!super.equals(o)) return false;

        RallyRepository that = (RallyRepository) o;

        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (workspaceId != null ? !workspaceId.equals(that.workspaceId) : that.workspaceId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = workspaceId != null ? workspaceId.hashCode() : 0;
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        return result;
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

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /*
    Helper methods to work with filters
     */

    public Object[] fetchWorkspaces() {
        refreshProvider();
        try {
            return provider.fetchWorkspaces().toArray();
        } catch (Exception e) {
            LOG.warn("Error while fetching workspaces",e);
            return null;
        }
    }

    public void applyWorkspace(Object selectedItem) {
        String backupedId = workspaceId;
        try {
            workspaceId = ((Workspace) selectedItem).id;
        } catch (Exception e) {
            LOG.warn("Error while saving workspace number, restored previous value.",e);
            workspaceId = backupedId;
        }
    }

    public Object[] fetchProjects() {
        refreshProvider();
        try {
            return provider.fetchProjects().toArray();
        } catch (Exception e) {
            LOG.warn("Error while fetching workspaces",e);
            return null;
        }
    }

    public void applyProject(Object selectedItem) {
        String backupedId = projectId;
        try {
            projectId = ((Project) selectedItem).id;
        } catch (Exception e) {
            LOG.warn("Error while saving workspace number, restored previous value.",e);
            projectId = backupedId;
        }
    }

}
