package com.rallydev.intellij.wsapi

import spock.lang.Specification

class GetRequestSpec extends Specification {

    def "Simple object, no filter"() {
        given:
        String rallyUri = 'https://rally1.rallydev.com/'
        GetRequest wsapiRequest = new GetRequest(rallyUri.toURI(), ApiObject.WORKSPACE)

        expect:
        wsapiRequest.url == "${rallyUri}/slm/webservice/${GetRequest.WSAPI_VERSION}/workspace.js"
    }

}
