package com.rallydev.intellij

import spock.lang.Specification

class RallyRepositorySpec extends Specification {

    def "Check clone"() {
        given:
        RallyRepository sourceRepository = new RallyRepository()

        when:
        RallyRepository clonedRepository = sourceRepository.clone()

        then:
        clonedRepository
    }

}
