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

    def "String keyword"() {
        when:
        QueryBuilder builder = QueryBuilder.keywordQuery('Bob')

        then:
        builder.toString() == '(Name contains "Bob")'
    }

    def "Formatted id keyword"() {
        when:
        QueryBuilder builder = QueryBuilder.keywordQuery('US3')

        then:
        builder.toString() == '((Name contains "US3") OR (FormattedId = "US3"))'
    }

    def "Integer keyword"() {
        when:
        QueryBuilder builder = QueryBuilder.keywordQuery('5')

        then:
        builder.toString() == '((Name contains "5") OR (ObjectId = "5"))'
    }

}
