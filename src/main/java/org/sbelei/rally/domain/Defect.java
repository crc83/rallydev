package org.sbelei.rally.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Defect extends BasicEntity{
	
	public String formattedId;
	public String severity;
	public String priority;
	public String state;
	public String taskStatus;
	
	@Override
	public String toString() {
		return state + " [" +severities.get(severity)+"/"+ priorities.get(priority) + "] " + formattedId + ":" 
				+ name +"\n"+ ref;
	}
	
	/**
	 * List of priorities and their shortcuts 
	 */
	private static HashMap<String, String> priorities = new LinkedHashMap<String, String>() {

		private static final long serialVersionUID = 3137338196796043542L;

		{
			put("Resolve Immediately", "P1");
			put("High Attention", "P2");
			put("Normal", "P3");
			put("Low", "P4");
		}
	};

	/**
	 * List of severities and their shortcuts 
	 */
	private static HashMap<String, String> severities = new LinkedHashMap<String, String>() {

		private static final long serialVersionUID = 7540332120174651925L;

		{
			put("Crash/Data Loss", "S1");
			put("Essential Failure", "S2");
			put("Non-Essential Failure", "S3");
			put("Annoyance", "S4");
		}
	};

    public String getPriorityShort() {
        return priorities.get(priority);
    }
}
