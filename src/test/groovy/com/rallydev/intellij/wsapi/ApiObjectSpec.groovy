package com.rallydev.intellij.wsapi

import spock.lang.Specification

class ApiObjectSpec extends Specification {

    def "Check toString implementation"() {
        expect:
        (ApiObject.WORKSPACE).toString() == 'workspace'

        and:
        (ApiObject.HIERARCHICAL_REQUIREMENT).toString() == 'hierarchicalrequirement'
    }

}
