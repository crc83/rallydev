package com.rallydev.intellij

import com.intellij.tasks.Task
import com.intellij.tasks.impl.BaseRepository
import com.intellij.tasks.impl.BaseRepositoryImpl
import com.rallydev.intellij.wsapi.ConnectionTest
import com.rallydev.intellij.wsapi.GetRequest
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
    Task[] getIssues(String s, int i, long l) {
        return RallyTaskFactory.fromResponse(getRallyClient().makeRequest(GetRequest.requirementGetRequest(url.toURI())))
    }

    @Override
    Task findTask(String s) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
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
