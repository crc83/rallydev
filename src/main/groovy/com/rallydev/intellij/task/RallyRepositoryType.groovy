package com.rallydev.intellij.task

import com.intellij.openapi.project.Project
import com.intellij.tasks.TaskRepository
import com.intellij.tasks.impl.BaseRepositoryType
import com.intellij.util.Consumer
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

import javax.swing.Icon
import javax.swing.ImageIcon

class RallyRepositoryType extends BaseRepositoryType<RallyRepository2> {
    static final Icon ICON = loadIcon()

    private static ImageIcon loadIcon() {
        try {
            new ImageIcon(RallyRepositoryType.classLoader.getResource("rally.png"), "Rally Icon")
        } catch (Exception e) {
            return null
        }
    }

    @Override
    @NotNull
    String getName() {
        return 'Rally'
    }

    @Override
    @Nullable
    Icon getIcon() {
        return ICON
    }

    @Override
    @NotNull
    TaskRepository createRepository() {
        return new RallyRepository2(this)
    }

    @Override
    Class<RallyRepository2> getRepositoryClass() {
        return RallyRepository2
    }

    //public TaskRepositoryEditor createEditor(final T repository, Project project, final Consumer<T> changeListener) {

    @Override
    public RallyRepositoryEditorForm createEditor(RallyRepository2 repository, Project project, Consumer<RallyRepository2> changeListener) {
        return new RallyRepositoryEditorForm()
    }

    @Override
    @Deprecated
    public RallyRepositoryEditorImpl _createEditor(RallyRepository2 repository, Project project, Consumer<RallyRepository2> changeListener) {
        return new RallyRepositoryEditorImpl(project, repository, changeListener)
    }

}
