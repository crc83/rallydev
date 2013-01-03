package com.rallydev.intellij.task;

import com.intellij.tasks.Task;
import com.intellij.tasks.impl.BaseRepository;
import com.intellij.tasks.impl.BaseRepositoryImpl;
import com.intellij.util.xmlb.annotations.Tag;
import com.rallydev.intellij.wsapi.ConnectionTest;
import com.rallydev.intellij.wsapi.RallyClient;
import com.rallydev.intellij.wsapi.queries.TasksFilteredQuery;
import com.rallydev.intellij.wsapi.queries.TaskFromIdQuery;
import org.jetbrains.annotations.Nullable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

@Tag("Rally")
public class RallyRepository extends BaseRepositoryImpl {

    @SuppressWarnings("unused")
    public RallyRepository() {
    }

    public RallyRepository(RallyRepository other) {
        super(other);
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
        Collection<RallyTask> rallyTasks = new TasksFilteredQuery(getClient()).findTasks(query, max, since);
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

    private RallyClient getClient() throws MalformedURLException {
        return new RallyClient(new URL(getUrl()), getUsername(), getPassword());
    }

}
