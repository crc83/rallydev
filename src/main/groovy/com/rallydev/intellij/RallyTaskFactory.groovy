package com.rallydev.intellij

import com.google.gson.JsonElement
import com.intellij.tasks.TaskType
import com.rallydev.intellij.wsapi.ApiResponse

import java.text.SimpleDateFormat

class RallyTaskFactory {

    public static final RALLY_DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'"

    private static Date parseWsapiDate(String rawDate) {
        try {
            return new SimpleDateFormat(RALLY_DATE_FORMAT).parse(rawDate)
        } catch (Exception e) {
            return null
        }
    }

    static Collection<RallyTask> tasksFromResponse(ApiResponse response, TaskType taskType) {
        Collection<RallyTask> tasks = []
        response.results.each { result ->
            tasks << fromJsonRequirement(result)
        }
        tasks*.type = taskType
        return tasks
    }

    static RallyTask singleTaskFromResponse(ApiResponse response, TaskType taskType) {
        RallyTask task = fromJsonRequirement(response.json.members.values().iterator().next())
        task.type = taskType
        return task
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
