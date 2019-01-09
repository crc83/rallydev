package org.sbelei.rally.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Story extends BasicEntity{
	
	public String formattedId;
	public String scheduleState;
	
	@Override
	public String toString() {
		return name;
	}

	/**
	 * List of schedule states and their shortcuts 
	 */
	private static HashMap<String, String> scheduleStates = new LinkedHashMap<String, String>() {

		{
			put("Defined", "D"); // ???
			put("In-Progress", "P");
			put("Completed", "C");
			put("Accepted", "A");
			put("Tested", "T");
		}
	};

}
