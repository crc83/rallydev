package org.sbelei.rally.helpers;

import java.util.ArrayList;
import java.util.List;

import com.rallydev.rest.util.QueryFilter;

public class QueryFilterBuilder {
	
	private List<QueryFilter> filters;
	
	public QueryFilterBuilder() {
		filters = new ArrayList<QueryFilter>();
	}
	
	public void add(QueryFilter filter){
		filters.add(filter);
	}
	
	public QueryFilter buildQuery(){
		QueryFilter result = null;
		//let's "and" all queries
		for (QueryFilter filter : filters) {
			if (result == null){
				result = filter;
			} else {
				result = result.and(filter);
			}
		}
		return result;
	}

}
