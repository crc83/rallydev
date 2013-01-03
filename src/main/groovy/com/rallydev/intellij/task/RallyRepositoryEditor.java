package com.rallydev.intellij.task;

import com.intellij.openapi.project.Project;
import com.intellij.tasks.config.BaseRepositoryEditor;
import com.intellij.util.Consumer;

@Deprecated
//Groovy stub generation failed from the generic, this Java class solves
public abstract class RallyRepositoryEditor extends BaseRepositoryEditor<RallyRepository2> {

    public RallyRepositoryEditor(final Project project, final RallyRepository2 repository, Consumer<RallyRepository2> changeListener) {
        super(project, repository, changeListener);
    }

}
