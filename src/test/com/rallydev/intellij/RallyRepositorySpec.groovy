package com.rallydev.intellij

import spock.lang.Specification

class RallyRepositorySpec extends Specification {

    def "Check that clone returns identical repo"() {
        given:
        RallyRepository sourceRepository = new RallyRepository()

        when:
        def clonedRepository = sourceRepository.clone()

        then:
        clonedRepository
    }

}
