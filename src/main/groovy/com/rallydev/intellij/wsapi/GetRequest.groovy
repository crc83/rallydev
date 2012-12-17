package com.rallydev.intellij.wsapi

class GetRequest {

    static final String WSAPI_VERSION = '1.39';

    URI server
    ApiObject wsapiObject

    GetRequest(URI server, ApiObject wsapiObject) {
        this.server = server
        this.wsapiObject = wsapiObject
    }

    String getUrl() {
        "${baseUrl}/${wsapiObject}.js"
    }

    private getBaseUrl() {
        return "${server}/slm/webservice/${WSAPI_VERSION}"
    }

}
