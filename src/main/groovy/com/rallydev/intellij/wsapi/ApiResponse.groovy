package com.rallydev.intellij.wsapi

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonParser

class ApiResponse {

    JsonElement rawResponse

    ApiResponse(String jsonString) {
        rawResponse = new JsonParser().parse(jsonString)
    }

    JsonArray getResults() {
        return rawResponse?.QueryResult?.Results
    }

}
