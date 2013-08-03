package com.rallydev.intellij.wsapi.queries

import com.rallydev.intellij.taskold.RallyTaskFactory
import com.rallydev.intellij.wsapi.ApiResponse

class FilteredTasksQuerySpec extends BaseQuerySpec {

    def setup() {
        GroovySpy(RallyTaskFactory, global: true)
        2 * RallyTaskFactory.tasksFromResponse(_ as ApiResponse) >> { a -> [] }
    }

    def "findTasks with query"() {
        given:
        TasksFilteredQuery query = new TasksFilteredQuery(recordingClient)

        when:
        query.findTasks('someQuery', 50, 0)

        then:
        requests.contains(server + '/slm/webservice/1.39/hierarchicalrequirement.js?fetch=true&pagesize=50&query=(Name contains "someQuery")')
        requests.contains(server + '/slm/webservice/1.39/defect.js?fetch=true&pagesize=50&query=(Name contains "someQuery")')
    }

    def "findTasks with since"() {
        given:
        TasksFilteredQuery query = new TasksFilteredQuery(recordingClient)

        when:
        query.findTasks('someQuery', 50, 1356038641966L)

        then:
        requests.contains(server + '/slm/webservice/1.39/hierarchicalrequirement.js?fetch=true&pagesize=50&query=((Name contains "someQuery") AND (LastUpdateDate > "2012-12-20T01:24:01.966Z"))')
        requests.contains(server + '/slm/webservice/1.39/defect.js?fetch=true&pagesize=50&query=((Name contains "someQuery") AND (LastUpdateDate > "2012-12-20T01:24:01.966Z"))')
    }

}
