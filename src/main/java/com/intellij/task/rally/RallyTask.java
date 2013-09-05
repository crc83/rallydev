package com.intellij.task.rally;

import com.intellij.tasks.Comment;
import com.intellij.tasks.Task;
import com.intellij.tasks.TaskType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sbelei.rally.domain.BasicEntity;
import org.sbelei.rally.domain.Defect;
import org.sbelei.rally.domain.Story;

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
    TaskType type;
    Icon icon;

    public RallyTask(BasicEntity entity) {
        super();
        if (entity instanceof Defect) {
            Defect defect = (Defect) entity;
            id = defect.formattedId;
            summary = defect.name;
            String iconName = "rally_defect_P3.png"; // default name
            icon =  new ImageIcon(this.getClass().getClassLoader().getResource("rally_defect_"+defect.getPriorityShort()+".png"), "Rally Defect Icon");
            type = TaskType.BUG;
        }
        if (entity instanceof Story) {
            Story story = (Story) entity;
            id = story.formattedId;
            summary = story.name;
            icon =  new ImageIcon(this.getClass().getClassLoader().getResource("rally_feature.png"), "Rally Feature Icon");
            type = TaskType.FEATURE;
        }

        description = entity.toString();
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
        return icon;
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