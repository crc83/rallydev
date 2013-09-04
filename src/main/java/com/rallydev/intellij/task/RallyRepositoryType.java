package com.rallydev.intellij.task;

import com.intellij.openapi.project.Project;
import com.intellij.tasks.TaskRepository;
import com.intellij.tasks.impl.BaseRepositoryType;
import com.intellij.util.Consumer;
import com.rallydev.intellij.config.RallyConfig;
import com.rallydev.intellij.task.rally.RallyRepositoryEditor;
import com.rallydev.intellij.task.rally.RepositoryEditorImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * This is description of Rally task repository for Itellij idea.
 */
class RallyRepositoryType extends BaseRepositoryType<RallyRepository> {

    private final RallyConfig config;

    @Override
    @NotNull
    public String getName() {
        return "Rally";
    }

    public RallyRepositoryType(RallyConfig config) {
        this.config = config;
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
        return new RallyRepository(config);
    }

    @Override
    public Class<RallyRepository> getRepositoryClass() {
        return RallyRepository.class;
    }


    @Override
    public RallyRepositoryEditor createEditor(RallyRepository repository, Project project, final Consumer<RallyRepository> changeListener) {
        Consumer<RallyRepository> consumer = new Consumer<RallyRepository>() {

            public void consume(RallyRepository repo) {
                changeListener.consume(repo);
            }
        };
        return new RepositoryEditorImpl(project, repository, consumer);
    }

}
