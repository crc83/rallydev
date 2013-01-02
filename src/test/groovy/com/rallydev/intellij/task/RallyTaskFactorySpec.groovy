package com.rallydev.intellij.task

import com.intellij.tasks.TaskType
import com.rallydev.intellij.wsapi.ApiResponse
import spock.lang.Specification

import static spock.util.matcher.HamcrestMatchers.closeTo
import static spock.util.matcher.HamcrestSupport.that

class RallyTaskFactorySpec extends Specification {

    def "Tasks from sample response"() {
        given:
        String requirementJson = RallyTaskFactorySpec.classLoader.getResourceAsStream('multiple_requirements.json').text
        ApiResponse response = new ApiResponse(requirementJson)

        and:
        Collection<RallyTask> tasks = RallyTaskFactory.tasksFromResponse(response)

        expect:
        tasks.size() == 3

        and:
        tasks[0].summary == 'US1: lone story'
        tasks[0].type == TaskType.FEATURE
        tasks[1].summary == 'US2: story with a task'
        tasks[1].type == TaskType.FEATURE
        tasks[2].summary == 'US3: I Haz Defect'
        tasks[2].type == TaskType.FEATURE
    }

    def "Single task from sample response"() {
        given:
        String requirementJson = RallyTaskFactorySpec.classLoader.getResourceAsStream('single_requirement.json').text
        ApiResponse response = new ApiResponse(requirementJson)

        and:
        RallyTask task = RallyTaskFactory.singleTaskFromResponse(response)

        expect:
        task.id == '14345'
        task.summary == 'US1: lone story'
        task.description == '<p>Allow potential Rally customers to sign up for a trial that is easy to understand.</p>'
        that task.created.time, closeTo(new GregorianCalendar(2012, 11 - 1, 21, 06, 7, 34).timeInMillis, 1000)
        that task.updated.time, closeTo(new GregorianCalendar(2012, 11 - 1, 21, 8, 0, 0).timeInMillis, 1000)
        task.issueUrl == 'http://localhost:7001/slm/webservice/1.39/hierarchicalrequirement/14345.js'
        task.type == TaskType.FEATURE
    }

}
