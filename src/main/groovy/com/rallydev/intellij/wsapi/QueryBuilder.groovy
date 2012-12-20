package com.rallydev.intellij.wsapi

import static com.rallydev.intellij.wsapi.QueryBuilder.Operator.contains
import static com.rallydev.intellij.wsapi.QueryBuilder.Operator.eq

class QueryBuilder {

    List<Restriction> disjunctions = []
    List<Restriction> conjunctions = []

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

    QueryBuilder withConjunction(String attribute, Operator operator, String value) {
        conjunctions << new Restriction(attribute: attribute, operator: operator, value: value)
        return this
    }

    @Override
    String toString() {
        List<Map> expressions = disjunctions.collect { [operator: 'OR', operand: it] } +
                conjunctions.collect { [operator: 'AND', operand: it] }
        return toStringHelper("${expressions.head().operand}", expressions.tail())
    }

    private String toStringHelper(String currentQuery, List<Map> expressions) {
        if (!expressions) {
            return currentQuery
        } else {
            Map expression = expressions.head()
            return toStringHelper("(${currentQuery} ${expression.operator} ${expression.operand})", expressions.tail())
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
