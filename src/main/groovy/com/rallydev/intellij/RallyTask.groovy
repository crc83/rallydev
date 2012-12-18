package com.rallydev.intellij

import com.intellij.tasks.Comment
import com.intellij.tasks.Task
import com.intellij.tasks.TaskType

import javax.swing.Icon

class RallyTask extends Task {

    String id
    String summary
    String description
    Date updated
    Date created
    boolean closed
    boolean issue
    String issueUrl
    TaskType type = TaskType.BUG

    @Override
    Comment[] getComments() {
        return new Comment[0]
    }

    @Override
    Icon getIcon() {
        return null
    }

}
