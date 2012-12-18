package com.rallydev.intellij

import com.intellij.tasks.Task
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
        def request = GetRequest.requirementGetRequest(url.toURI())
                .withFetch()
                .withPageSize(max)
        if (query) {
            request.withQuery(QueryBuilder.keywordQuery(query).toString())
        }

        return RallyTaskFactory.tasksFromResponse(getRallyClient().makeRequest(request))
    }

    @Override
    Task findTask(String id) {
        if (id.isInteger()) {
            def request = GetRequest.requirementGetRequest(url.toURI()).withObjectId(id)
            return RallyTaskFactory.singleTaskFromResponse(getRallyClient().makeRequest(request))
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
