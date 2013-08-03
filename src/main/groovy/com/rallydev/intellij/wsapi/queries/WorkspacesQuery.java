package com.rallydev.intellij.wsapi.queries;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rallydev.intellij.wsapi.ApiObject;
import com.rallydev.intellij.wsapi.ApiResponse;
import com.rallydev.intellij.wsapi.GetRequest;
import com.rallydev.intellij.wsapi.RallyClient;
import com.rallydev.intellij.wsapi.typedefs.Workspace;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class WorkspacesQuery {

    RallyClient client;

    public WorkspacesQuery(RallyClient client) {
        this.client = client;
    }

    public Collection<Workspace> findAllWorkspaces() throws URISyntaxException, IOException {
        QueryRequest request = new QueryRequest(ApiObject.WORKSPACE.name());
        RallyRestApi restApi = new RallyRestApi(new URI("https://rally1.rallydev.com"), "sbelei@softserveinc.com", "S123b9876");
        return workspacesFromResponse(restApi.query(request));
    }

    private Collection<Workspace> workspacesFromResponse(QueryResponse response) {
        List<Workspace> workspaces = new ArrayList<Workspace>();
        JsonArray jsonWorkspaces = response.getResults();
        for (int i = 0; i < jsonWorkspaces .size(); i++) {
            JsonObject obj = jsonWorkspaces .get(i).getAsJsonObject();
            Workspace ws = new Workspace();

            if (obj.get("_refObjectName") != null) {
                ws.setName(obj.get("_refObjectName").toString());
            }
            if (obj.get("ObjectID") != null) {
                ws.setObjectId(obj.get("ObjectID").toString());
            }


            workspaces.add(ws);
        }

        return workspaces;
    }

}
