package org.sbelei.rally.provider;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.sbelei.rally.domain.BasicEntity;

import com.rallydev.rest.RallyRestApi;
import org.sbelei.rally.domain.Iteration;

/**
 * Test IterationProvider to work with empty responses
 * @author sbelei
 */
public class IterationProviderTestEmptyResponces {

    private static final String STUB_PROJECT_ID = "projectId";
	private static final String STUB_WORKSPACE_ID = "workspaceId";
	
	IterationProvider iterationProvider;
    
    @Before
    public void setUp() {
        RallyRestApi restApi = mock(RallyRestApi.class);
        iterationProvider = new IterationProvider(restApi, STUB_WORKSPACE_ID, STUB_PROJECT_ID);
    }
    
    @Test
    public void testGetCurrentIterationForEmptyResponce() throws Exception {
        BasicEntity oneIteration = iterationProvider.fetchCurrentIteration();
        assertNull(oneIteration);
    }
    
    @Test
    public void testGetIteration() throws Exception {
        List<Iteration> iterations = iterationProvider.getIterations();
        assertNotNull(iterations);
        assertEquals(0, iterations.size());
    }
}
