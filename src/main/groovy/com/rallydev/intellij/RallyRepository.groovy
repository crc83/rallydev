package com.rallydev.intellij

import com.intellij.tasks.Task
import com.intellij.tasks.impl.BaseRepository
import com.intellij.tasks.impl.BaseRepositoryImpl
import com.rallydev.intellij.wsapi.ConnectionTest
import com.rallydev.intellij.wsapi.RallyClient

class RallyRepository extends BaseRepositoryImpl {

    RallyRepository() {}

    RallyRepository(RallyRepositoryType type) {
        super(type)
    }

    @Override
    BaseRepository clone() {
        return new RallyRepository()
    }

    @Override
    Task[] getIssues(String s, int i, long l) {
        return new Task[0]  //To change body of implemented methods use File | Settings | File Templates.
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
