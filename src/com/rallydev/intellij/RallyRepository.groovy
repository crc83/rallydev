package com.rallydev.intellij

import com.intellij.tasks.impl.BaseRepositoryImpl
import com.intellij.tasks.impl.BaseRepository
import com.intellij.tasks.Task

class RallyRepository extends BaseRepositoryImpl {

    @Override
    BaseRepository clone() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Task[] getIssues(String s, int i, long l) {
        return new Task[0]  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Task findTask(String s) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

}
