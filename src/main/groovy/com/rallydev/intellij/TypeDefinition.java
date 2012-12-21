package com.rallydev.intellij;

import com.intellij.util.xmlb.annotations.Tag;

import java.util.List;

@Tag("TypeDefinition")
public class TypeDefinition {

    private List<String> scheduleStates;

    public List<String> getScheduleStates() {
        return scheduleStates;
    }

    public void setScheduleStates(List<String> scheduleStates) {
        this.scheduleStates = scheduleStates;
    }

}
