package com.rallydev.intellij.wsapi.queries

class TypedefQuerySpec extends BaseQuerySpec {

    def "Check query"() {
        given:
        TypedefQuery query = new TypedefQuery(recordingClient)

        when:
        query.findWorkspace()

        then:
        requests.contains(server + '/slm/webservice/1.39/typedefinition.js?fetch=true&pagesize=200&query=(ElementName = "WorkSpace")')
    }

}
