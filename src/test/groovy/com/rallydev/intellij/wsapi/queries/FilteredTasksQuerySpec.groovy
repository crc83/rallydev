package com.rallydev.intellij.wsapi.queries

import com.rallydev.intellij.task.RallyTaskFactory
import com.rallydev.intellij.wsapi.ApiResponse
import com.rallydev.intellij.wsapi.GetRequest
import com.rallydev.intellij.wsapi.RallyClient
import spock.lang.Shared
import spock.lang.Specification

class FilteredTasksQuerySpec extends Specification {
    static String server = 'http://asdf'

    @Shared
    RallyClient client
    List<String> requests = []

    def setup() {
        GroovySpy(RallyTaskFactory, global: true)
        2 * RallyTaskFactory.tasksFromResponse(_ as ApiResponse) >> { a -> [] }
        client = Mock(RallyClient)
        client.makeRequest(_ as GetRequest) >> { GetRequest request ->
            requests << request.getUrl(server.toURL())
            return new ApiResponse('')
        }

    }

    def "findTasks with query"() {
        given:
        FilteredTasksQuery query = new FilteredTasksQuery(client)

        when:
        query.findTasks('someQuery', 50, 0)

        then:
        requests.contains(server + '/slm/webservice/1.39/hierarchicalrequirement.js?fetch=true&pagesize=50&query=(Name contains "someQuery")')
        requests.contains(server + '/slm/webservice/1.39/defect.js?fetch=true&pagesize=50&query=(Name contains "someQuery")')
    }

    def "findTasks with since"() {
        given:
        FilteredTasksQuery query = new FilteredTasksQuery(client)

        when:
        query.findTasks('someQuery', 50, 1356038641966L)

        then:
        requests.contains(server + '/slm/webservice/1.39/hierarchicalrequirement.js?fetch=true&pagesize=50&query=((Name contains "someQuery") AND (LastUpdateDate > "2012-12-20T01:24:01.966Z"))')
        requests.contains(server + '/slm/webservice/1.39/defect.js?fetch=true&pagesize=50&query=((Name contains "someQuery") AND (LastUpdateDate > "2012-12-20T01:24:01.966Z"))')
    }

}
