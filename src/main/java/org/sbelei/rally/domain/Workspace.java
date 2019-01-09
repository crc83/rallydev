package org.sbelei.rally.domain;

import java.util.ArrayList;
import java.util.List;

public class Workspace extends BasicEntity{
    
    public String description;
    public String notes;
    public List<Project> projects;
    
    public Workspace() {
        projects = new ArrayList<Project>();
    }

    @Override
    public String toString() {
        return name;
    }

}
