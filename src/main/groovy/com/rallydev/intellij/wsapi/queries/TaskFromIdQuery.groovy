package com.rallydev.intellij.wsapi.queries

import com.rallydev.intellij.task.RallyTask
import com.rallydev.intellij.task.RallyTaskFactory
import com.rallydev.intellij.wsapi.ApiObject
import com.rallydev.intellij.wsapi.GetRequest
import com.rallydev.intellij.wsapi.RallyClient

class TaskFromIdQuery {
    RallyClient client

    public TaskFromIdQuery(RallyClient client) {
        this.client = client
    }

    RallyTask findTask(String id) {
        GetRequest request = new GetRequest(ApiObject.ARTIFACT)
                .withFetch()
                .withObjectId(id)
        def response = client.makeRequest(request)
        return RallyTaskFactory.singleTaskFromResponse(response)
    }

}
