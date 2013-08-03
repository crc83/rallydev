package com.rallydev.intellij.wsapi.queries;

import com.rallydev.intellij.taskold.RallyTask;
import com.rallydev.intellij.taskold.RallyTaskFactory;
import com.rallydev.intellij.wsapi.ApiObject;
import com.rallydev.intellij.wsapi.RallyClient;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Ref;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TasksFilteredQuery {

    RallyClient client;

    public TasksFilteredQuery(RallyClient client) {
        this.client = client;
    }

    public Collection<RallyTask> findTasks(String projectRef, String keyword, int max, long since) throws URISyntaxException, IOException {
        QueryRequest defectRequest = new QueryRequest(ApiObject.DEFECT.name());
//        defectRequest.setWorkspace();
        defectRequest.setProject(Ref.getRelativeRef(projectRef));
        defectRequest.setPageSize(max);

//        def requirementRequest = new GetRequest(ApiObject.HIERARCHICAL_REQUIREMENT)
//                .withFetch()
//                .withPageSize(max)
//
//        QueryBuilder query = new QueryBuilder()
//        if (keyword) {
//            query.withKeyword(keyword)
//        }
//        if (since) {
//            String date = new SimpleDateFormat(ApiResponse.RALLY_DATE_FORMAT).format(new Date(since))
//            query.withConjunction('LastUpdateDate', gt, date)
//        }
//
//        if (query.hasConditions()) {
//            defectRequest.setQueryFilter(query.toString())
//            requirementRequest.withQuery(query.toString())
//        }
//
        List<RallyTask> rallyTasks = new ArrayList<RallyTask>();
//        def request = client.makeRequest(requirementRequest)
//        rallyTasks.addAll(RallyTaskFactory.tasksFromResponse(request))
        RallyRestApi restApi = new RallyRestApi(new URI("https://rally1.rallydev.com"), "sbelei@softserveinc.com", "S123b9876");
        QueryResponse responce = restApi.query(defectRequest);
        rallyTasks.addAll(RallyTaskFactory.tasksFromResponse(responce));

        return rallyTasks;
    }

}
