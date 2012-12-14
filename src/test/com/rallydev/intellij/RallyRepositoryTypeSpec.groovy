package com.rallydev.intellij

import spock.lang.Shared
import spock.lang.Specification

class RallyRepositoryTypeSpec extends Specification {

    @Shared
    RallyRepositoryType repositoryType

    def setup() {
        repositoryType = new RallyRepositoryType()
    }

    def "Check name"() {
        expect:
        repositoryType.name == 'Rally'
    }

    def "Check that type class is correct"() {
        expect:
        repositoryType.repositoryClass == RallyRepository.class
    }

    def "Ensure create repository returns initialized RallyRepository"() {
        given:
        def repository = repositoryType.createRepository()

        expect:
        repository
        repository instanceof RallyRepository
        repository.repositoryType == repositoryType
    }

    def "Icon is found"() {
        expect:
        repositoryType.icon
    }

}
