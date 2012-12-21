package com.rallydev.intellij.wsapi;

//Written in Java due to http://jira.codehaus.org/browse/GROOVY-5212
public enum ApiObject {
    ARTIFACT, DEFECT, HIERARCHICAL_REQUIREMENT, TYPE_DEFINITION,  WORKSPACE;

    @Override
    public String toString() {
        return super.toString().replaceAll("_", "").toLowerCase();
    }

}
