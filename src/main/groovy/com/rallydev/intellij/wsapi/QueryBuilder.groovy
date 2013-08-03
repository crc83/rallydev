package com.rallydev.intellij.wsapi

import com.rallydev.intellij.config.RallyConfig

import static com.rallydev.intellij.wsapi.QueryBuilder.Operator.contains
import static com.rallydev.intellij.wsapi.QueryBuilder.Operator.eq

class QueryBuilder {

    List<Restriction> disjunctions = []
    List<Restriction> conjunctions = []

    QueryBuilder withDisjunction(String attribute, Operator operator, String value) {
        disjunctions << new Restriction(attribute: attribute, operator: operator, value: value)
        return this
    }

    QueryBuilder withConjunction(String attribute, Operator operator, String value) {
        conjunctions << new Restriction(attribute: attribute, operator: operator, value: value)
        return this
    }

    QueryBuilder withKeyword(String keyword) {
        withDisjunction('Name', contains, keyword)
        if (keyword ==~ /[a-zA-Z]+\d+/) {
            withDisjunction('FormattedId', eq, keyword)
        }
        if (keyword.isInteger()) {
            withDisjunction('ObjectId', eq, keyword)
        }
        return this
    }

    QueryBuilder withProjectId(String projectId) {
        withConjunction('ProjectId',eq, projectId)
        //withConjunction('State',Operator.noteq,'Compleated')
//        withConjunction('Owner.Name',eq,RallyConfig.getInstance().userName)
        return this
    }

    boolean hasConditions() {
        return disjunctions || conjunctions
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
        eq, contains, gt, noteq;

        @Override
        String toString() {
            switch (this) {
                case Operator.eq:
                    return '='
                case Operator.noteq:
                    return '!='
                case Operator.contains:
                    return 'contains'
                case Operator.gt:
                    return '>'
            }
        }
    }

}
