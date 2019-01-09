package org.sbelei.rally.helpers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.sbelei.rally.helpers.QueryRequestDecorator;

import com.rallydev.rest.util.QueryFilter;


public class QueryRequestDecoratorTest {
	
	QueryRequestDecorator request;
	
	@Before
	public void setUp() {
		request = new QueryRequestDecorator("foo");
	}
	
	@Test
	public void testAddFilter() throws Exception {
		// setting of workspace shouldn't add filter
		request.setWorkspace("42");
		assertNull( request.getRequest().getQueryFilter() );
		// adding null filter shouldn't add filter
		request.andFilter(null);
		assertNull( request.getRequest().getQueryFilter() );
		// adding of empty filter should add filter
		request.andFilter(new QueryFilter(null, null, null));
		assertNotNull( request.getRequest().getQueryFilter() );
	}

}
