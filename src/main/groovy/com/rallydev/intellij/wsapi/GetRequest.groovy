package com.rallydev.intellij.wsapi

import org.apache.commons.httpclient.util.URIUtil

class GetRequest {
    static final String WSAPI_VERSION = '1.39'
    static int MAX_PAGE_SIZE = 200
    static int MIN_PAGE_SIZE = 1

    ApiObject wsapiObject
    String objectId

    Map<String, String> params = [:]

    GetRequest(ApiObject wsapiObject) {
        this.wsapiObject = wsapiObject
    }

    String getUrl(URL server) {
        "${baseUrl(server)}/${endPoint}.js${queryString}"
    }

    String getEncodedUrl(URL server) {
        "${baseUrl(server)}/${endPoint}.js${URIUtil.encodeQuery(queryString)}"
    }

    private String getQueryString() {
        params ? '?' + params.collect { key, value -> "${key}=${value}" }.join('&') : ''
    }

    private String getEndPoint() {
        return objectId ? "${wsapiObject}/${objectId}" : "${wsapiObject}"
    }

    GetRequest withFetch() {
        params['fetch'] = true
        return this
    }

    GetRequest withPageSize(Integer pageSize) {
        params['pagesize'] = between(pageSize, MIN_PAGE_SIZE, MAX_PAGE_SIZE)
        return this
    }

    GetRequest withMaxPageSize() {
        return withPageSize(MAX_PAGE_SIZE)
    }

    GetRequest withQuery(String query) {
        params['query'] = query
        return this
    }

    GetRequest withObjectId(String objectId) {
        this.objectId = objectId
        return this
    }

    private baseUrl(URL server) {
        return "${server}/slm/webservice/${WSAPI_VERSION}"
    }

    private int between(int number, int min, int max) {
        Math.min(Math.max(number, min), max)
    }

}
