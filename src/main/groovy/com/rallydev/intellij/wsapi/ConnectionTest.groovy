package com.rallydev.intellij.wsapi

class ConnectionTest {

    URI server
    RallyClient rallyClient

    ConnectionTest(URI server, RallyClient rallyClient) {
        this.server = server
        this.rallyClient = rallyClient
    }

    void doTest() throws Exception {
        ApiResponse response = rallyClient.makeRequest(new GetRequest(server, ApiObject.WORKSPACE))
        if (!response?.results) {
            throw new RuntimeException("Incorrect response from server\n${response ?: 'No response'}")
        }
    }

}
