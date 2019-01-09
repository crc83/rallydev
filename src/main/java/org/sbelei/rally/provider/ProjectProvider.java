package org.sbelei.rally.provider;

import org.sbelei.rally.domain.Project;
import org.sbelei.rally.domain.constants.Type;

import com.rallydev.rest.RallyRestApi;

public class ProjectProvider extends EntityProvider<Project>{

	public ProjectProvider(RallyRestApi restApi, String workspaceId) {
		super(restApi, workspaceId, null);
	}

	@Override
	String getType() {
		return Type.PROJECT;
	}

	@Override
	public Project newEntity() {
		// TODO Auto-generated method stub
		return new Project();
	}

}
