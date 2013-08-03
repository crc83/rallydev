package com.rallydev.intellij.wsapi.queries

import com.rallydev.intellij.wsapi.ApiObject
import com.rallydev.intellij.wsapi.ApiResponse
import com.rallydev.intellij.wsapi.GetRequest
import com.rallydev.intellij.wsapi.RallyClient
import com.rallydev.intellij.wsapi.typedefs.Project
import com.rallydev.intellij.wsapi.typedefs.Workspace
import com.rallydev.rest.RallyRestApi
import com.rallydev.rest.request.QueryRequest
import com.rallydev.rest.response.QueryResponse

/**
 * Created with IntelliJ IDEA.
 * User: sbelei
 * Date: 24.05.13
 * Time: 0:39
 * To change this template use File | Settings | File Templates.
 */
class ProjectsQuery {

    RallyClient client

    ProjectsQuery(RallyClient client) {
        this.client = client
    }

    Collection<Project> findAllProjects() {
        QueryRequest request = new QueryRequest(ApiObject.PROJECT.name())
        RallyRestApi restApi = new RallyRestApi(new URI("https://rally1.rallydev.com"), "sbelei@softserveinc.com", "S123b9876");
        QueryResponse responce = restApi.query(request);

        return projectsFromResponse(responce)
    }

    private Collection<Project> projectsFromResponse(QueryResponse response) {
        List<Project> projects = []
        response.results.each { result ->
            projects << new Project(
                    objectId: result.ObjectID?.getAsString(),
                    name: result.Name?.getAsString(),
                    ref: result._ref?.getAsString()
            )
        }
        return projects
    }
}
