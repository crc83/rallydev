package com.rallydev.intellij.wsapi.queries

import com.rallydev.intellij.SpecUtils
import com.rallydev.intellij.wsapi.ApiResponse
import com.rallydev.intellij.wsapi.GetRequest
import com.rallydev.intellij.wsapi.RallyClient
import spock.lang.Shared
import spock.lang.Specification

abstract class BaseQuerySpec extends Specification {
    static String server = 'http://asdf'

    @Shared
    RallyClient recordingClient
    List<String> requests = []

    def setup() {
        recordingClient = Mock(RallyClient)
        recordingClient.makeRequest(_ as GetRequest) >> { GetRequest request ->
            requests << request.getUrl(server.toURL())
            return new ApiResponse(SpecUtils.minimalResponseJson)
        }
    }

}
