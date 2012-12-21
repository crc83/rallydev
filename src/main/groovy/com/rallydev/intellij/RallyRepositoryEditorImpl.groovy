package com.rallydev.intellij

import com.intellij.openapi.project.Project
import com.intellij.util.Consumer

import javax.swing.*

class RallyRepositoryEditorImpl extends RallyRepositoryEditor {

    public RallyRepositoryEditorImpl(final Project project, final RallyRepository repository, Consumer<RallyRepository> changeListener) {
        super(project, repository, changeListener)
    }

    @Override
    void apply() {

    }

}
