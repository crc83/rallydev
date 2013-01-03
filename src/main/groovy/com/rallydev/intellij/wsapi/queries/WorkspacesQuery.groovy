package com.rallydev.intellij.wsapi.queries

import com.rallydev.intellij.wsapi.ApiObject
import com.rallydev.intellij.wsapi.ApiResponse
import com.rallydev.intellij.wsapi.GetRequest
import com.rallydev.intellij.wsapi.RallyClient
import com.rallydev.intellij.wsapi.typedefs.Workspace

class WorkspacesQuery {

    RallyClient client

    WorkspacesQuery(RallyClient client) {
        this.client = client
    }

    Collection<Workspace> findAllWorkspaces() {
        GetRequest request = new GetRequest(ApiObject.WORKSPACE)
                .withFetch()
                .withMaxPageSize()
        return workspacesFromResponse(client.makeRequest(request))
    }

    private Collection<Workspace> workspacesFromResponse(ApiResponse response) {
        List<Workspace> workspaces = []
        response.results.each { result ->
            workspaces << new Workspace(name: result.Name?.getAsString())
        }
        return workspaces
    }

}
