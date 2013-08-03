package com.rallydev.intellij.taskold;

import com.intellij.tasks.Task;
import com.intellij.tasks.impl.BaseRepository;
import com.intellij.tasks.impl.BaseRepositoryImpl;
import com.intellij.util.xmlb.annotations.Tag;
import com.rallydev.intellij.config.RallyConfig;
import com.rallydev.intellij.wsapi.ConnectionTest;
import com.rallydev.intellij.wsapi.RallyClient;
import com.rallydev.intellij.wsapi.queries.TaskFromIdQuery;
import com.rallydev.intellij.wsapi.queries.TasksFilteredQuery;
import org.jetbrains.annotations.Nullable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

@Tag("Rally")
public class RallyRepository extends BaseRepositoryImpl {


    public String workspaceId;
    public String workspaceName;
    public String projectId;
    public String projectName;
    public String projectRef;
    public String testField;
    private boolean filterByProject;
    private boolean filterByWorkspace;

    public RallyClient client;

    @SuppressWarnings("unused")
    public RallyRepository() {
//        client = new RallyClient(
//                new URL(RallyConfig.getInstance().url),
//                RallyConfig.getInstance().userName,
//                RallyConfig.getInstance().password
//        );
    }

    public RallyRepository(RallyRepository other) {
        super(other);
        this.testField = other.testField;
        this.workspaceId = other.workspaceId;
        this.workspaceName = other.workspaceName;
        this.projectId = other.projectId;
        this.projectName = other.projectName;
        this.projectRef = other.projectRef;
    }

    public RallyRepository(RallyRepositoryType type) {
        super(type);
    }

    @Override
    public BaseRepository clone() {
        return new RallyRepository(this);
    }

    @Override
    public Task[] getIssues(@Nullable String query, int max, long since) throws Exception {
        Collection<RallyTask> rallyTasks = new TasksFilteredQuery(getClient()).findTasks(projectRef,query, max, since);
        return rallyTasks.toArray(new RallyTask[rallyTasks.size()]);
    }

    @Nullable
    @Override
    public Task findTask(String id) throws Exception {
        return new TaskFromIdQuery(getClient()).findTask(id);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void testConnection() throws Exception {
        new ConnectionTest(getClient()).doTest();
    }

    public RallyClient getClient() throws MalformedURLException {
        return client;
    }

    //Url is used in the server list, overriding to return a display name instead.
    @Override
    public String getUrl() {
        return RallyConfig.getInstance().url + " (" + workspaceId + ":"+workspaceName+")";
    }

}
