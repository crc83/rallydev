package com.rallydev.intellij.wsapi

import spock.lang.Specification

class WsapiObjectSpec extends Specification {

    def "Check toString implementation"() {
        expect:
        (ApiObject.WORKSPACE).toString() == 'workspace'
    }

}
