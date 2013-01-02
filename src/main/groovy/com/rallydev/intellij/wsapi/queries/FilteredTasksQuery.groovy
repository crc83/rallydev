package com.rallydev.intellij.wsapi.queries

import com.rallydev.intellij.task.RallyTask
import com.rallydev.intellij.task.RallyTaskFactory
import com.rallydev.intellij.wsapi.ApiObject
import com.rallydev.intellij.wsapi.ApiResponse
import com.rallydev.intellij.wsapi.GetRequest
import com.rallydev.intellij.wsapi.QueryBuilder
import com.rallydev.intellij.wsapi.RallyClient

import java.text.SimpleDateFormat

import static com.rallydev.intellij.wsapi.QueryBuilder.Operator.gt

class FilteredTasksQuery {

    RallyClient client

    public FilteredTasksQuery(RallyClient client) {
        this.client = client
    }

    Collection<RallyTask> findTasks(String keyword, int max, long since) {
        def defectRequest = new GetRequest(ApiObject.DEFECT)
                .withFetch()
                .withPageSize(max)
        def requirementRequest = new GetRequest(ApiObject.HIERARCHICAL_REQUIREMENT)
                .withFetch()
                .withPageSize(max)

        QueryBuilder query = new QueryBuilder()
        if (keyword) {
            query.withKeyword(keyword)
        }
        if (since) {
            String date = new SimpleDateFormat(ApiResponse.RALLY_DATE_FORMAT).format(new Date(since))
            query.withConjunction('LastUpdateDate', gt, date)
        }
        if (query.hasConditions()) {
            defectRequest.withQuery(query.toString())
            requirementRequest.withQuery(query.toString())
        }

        List<RallyTask> rallyTasks = []
        def request = client.makeRequest(requirementRequest)
        rallyTasks.addAll(RallyTaskFactory.tasksFromResponse(request))
        rallyTasks.addAll(RallyTaskFactory.tasksFromResponse(client.makeRequest(defectRequest)))

        return rallyTasks
    }

}
