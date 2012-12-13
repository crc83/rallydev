package com.rallydev.intellij

import com.intellij.tasks.impl.BaseRepositoryType
import javax.swing.Icon
import com.intellij.tasks.TaskRepository

class RallyRepositoryType extends BaseRepositoryType<RallyRepository> {

    @Override
    String getName() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Icon getIcon() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    TaskRepository createRepository() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Class<RallyRepository> getRepositoryClass() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

}
