package com.rallydev.intellij.wsapi.queries

import com.rallydev.intellij.wsapi.ApiObject
import com.rallydev.intellij.wsapi.GetRequest
import com.rallydev.intellij.wsapi.RallyClient
import com.rallydev.intellij.wsapi.typedefs.Project

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
        GetRequest request = new GetRequest(ApiObject.PROJECT)
                .withFetch()
                .withMaxPageSize()
        return workspacesFromResponse(client.makeRequest(request))
    }
}
