package com.rallydev.intellij.task

import com.intellij.tasks.Task
import com.intellij.tasks.impl.BaseRepository
import com.intellij.tasks.impl.BaseRepositoryImpl
import com.rallydev.intellij.config.RallyConfig
import com.rallydev.intellij.wsapi.ConnectionTest
import com.rallydev.intellij.wsapi.RallyClient
import com.rallydev.intellij.wsapi.queries.TasksFilteredQuery
import com.rallydev.intellij.wsapi.queries.TaskFromIdQuery
import org.jetbrains.annotations.Nullable

class RallyRepository2 extends BaseRepositoryImpl {

    @SuppressWarnings("unused")
    public RallyRepository2() {}

    public RallyRepository2(RallyRepository2 other) {
        super(other)
    }

    public RallyRepository2(RallyRepositoryType type) {
        super(type)
    }

    @Override
    public BaseRepository clone() {
        return new RallyRepository2(this)
    }

    @Override
    public Task[] getIssues(@Nullable String query, int max, long since) throws Exception {
        Collection<RallyTask> rallyTasks = new TasksFilteredQuery(getClient()).findTasks(query, max, since)
        return rallyTasks.toArray(new RallyTask[rallyTasks.size()])
    }

    @Nullable
    @Override
    public Task findTask(String id) throws Exception {
        return new TaskFromIdQuery(getClient()).findTask(id)
    }

    @Override
    @SuppressWarnings("deprecation")
    public void testConnection() throws Exception {
        new ConnectionTest(getClient()).doTest()
    }

    private RallyClient getClient() throws MalformedURLException {
        return new RallyClient(new URL(getUrl()), getUsername(), getPassword())
    }





    @Override
    String getUrl() {
        return RallyConfig.instance.url
    }

    @Override
    void setUrl(String url) {
        RallyConfig.instance.url = url
    }

    @Override
    String getUsername() {
        return RallyConfig.instance.userName
    }

    @Override
    void setUsername(String username) {
        RallyConfig.instance.userName = username
    }

    @Override
    String getPassword() {
        RallyConfig.instance.password
    }

    @Override
    void setPassword(String password) {
        RallyConfig.instance.password = password
    }

    @Override
    public boolean isConfigured() {
        return getUrl() && getUsername() && getPassword()
    }

}
