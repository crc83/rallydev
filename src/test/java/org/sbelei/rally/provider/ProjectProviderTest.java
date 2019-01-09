package org.sbelei.rally.provider;

import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.sbelei.rally.TestHelper;
import org.sbelei.rally.domain.Project;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.sbelei.rally.TestHelper.getResourseAsString;

public class ProjectProviderTest {

	private static final String STUB_WORKSPACE_ID = "workspaceId";

	ProjectProvider projectProvider;
    
    @Before
    public void setUp() throws IOException {
    	RallyRestApi restApi = TestHelper.getRestApiWithResponse("/project-response.json");
        projectProvider = new ProjectProvider(restApi, STUB_WORKSPACE_ID);
	}
    
    @Test
    /**
     * Something wrong with stubbed responses
     * java.lang.ClassCastException: com.google.gson.JsonArray cannot be cast to com.google.gson.JsonObject
     * @throws Exception
     */
	public void testGetIterations() throws Exception {
		List<Project> projects = projectProvider.getProjects();
		Assertions.assertAll(
				() -> assertEquals(2, projects.size()),
				() ->assertEquals("AB", projects.get(0).name),
				() ->assertEquals("Some child project", projects.get(1).name)
		);
	}



    
    
}
