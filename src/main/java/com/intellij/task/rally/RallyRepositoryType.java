package com.intellij.task.rally;

import com.intellij.openapi.project.Project;
import com.intellij.tasks.TaskRepository;
import com.intellij.tasks.impl.BaseRepositoryType;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * This is description of Rally task repository for Itellij idea.
 */
public class RallyRepositoryType extends BaseRepositoryType<RallyRepository> {

    @Override
    @NotNull
    public String getName() {
        return "Rally";
    }

    @Override
    @Nullable
    public Icon getIcon() {
        Icon icon = new ImageIcon(this.getClass().getClassLoader().getResource("rally.png"), "Rally Icon");
        return icon;
    }

    @Override
    @NotNull
    public TaskRepository createRepository() {
        return new RallyRepository(this);
    }

    @Override
    public Class<RallyRepository> getRepositoryClass() {
        return RallyRepository.class;
    }


    @Override
    public RallyRepositoryEditor createEditor(RallyRepository repository, Project project, final Consumer<RallyRepository> changeListener) {
        return new RallyRepositoryEditor(project, repository, changeListener);
    }

}
