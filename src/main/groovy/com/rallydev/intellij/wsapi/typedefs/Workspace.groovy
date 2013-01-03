package com.rallydev.intellij.wsapi.typedefs

import com.rallydev.intellij.wsapi.ApiObject

class Workspace {

    static final ApiObject API_OBJECT = ApiObject.WORKSPACE
    static final String ELEMENT_NAME = 'WorkSpace'

    String name
    String objectId

    @Override
    String toString() {
        name
    }

}
