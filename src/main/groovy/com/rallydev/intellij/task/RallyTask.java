package com.rallydev.intellij.task;

import com.intellij.tasks.Comment;
import com.intellij.tasks.Task;
import com.intellij.tasks.TaskType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sbelei.rally.domain.BasicEntity;

import javax.swing.*;
import java.util.Date;

public class RallyTask extends Task {

    String id;
    String summary;
    String description;
    Date updated;
    Date created;
    boolean closed;
    String issueUrl;
    TaskType type = TaskType.OTHER;

    public RallyTask(BasicEntity entity) {
        super();
        id = entity.id;
        description = entity.toString();
        summary = entity.name;
        issueUrl = entity.ref;
    }

    @NotNull
    @Override
    public String getId() {
        return id;
    }

    @NotNull
    @Override
    public String getSummary() {
        return summary;
    }

    @Nullable
    @Override
    public String getDescription() {
        return description;
    }

    @NotNull
    @Override
    public Comment[] getComments() {
        return new Comment[0];
    }

    @NotNull
    @Override
    public Icon getIcon() {
        return new ImageIcon(this.getClass().getClassLoader().getResource("rally.png"), "Rally Icon");
    }

    @NotNull
    @Override
    public TaskType getType() {
        return type;
    }

    @Nullable
    @Override
    public Date getUpdated() {
        return updated;
    }

    @Nullable
    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public boolean isIssue() {
        return true;
    }

    @Nullable
    @Override
    public String getIssueUrl() {
        return issueUrl;
    }

}