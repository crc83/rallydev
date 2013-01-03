package com.rallydev.intellij.wsapi.queries

import com.rallydev.intellij.wsapi.ApiResponse
import com.rallydev.intellij.wsapi.GetRequest
import com.rallydev.intellij.wsapi.RallyClient

class WorkspacesQuerySpec extends BaseQuerySpec {

    def "Check request"() {
        given:
        WorkspacesQuery query = new WorkspacesQuery(recordingClient)

        when:
        query.findAllWorkspaces()

        then:
        requests.contains(server + '/slm/webservice/1.39/workspace.js?fetch=true&pagesize=200')
    }

    def "Check response parsing"() {
        given:
        RallyClient rallyClient = Mock(RallyClient)
        rallyClient.makeRequest(_ as GetRequest) >> {
            return new ApiResponse(WorkspacesQuerySpec.classLoader.getResourceAsStream('workspace_single.json').text)
        }

        and:
        WorkspacesQuery query = new WorkspacesQuery(rallyClient)

        when:
        def workspaces = query.findAllWorkspaces()

        then:
        workspaces.size() == 1
        workspaces[0].objectId == '11864'
        workspaces[0].name == 'Workspace 1'

    }

}
