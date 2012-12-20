package com.rallydev.intellij.wsapi.queries

import com.rallydev.intellij.RallyTask
import com.rallydev.intellij.RallyTaskFactory
import com.rallydev.intellij.wsapi.ApiObject
import com.rallydev.intellij.wsapi.GetRequest
import com.rallydev.intellij.wsapi.QueryBuilder
import com.rallydev.intellij.wsapi.RallyClient

class FilteredTasksQuery {

    RallyClient client

    public FilteredTasksQuery(RallyClient client) {
        this.client = client
    }

    Collection<RallyTask> findTasks(String query, int max) {
        def defectRequest = new GetRequest(ApiObject.DEFECT)
                .withFetch()
                .withPageSize(max)
        def requirementRequest = new GetRequest(ApiObject.HIERARCHICAL_REQUIREMENT)
                .withFetch()
                .withPageSize(max)
        if (query) {
            String queryParam = QueryBuilder.keywordQuery(query) as String
            requirementRequest.withQuery(queryParam)
            defectRequest.withQuery(queryParam)
        }

        List<RallyTask> rallyTasks = []
        rallyTasks.addAll(RallyTaskFactory.tasksFromResponse(client.makeRequest(requirementRequest)))
        rallyTasks.addAll(RallyTaskFactory.tasksFromResponse(client.makeRequest(defectRequest)))

        return rallyTasks
    }

}
