package com.rallydev.intellij

import spock.lang.Specification

class RallyRepositorySpec extends Specification {

    def "Check that clone returns identical repo"() {
        given:
        String serverUrl = 'http://www.google.com'
        String username = 'ue@test.com'
        String password = 'password'

        and:
        RallyRepository sourceRepository = new RallyRepository(
                url: serverUrl, username: username, password: password
        )

        when:
        def clonedRepository = sourceRepository.clone()

        then:
        clonedRepository
        clonedRepository.url == serverUrl
        clonedRepository.username == username
        clonedRepository.password == password
    }

}
