package com.rallydev.intellij

import com.intellij.openapi.util.IconLoader
import com.intellij.tasks.TaskRepository
import com.intellij.tasks.impl.BaseRepositoryType

import javax.swing.Icon

class RallyRepositoryType extends BaseRepositoryType<RallyRepository> {
    static final Icon ICON = IconLoader.getIcon("/icon/rally.png");

    @Override
    String getName() {
        return 'Rally'
    }

    @Override
    Icon getIcon() {
        //test?
        return ICON
    }

    @Override
    TaskRepository createRepository() {
        return new RallyRepository(this)
    }

    @Override
    Class<RallyRepository> getRepositoryClass() {
        return RallyRepository
    }

}
