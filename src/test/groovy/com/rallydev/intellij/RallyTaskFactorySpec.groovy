package com.rallydev.intellij

import com.rallydev.intellij.wsapi.ApiResponse
import spock.lang.Specification

import java.text.SimpleDateFormat

import static spock.util.matcher.HamcrestMatchers.closeTo
import static spock.util.matcher.HamcrestSupport.that

class RallyTaskFactorySpec extends Specification {

    def "Datestring is correct"() {
        given:
        SimpleDateFormat dateFormat = new SimpleDateFormat(RallyTaskFactory.RALLY_DATE_FORMAT)

        when:
        Calendar calendar = new GregorianCalendar()
        calendar.setTime(dateFormat.parse('2012-11-21T06:07:34.127Z'))

        then:
        calendar.get(Calendar.YEAR) == 2012
        calendar.get(Calendar.MONTH) + 1 == 11 //month is 0 based
        calendar.get(Calendar.DAY_OF_MONTH) == 21
        calendar.get(Calendar.HOUR) == 6
        calendar.get(Calendar.MINUTE) == 7
        calendar.get(Calendar.SECOND) == 34

    }

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
        tasks[1].summary == 'US2: story with a task'
        tasks[2].summary == 'US3: I Haz Defect'
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

    }

}
