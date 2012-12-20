package com.rallydev.intellij.wsapi.queries

import com.rallydev.intellij.wsapi.ApiResponse
import com.rallydev.intellij.wsapi.RallyClient
import spock.lang.Specification

class FilteredTasksQuerySpec extends Specification {

    String minimalResponseJson = '{"QueryResult": { "Results":[ { } ] }}'

    def "findTasks query"() {
        given:
        RallyClient client = Mock(RallyClient)
        2 * client.makeRequest(_) >> { new ApiResponse(minimalResponseJson) }
        FilteredTasksQuery query = new FilteredTasksQuery(client)

        when:
        query.findTasks('query', 50)

        then:
        true
    }

}
