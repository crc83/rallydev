package com.rallydev.intellij.wsapi

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonParser

class ApiResponse {
    JsonElement json

    ApiResponse(String jsonString) {
        json = new JsonParser().parse(jsonString)
    }

    JsonArray getResults() {
        return json?.QueryResult?.Results
    }

}
