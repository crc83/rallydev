package com.rallydev.intellij.task

import com.intellij.openapi.project.Project
import com.intellij.util.Consumer

@Deprecated
class RallyRepositoryEditorImpl extends RallyRepositoryEditor {

    public RallyRepositoryEditorImpl(final Project project, final RallyRepository2 repository, Consumer<RallyRepository2> changeListener) {
        super(project, repository, changeListener)
    }

    @Override
    void apply() {

    }

}
