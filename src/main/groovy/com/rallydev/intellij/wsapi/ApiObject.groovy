package com.rallydev.intellij.wsapi

enum ApiObject {
    WORKSPACE, HIERARCHICAL_REQUIREMENT

    @Override
    String toString() {
        return super.toString().replaceAll('_', '').toLowerCase()
    }

}
