package com.rallydev.intellij.wsapi

import spock.lang.Specification

import static com.rallydev.intellij.wsapi.QueryBuilder.Operator.contains
import static com.rallydev.intellij.wsapi.QueryBuilder.Operator.eq

class QueryBuilderSpec extends Specification {

    def "Single disjunction, operators: [contains]"() {
        when:
        QueryBuilder builder = new QueryBuilder()
                .withDisjunction('Name', contains, 'US3')

        then:
        builder.toString() == '(Name contains "US3")'
    }

    def "Two disjunctions, operators: [contains, equals]"() {
        when:
        QueryBuilder builder = new QueryBuilder()
                .withDisjunction('Name', contains, 'US3')
                .withDisjunction('FormattedId', eq, 'US3')

        then:
        builder.toString() == '((Name contains "US3") OR (FormattedId = "US3"))'
    }

    def "Three disjunctions, operators: [contains, equals]"() {
        when:
        QueryBuilder builder = new QueryBuilder()
                .withDisjunction('Name', contains, 'US3')
                .withDisjunction('FormattedId', eq, 'US3')
                .withDisjunction('ObjectID', eq, '5')

        then:
        builder.toString() == '(((Name contains "US3") OR (FormattedId = "US3")) OR (ObjectID = "5"))'
    }

    def "Single conjunction"() {
        when:
        QueryBuilder builder = new QueryBuilder()
                .withConjunction('Name', contains, 'US3')

        then:
        builder.toString() == '(Name contains "US3")'
    }

    def "Two conjunctions"() {
        when:
        QueryBuilder builder = new QueryBuilder()
                .withConjunction('Name', contains, 'US3')
                .withConjunction('Name', contains, 'US4')

        then:
        builder.toString() == '((Name contains "US3") AND (Name contains "US4"))'
    }

    def "When mixing conjunctions and disjunctions, conjunctions should appear last"() {
        when:
        QueryBuilder builder = new QueryBuilder()
                .withConjunction('Name', contains, 'US3')
                .withDisjunction('Name', contains, 'US4')
                .withConjunction('Name', contains, 'US5')
                .withDisjunction('Name', contains, 'US6')

        then:
        builder.toString() == '((((Name contains "US4") OR (Name contains "US6")) AND (Name contains "US3")) AND (Name contains "US5"))'
    }

    def "String keyword"() {
        when:
        QueryBuilder builder = new QueryBuilder().withKeyword('Bob')

        then:
        builder.toString() == '(Name contains "Bob")'
    }

    def "Formatted id keyword"() {
        when:
        QueryBuilder builder = new QueryBuilder().withKeyword('US3')

        then:
        builder.toString() == '((Name contains "US3") OR (FormattedId = "US3"))'
    }

    def "Integer keyword"() {
        when:
        QueryBuilder builder = new QueryBuilder().withKeyword('5')

        then:
        builder.toString() == '((Name contains "5") OR (ObjectId = "5"))'
    }

}
