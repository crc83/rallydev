package com.rallydev.intellij.wsapi

import com.rallydev.rest.request.QueryRequest

class ConnectionTest {

    URL server
    RallyClient rallyClient

    ConnectionTest(RallyClient rallyClient) {
        this.server = server
        this.rallyClient = rallyClient
    }

    void doTest() throws Exception {

        ApiResponse response = rallyClient.makeRequest(new QueryRequest(ApiObject.WORKSPACE.name()))
        if (!response?.results) {
            throw new RuntimeException("Incorrect response from server\n${response ?: 'No response'}")
        }
    }

}
