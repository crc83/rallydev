package com.rallydev.intellij.wsapi

import static com.rallydev.intellij.wsapi.QueryBuilder.Operator.contains
import static com.rallydev.intellij.wsapi.QueryBuilder.Operator.eq

class QueryBuilder {

    List<Restriction> disjunctions = []

    static QueryBuilder keywordQuery(String keyword) {
        QueryBuilder queryBuilder = new QueryBuilder()
                .withDisjunction('Name', contains, keyword)
        if (keyword ==~ /[a-zA-Z]+\d+/) {
            queryBuilder.withDisjunction('FormattedId', eq, keyword)
        }
        if (keyword.isInteger()) {
            queryBuilder.withDisjunction('ObjectId', eq, keyword)
        }

        return queryBuilder
    }

    QueryBuilder withDisjunction(String attribute, Operator operator, String value) {
        disjunctions << new Restriction(attribute: attribute, operator: operator, value: value)
        return this
    }

    @Override
    String toString() {
        return toStringHelper("${disjunctions.head()}", disjunctions.tail())
    }

    private String toStringHelper(String currentString, List disjunctions) {
        if (!disjunctions) {
            return currentString
        } else {
            return toStringHelper("(${currentString} OR ${disjunctions.head()})", disjunctions.tail())
        }
    }

    class Restriction {
        String attribute, value
        Operator operator

        @Override
        String toString() {
            return "(${attribute} ${operator} \"${value}\")"
        }
    }

    enum Operator {
        eq, contains

        @Override
        String toString() {
            switch (this) {
                case Operator.eq:
                    return '='
                case Operator.contains:
                    return 'contains'
            }
        }
    }

}
