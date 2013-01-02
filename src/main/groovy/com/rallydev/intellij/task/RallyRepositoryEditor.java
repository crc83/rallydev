package com.rallydev.intellij.task;

import com.intellij.openapi.project.Project;
import com.intellij.tasks.config.BaseRepositoryEditor;
import com.intellij.util.Consumer;

//Groovy stub generation failed from the generic, this Java class solves
public abstract class RallyRepositoryEditor extends BaseRepositoryEditor<RallyRepository> {

    public RallyRepositoryEditor(final Project project, final RallyRepository repository, Consumer<RallyRepository> changeListener) {
        super(project, repository, changeListener);
    }

}
