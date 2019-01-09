package org.sbelei.rally.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.sbelei.rally.domain.constants.DefectState;

import com.rallydev.rest.util.QueryFilter;

public class FilterHelper {
	
	private static final String EQ = "=";
	
	private static SimpleDateFormat FORMAT = new SimpleDateFormat("YYYY-MM-dd'T'00:00:00.000'Z'");
	
	private static QueryFilter getFilter(String field, String operator, String value) {
		if (value == null) {
			return null;
		} else {
			return new QueryFilter(field, operator, value);
		}
		
	}
	
	public static QueryFilter includeByOwner(String owner){
		return getFilter("Owner.Name", EQ, owner);
	}
	
	public static QueryFilter includeByStates(DefectState state, DefectState... states){
		QueryFilter filter = getFilter("State", EQ, state.toString());
		for(DefectState theState : states) {
			filter = filter.or(new QueryFilter("State", EQ, theState.toString()));
		}
		return filter;
	}
	
	public static QueryFilter byIterationId(String iterationId){
		return getFilter("Iteration.ObjectID", EQ, iterationId);
	}
	
	public static QueryFilter byProjectId(String projectId){
		return getFilter("Project.ObjectID", EQ, projectId);
	}
	
	public static String queryDate(Date date){		
		return FORMAT.format(date);		
	}

}
