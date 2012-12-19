package com.rallydev.intellij.wsapi

import spock.lang.Specification

class GetRequestSpec extends Specification {

    def "requirementGetRequest"() {
        when:
        GetRequest request = GetRequest.requirementGetRequest('https://rally1.rallydev.com/'.toURI())

        then:
        request.params['fetch'] == true
        request.wsapiObject == ApiObject.HIERARCHICAL_REQUIREMENT
    }

    def "defectGetRequest"() {
        when:
        GetRequest request = GetRequest.defectGetRequest('https://rally1.rallydev.com/'.toURI())

        then:
        request.params['fetch'] == true
        request.wsapiObject == ApiObject.DEFECT
    }

    def "Simple object, no filter"() {
        given:
        String rallyUri = 'https://rally1.rallydev.com/'
        GetRequest wsapiRequest = new GetRequest(rallyUri.toURI(), ApiObject.WORKSPACE)

        expect:
        wsapiRequest.url == "${rallyUri}/slm/webservice/${GetRequest.WSAPI_VERSION}/workspace.js"
    }

    def "Simple object with single query param"() {
        given:
        String rallyUri = 'https://rally1.rallydev.com/'
        GetRequest wsapiRequest = new GetRequest(rallyUri.toURI(), ApiObject.WORKSPACE)
                .withFetch()

        expect:
        wsapiRequest.url == "${rallyUri}/slm/webservice/${GetRequest.WSAPI_VERSION}/workspace.js?fetch=true"
    }

    def "With objectId"() {
        given:
        String rallyUri = 'https://rally1.rallydev.com/'
        GetRequest wsapiRequest = new GetRequest(rallyUri.toURI(), ApiObject.WORKSPACE)
                .withFetch()
                .withObjectId('5')

        expect:
        wsapiRequest.url == "${rallyUri}/slm/webservice/${GetRequest.WSAPI_VERSION}/workspace/5.js?fetch=true"
    }

    def "With query"() {
        given:
        String rallyUri = 'https://rally1.rallydev.com/'
        GetRequest wsapiRequest = new GetRequest(rallyUri.toURI(), ApiObject.WORKSPACE).withQuery('(Name contains "Matt")')

        expect:
        wsapiRequest.url == "${rallyUri}/slm/webservice/${GetRequest.WSAPI_VERSION}/workspace.js?query=(Name contains \"Matt\")"
    }

    def "Encoded query"() {
        given:
        String rallyUri = 'https://rally1.rallydev.com/'
        GetRequest wsapiRequest = new GetRequest(rallyUri.toURI(), ApiObject.WORKSPACE).withQuery('(Name contains "Matt")')

        expect:
        wsapiRequest.encodedUrl == "${rallyUri}/slm/webservice/${GetRequest.WSAPI_VERSION}/workspace.js?query=(Name%20contains%20%22Matt%22)"
    }

    def "With fetch"() {
        given:
        String rallyUri = 'https://rally1.rallydev.com/'
        GetRequest wsapiRequest = new GetRequest(rallyUri.toURI(), ApiObject.WORKSPACE).withFetch()

        expect:
        wsapiRequest.url == "${rallyUri}/slm/webservice/${GetRequest.WSAPI_VERSION}/workspace.js?fetch=true"
    }

    def "With pagesize"() {
        given:
        String rallyUri = 'https://rally1.rallydev.com/'
        GetRequest wsapiRequest

        when:
        wsapiRequest = new GetRequest(rallyUri.toURI(), ApiObject.WORKSPACE).withPageSize(5)

        then:
        wsapiRequest.url == "${rallyUri}/slm/webservice/${GetRequest.WSAPI_VERSION}/workspace.js?pagesize=5"

        when:
        wsapiRequest = new GetRequest(rallyUri.toURI(), ApiObject.WORKSPACE).withPageSize(-3)

        then:
        wsapiRequest.url == "${rallyUri}/slm/webservice/${GetRequest.WSAPI_VERSION}/workspace.js?pagesize=${GetRequest.MIN_PAGE_SIZE}"

        when:
        wsapiRequest = new GetRequest(rallyUri.toURI(), ApiObject.WORKSPACE).withPageSize(200)

        then:
        wsapiRequest.url == "${rallyUri}/slm/webservice/${GetRequest.WSAPI_VERSION}/workspace.js?pagesize=${GetRequest.MAX_PAGE_SIZE}"
    }

}
