package com.rallydev.intellij.wsapi.queries

import com.rallydev.intellij.taskold.RallyTaskFactory
import com.rallydev.intellij.wsapi.ApiResponse

class TaskFromIdQuerySpec extends BaseQuerySpec {

    def "findTasks query"() {
        given:
        GroovySpy(RallyTaskFactory, global: true)
        1 * RallyTaskFactory.singleTaskFromResponse(_ as ApiResponse) >> { a -> [] }

        and:
        TaskFromIdQuery query = new TaskFromIdQuery(recordingClient)

        when:
        query.findTask('1234')

        then: 'Check that proper requests were made'
        requests == [(server + '/slm/webservice/1.39/artifact/1234.js?fetch=true')]
    }

}
