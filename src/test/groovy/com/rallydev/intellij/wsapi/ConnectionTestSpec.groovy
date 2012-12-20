package com.rallydev.intellij.wsapi

import spock.lang.Specification

class ConnectionTestSpec extends Specification {

    String minimalResponseJson = '{"QueryResult": { "Results":[ { } ] }}'

    def "Ensure doTest tries to make connection"() {
        given:
        RallyClient rallyClient = Mock(RallyClient)
        1 * rallyClient.makeRequest(_) >> { new ApiResponse(minimalResponseJson) }
        ConnectionTest connectionTest = new ConnectionTest('http://localhost:7001'.toURI(), rallyClient)

        when:
        connectionTest.doTest()

        then:
        true
    }

    def "Ensure exception when no response results"() {
        RallyClient rallyClient = Mock(RallyClient)
        1 * rallyClient.makeRequest(_) >> { null }
        ConnectionTest connectionTest = new ConnectionTest('http://localhost:7001'.toURI(), rallyClient)

        when:
        connectionTest.doTest()

        then:
        thrown(Exception)
    }

}
