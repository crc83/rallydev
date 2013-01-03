package com.rallydev.intellij.task

import com.intellij.tasks.Comment
import com.intellij.tasks.Task
import com.intellij.tasks.TaskType

import javax.swing.*

class RallyTask extends Task {

    String id
    String summary
    String description
    Date updated
    Date created
    boolean closed
    String issueUrl
    TaskType type = TaskType.OTHER

    @Override
    Comment[] getComments() {
        return new Comment[0]
    }

    @Override
    Icon getIcon() {
        return null
    }

    boolean isIssue() {
        return true
    }

}
