package com.rallydev.intellij.wsapi.queries

import com.rallydev.intellij.RallyTaskFactory
import com.rallydev.intellij.wsapi.ApiResponse
import com.rallydev.intellij.wsapi.GetRequest
import com.rallydev.intellij.wsapi.RallyClient
import spock.lang.Specification

class TaskFromIdQuerySpec extends Specification {

    def "findTasks query"() {
        given:
        GroovySpy(RallyTaskFactory, global: true)
        1 * RallyTaskFactory.singleTaskFromResponse(_ as ApiResponse) >> { a -> [] }

        and: 'Mock a client that records requests'
        RallyClient client = Mock(RallyClient)
        List<String> requests = []
        client.makeRequest(_ as GetRequest) >> { GetRequest request ->
            requests << request.getUrl(''.toURI())
            return new ApiResponse('')
        }

        and:
        TaskFromIdQuery query = new TaskFromIdQuery(client)

        when:
        query.findTask('1234')

        then: 'Check that proper requests were made'
        requests == [('/slm/webservice/1.39/artifact/1234.js?fetch=true')]
    }


}
