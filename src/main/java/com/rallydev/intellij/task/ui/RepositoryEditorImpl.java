package com.rallydev.intellij.task.ui;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.tasks.config.BaseRepositoryEditor;
import com.intellij.util.Consumer;
import com.rallydev.intellij.config.RallyConfig;
import com.rallydev.intellij.task.RallyRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RepositoryEditorImpl extends RepositoryEditor {


    public RepositoryEditorImpl(Project project, RallyRepository repository, Consumer<RallyRepository> changeListener) {
        super(project, repository, changeListener);
    }

    @Override
    public void apply() {
        super.apply();
    }
}
