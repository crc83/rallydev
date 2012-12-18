package com.rallydev.intellij

import com.google.gson.JsonParser
import com.rallydev.intellij.wsapi.ApiResponse
import spock.lang.Specification
import spock.util.matcher.HamcrestSupport

import static spock.util.matcher.HamcrestMatchers.closeTo
import static spock.util.matcher.HamcrestMatchers.closeTo
import static spock.util.matcher.HamcrestSupport.that


import java.text.SimpleDateFormat

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
        String requirementJson = RallyTaskFactorySpec.classLoader.getResourceAsStream('requirement.json').text
        ApiResponse response = new ApiResponse(requirementJson)

        and:
        Collection<RallyTask> tasks = RallyTaskFactory.fromResponse(response)

        expect:
        tasks.size() == 3

        and:
        tasks[0].summary == 'lone story'
        tasks[1].summary == 'story with a task'
        tasks[2].summary == 'I Haz Defect'
    }

    def "Task from JsonObject"() {
        given:
        RallyTask task = RallyTaskFactory.fromJsonElement(new JsonParser().parse(singleRequirementJson))

        expect:
        task.id == '14345'
        task.summary == 'lone story'
        task.description == '<p>Allow potential Rally customers to sign up for a trial that is easy to understand.</p>'
        that task.created.time, closeTo(new GregorianCalendar(2012, 11-1, 21, 06, 7, 34).timeInMillis, 1000)
        that task.updated.time, closeTo(new GregorianCalendar(2012, 11-1, 21, 8, 0, 0).timeInMillis, 1000)
        task.issueUrl == 'http://localhost:7001/slm/webservice/1.39/hierarchicalrequirement/14345.js'
    }

    String singleRequirementJson = """
{
   "_rallyAPIMajor":"1",
   "_rallyAPIMinor":"39",
   "_ref":"http://localhost:7001/slm/webservice/1.39/hierarchicalrequirement/14345.js",
   "_objectVersion":"1",
   "_refObjectName":"lone story",
   "CreationDate":"2012-11-21T06:07:34.025Z",
   "_CreatedAt":"Nov 20",
   "ObjectID":14345,
   "Subscription":{
      "_rallyAPIMajor":"1",
      "_rallyAPIMinor":"39",
      "_ref":"http://localhost:7001/slm/webservice/1.39/subscription/11852.js",
      "_refObjectName":"UnlimitedSubTest",
      "_type":"Subscription"
   },
   "Workspace":{
      "_rallyAPIMajor":"1",
      "_rallyAPIMinor":"39",
      "_ref":"http://localhost:7001/slm/webservice/1.39/workspace/11864.js",
      "_refObjectName":"Workspace 1",
      "_type":"Workspace"
   },
   "Changesets":[

   ],
   "Description":"<p>Allow potential Rally customers to sign up for a trial that is easy to understand.</p>",
   "Discussion":[

   ],
   "FormattedID":"US1",
   "LastUpdateDate":"2012-11-21T08:00:00.127Z",
   "Name":"lone story",
   "Notes":"",
   "Owner":null,
   "Project":{
      "_rallyAPIMajor":"1",
      "_rallyAPIMinor":"39",
      "_ref":"http://localhost:7001/slm/webservice/1.39/project/11954.js",
      "_refObjectName":"Sample Project",
      "_type":"Project"
   },
   "Ready":false,
   "RevisionHistory":{
      "_rallyAPIMajor":"1",
      "_rallyAPIMinor":"39",
      "_ref":"http://localhost:7001/slm/webservice/1.39/revisionhistory/14346.js",
      "_type":"RevisionHistory"
   },
   "Tags":[

   ],
   "Attachments":[

   ],
   "Package":null,
   "AcceptedDate":null,
   "Blocked":false,
   "BlockedReason":null,
   "Blocker":null,
   "Children":[

   ],
   "DefectStatus":"NONE",
   "Defects":[

   ],
   "DirectChildrenCount":0,
   "HasParent":false,
   "InProgressDate":null,
   "Iteration":null,
   "Parent":null,
   "PlanEstimate":null,
   "Predecessors":[

   ],
   "Rank":500000000000,
   "Recycled":false,
   "Release":null,
   "ScheduleState":"Defined",
   "Successors":[

   ],
   "TaskActualTotal":0,
   "TaskEstimateTotal":0,
   "TaskRemainingTotal":0,
   "TaskStatus":"NONE",
   "Tasks":[

   ],
   "TestCaseStatus":"NONE",
   "TestCases":[

   ],
   "PortfolioItem":null,
   "_type":"HierarchicalRequirement"
}
"""

}
