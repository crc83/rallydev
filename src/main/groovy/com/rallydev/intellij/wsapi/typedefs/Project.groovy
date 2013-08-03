package com.rallydev.intellij.wsapi.typedefs

import com.rallydev.intellij.wsapi.ApiObject

class Project {

    static final ApiObject API_OBJECT = ApiObject.PROJECT
    static final String ELEMENT_NAME = 'Project'

    String name
    String objectId
    String ref

    @Override
    String toString() {
        name
    }

}
