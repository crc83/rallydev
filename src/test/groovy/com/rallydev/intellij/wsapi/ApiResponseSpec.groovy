package com.rallydev.intellij.wsapi

import spock.lang.Specification

import java.text.SimpleDateFormat

class ApiResponseSpec extends Specification {

    String workSpaceJson = """
{
   "QueryResult":{
      "_rallyAPIMajor":"1",
      "_rallyAPIMinor":"40",
      "Errors":[

      ],
      "Warnings":[

      ],
      "TotalResultCount":1,
      "StartIndex":1,
      "PageSize":20,
      "Results":[
         {
            "_rallyAPIMajor":"1",
            "_rallyAPIMinor":"40",
            "_ref":"https://rally1.rallydev.com/slm/webservice/1.40/workspace/41529001.js",
            "_refObjectName":"Rally",
            "_type":"Workspace"
         }
      ]
   }
}
"""

    def "Date string is correct"() {
        given:
        SimpleDateFormat dateFormat = new SimpleDateFormat(ApiResponse.RALLY_DATE_FORMAT)

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


    def "Response correctly parses workspace from sample results"() {
        given:
        ApiResponse response = new ApiResponse(workSpaceJson)

        expect:
        response.results

        and:
        response.results.get(0)._ref.getAsString() == 'https://rally1.rallydev.com/slm/webservice/1.40/workspace/41529001.js'
        response.results.get(0)._refObjectName.getAsString() == 'Rally'
        response.results.get(0)._type.getAsString() == 'Workspace'
    }

}
