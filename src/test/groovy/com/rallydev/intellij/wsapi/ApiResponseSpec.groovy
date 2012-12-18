package com.rallydev.intellij.wsapi

import spock.lang.Specification

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
