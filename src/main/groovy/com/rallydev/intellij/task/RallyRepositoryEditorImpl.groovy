package com.rallydev.intellij.task

import com.intellij.openapi.project.Project
import com.intellij.util.Consumer

class RallyRepositoryEditorImpl extends RallyRepositoryEditor {

    public RallyRepositoryEditorImpl(final Project project, final RallyRepository repository, Consumer<RallyRepository> changeListener) {
        super(project, repository, changeListener)
    }

    @Override
    void apply() {

    }

}
