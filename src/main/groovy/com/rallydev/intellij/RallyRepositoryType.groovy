package com.rallydev.intellij

import com.intellij.openapi.util.IconLoader
import com.intellij.tasks.TaskRepository
import com.intellij.tasks.impl.BaseRepositoryType

import javax.swing.Icon
import javax.swing.ImageIcon

class RallyRepositoryType extends BaseRepositoryType<RallyRepository> {
    static final Icon ICON = loadIcon()

    private static ImageIcon loadIcon() {
        try {
            new ImageIcon(RallyRepositoryType.classLoader.getResource("rally.png"), "Rally Icon")
        } catch (Exception e) {
            return null
        }
    }

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
