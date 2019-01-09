package org.sbelei.rally.helpers;

import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.util.QueryFilter;

public class QueryRequestDecorator {
	
	private QueryRequest request;
	
	public QueryRequestDecorator(String type) {
		this.request = new QueryRequest(type);
	}
	
	public QueryRequestDecorator(QueryRequest request){
		this.request = request;
	}

	public void andFilter(QueryFilter filter){
		if (request.getQueryFilter() == null ) {
			request.setQueryFilter(filter);
		} else {
			if (filter != null) {
				request.setQueryFilter(request.getQueryFilter().and(filter));
			}
		}
	}
	
	public QueryRequest getRequest() {
		return request;
	}

	public void setWorkspace(String workspaceId) {
		if (workspaceId != null){
			request.setWorkspace(workspaceId);
		}		
	}
}
