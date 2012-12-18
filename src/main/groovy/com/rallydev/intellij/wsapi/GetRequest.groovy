package com.rallydev.intellij.wsapi

class GetRequest {
    static final String WSAPI_VERSION = '1.39';

    URI server
    ApiObject wsapiObject

    Map<String, String> params = [:]

    GetRequest(URI server, ApiObject wsapiObject) {
        this.server = server
        this.wsapiObject = wsapiObject
    }

    static requirementGetRequest(URI server) {
        new GetRequest(server, ApiObject.HIERARCHICAL_REQUIREMENT).withFetch()
    }

    String getUrl() {
        "${baseUrl}/${wsapiObject}.js${queryString}"
    }

    String getQueryString() {
        params ? '?' + params.collect { k, v -> "${k}=${v}" }.join('&') : ''
    }

    GetRequest withFetch() {
        params['fetch'] = true
        return this
    }

    private getBaseUrl() {
        return "${server}/slm/webservice/${WSAPI_VERSION}"
    }

}
