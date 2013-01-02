package com.rallydev.intellij.config

import spock.lang.Specification

class RallyConfigSpec extends Specification {

    def "Load state correctly copies values"() {
        given:
        def password = 'monkey'
        def userName = 'matt'
        def url = 'http://localhost:70001/'

        and:
        RallyConfig storedConfig = new RallyConfig(
                password: password, userName: userName, url: url, rememberPassword: true
        )
        RallyConfig loadedConfig = new RallyConfig()

        when:
        loadedConfig.loadState(storedConfig)

        then:
        loadedConfig.password == password
        loadedConfig.userName == userName
        loadedConfig.url == url
        loadedConfig.rememberPassword
    }

}
