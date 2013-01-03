package com.rallydev.intellij.wsapi.queries

import com.rallydev.intellij.wsapi.ApiObject
import com.rallydev.intellij.wsapi.GetRequest
import com.rallydev.intellij.wsapi.QueryBuilder
import com.rallydev.intellij.wsapi.RallyClient
import com.rallydev.intellij.wsapi.typedefs.Workspace

import static com.rallydev.intellij.wsapi.QueryBuilder.Operator.eq

class TypedefQuery {
    RallyClient client

    public TypedefQuery(RallyClient client) {
        this.client = client
    }

    //unused, pull back out later if I don't need it
    void findWorkspace() {
        String query = new QueryBuilder()
                .withDisjunction('ElementName', eq, Workspace.ELEMENT_NAME)
                .toString()
        GetRequest request = new GetRequest(ApiObject.TYPE_DEFINITION)
                .withFetch()
                .withMaxPageSize()
                .withQuery(query)

        client.makeRequest(request)
    }

}
