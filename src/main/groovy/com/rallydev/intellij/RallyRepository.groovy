package com.rallydev.intellij

import com.intellij.tasks.Task
import com.intellij.tasks.TaskType
import com.intellij.tasks.impl.BaseRepository
import com.intellij.tasks.impl.BaseRepositoryImpl
import com.rallydev.intellij.wsapi.ConnectionTest
import com.rallydev.intellij.wsapi.GetRequest
import com.rallydev.intellij.wsapi.QueryBuilder
import com.rallydev.intellij.wsapi.RallyClient

class RallyRepository extends BaseRepositoryImpl {

    RallyRepository() {}

    RallyRepository(RallyRepositoryType type) {
        super(type)
    }

    RallyRepository(RallyRepository other) {
        super(other)
    }

    @Override
    BaseRepository clone() {
        return new RallyRepository(this)
    }

    @Override
    Task[] getIssues(String query, int max, long since) {
        def requirementRequest = GetRequest.requirementGetRequest(url.toURI()).withPageSize(max)
        def defectRequest = GetRequest.defectGetRequest(url.toURI()).withPageSize(max)
        if (query) {
            String queryParam = QueryBuilder.keywordQuery(query).toString()
            requirementRequest.withQuery(queryParam)
            defectRequest.withQuery(queryParam)
        }

        List<RallyTask> rallyTasks = []
        rallyTasks.addAll(RallyTaskFactory.tasksFromResponse(getRallyClient().makeRequest(requirementRequest), TaskType.FEATURE))
        rallyTasks.addAll(RallyTaskFactory.tasksFromResponse(getRallyClient().makeRequest(defectRequest), TaskType.BUG))

        return rallyTasks
    }

    @Override
    Task findTask(String id) {
        if (id.isInteger()) {
            def request = GetRequest.requirementGetRequest(url.toURI()).withObjectId(id)
            return RallyTaskFactory.singleTaskFromResponse(getRallyClient().makeRequest(request), TaskType.OTHER)
        }
        return null
    }

    @Override
    void testConnection() {
        ConnectionTest connectionTest = new ConnectionTest(url.toURI(), rallyClient)
        connectionTest.doTest()
    }

    private RallyClient getRallyClient() {
        new RallyClient(username: username, password: password)
    }

}
