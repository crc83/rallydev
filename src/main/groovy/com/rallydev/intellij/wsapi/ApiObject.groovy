package com.rallydev.intellij.wsapi

enum ApiObject {
    WORKSPACE, HIERARCHICAL_REQUIREMENT, DEFECT

    @Override
    String toString() {
        return super.toString().replaceAll('_', '').toLowerCase()
    }

}
