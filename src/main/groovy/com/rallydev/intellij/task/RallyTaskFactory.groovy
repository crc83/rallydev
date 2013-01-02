package com.rallydev.intellij.task

import com.google.gson.JsonElement
import com.intellij.tasks.TaskType
import com.rallydev.intellij.wsapi.ApiResponse

import java.text.SimpleDateFormat

class RallyTaskFactory {

    private static Date parseWsapiDate(String rawDate) {
        try {
            return new SimpleDateFormat(ApiResponse.RALLY_DATE_FORMAT).parse(rawDate)
        } catch (Exception e) {
            return null
        }
    }

    static Collection<RallyTask> tasksFromResponse(ApiResponse response) {
        Collection<RallyTask> tasks = []
        response.results.each { result ->
            RallyTask task = fromJsonRequirement(result)
            switch (result._type?.getAsString()) {
                case ~/(?i)hierarchicalrequirement/:
                    task.type = TaskType.FEATURE
                    break
                case ~/(?i)defect/:
                    task.type = TaskType.BUG
                    break
                default:
                    task.type = TaskType.OTHER
            }
            tasks << task
        }
        return tasks
    }

    static RallyTask singleTaskFromResponse(ApiResponse response) {
        if (response.json.HierarchicalRequirement) {
            RallyTask task = fromJsonRequirement(response.json.HierarchicalRequirement)
            task.type = TaskType.FEATURE
            return task
        }
        if (response.json.Defect) {
            RallyTask task = fromJsonRequirement(response.json.Defect)
            task.type = TaskType.BUG
            return task
        }
        throw new IllegalArgumentException("Un-parsable response")
    }

    private static RallyTask fromJsonRequirement(JsonElement taskJson) {
        return new RallyTask(
                id: taskJson.ObjectID?.getAsString(),
                summary: taskJson.FormattedID?.getAsString() + ': ' + taskJson.Name?.getAsString(),
                description: taskJson.Description?.getAsString(),
                created: parseWsapiDate(taskJson.CreationDate?.getAsString()),
                updated: parseWsapiDate(taskJson.LastUpdateDate?.getAsString()),
                issueUrl: taskJson._ref.getAsString(),
        )
    }

}
