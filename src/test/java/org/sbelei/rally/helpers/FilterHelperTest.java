package org.sbelei.rally.helpers;

import static org.junit.Assert.*;
import static org.sbelei.rally.TestHelper.date;




import org.junit.Test;
import org.sbelei.rally.helpers.FilterHelper;

import com.rallydev.rest.util.QueryFilter;


public class FilterHelperTest {
	
	private static final String ANY_ID = "42";


	@Test
	public void testByProjectIdNull() throws Exception {
		assertNull(FilterHelper.byProjectId(null));
	} 
	
	@Test
	public void testByProjectId() throws Exception {
		assertNotNull(FilterHelper.byProjectId(ANY_ID));
	} 
	
	@Test
	public void testIncludeByOqner() throws Exception {
		assertEquals(new QueryFilter("Owner.Name", "=", "the owner").toString(), FilterHelper.includeByOwner("the owner").toString());
	}
	

	@Test
	public void testQueryDate() throws Exception {
		assertEquals("2013-07-14T00:00:00.000Z", FilterHelper.queryDate(date("14/07/2013")));		
	}
}
