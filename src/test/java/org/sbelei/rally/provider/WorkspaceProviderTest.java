package org.sbelei.rally.provider;

import com.rallydev.rest.RallyRestApi;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.sbelei.rally.TestHelper;
import org.sbelei.rally.domain.Workspace;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WorkspaceProviderTest {

	private static final String STUB_WORKSPACE_ID = "workspaceId";

	WorkspaceProvider workspaceProvider;
    
    @Before
    public void setUp() throws IOException {
    	RallyRestApi restApi = TestHelper.getRestApiWithResponse("/workspace-response.json");
        workspaceProvider = new WorkspaceProvider(restApi);
	}
    
    @Test
    /**
     * Something wrong with stubbed responses
     * java.lang.ClassCastException: com.google.gson.JsonArray cannot be cast to com.google.gson.JsonObject
     * @throws Exception
     */
	public void testGetIterations() throws Exception {
		List<Workspace> projects = workspaceProvider.getWorkspaces();
		Assertions.assertAll(
				() -> assertEquals(1, projects.size()),
				() ->assertEquals("Meaningfull name of workspace", projects.get(0).name)
		);
	}



    
    
}
