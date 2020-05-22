package org.openmbee.sdvc.twc.permissions;

import exceptions.TwcPermissionException;
import org.openmbee.sdvc.core.builders.PermissionUpdateResponseBuilder;
import org.openmbee.sdvc.core.builders.PermissionUpdatesResponseBuilder;
import org.openmbee.sdvc.core.delegation.PermissionsDelegate;
import org.openmbee.sdvc.core.objects.PermissionResponse;
import org.openmbee.sdvc.core.objects.PermissionUpdateRequest;
import org.openmbee.sdvc.core.objects.PermissionUpdateResponse;
import org.openmbee.sdvc.core.objects.PermissionUpdatesResponse;
import org.openmbee.sdvc.core.utils.RestUtils;
import org.openmbee.sdvc.data.domains.global.Branch;
import org.openmbee.sdvc.twc.TeamworkCloud;
import org.openmbee.sdvc.twc.config.TwcConfig;
import org.openmbee.sdvc.twc.exceptions.TwcConfigurationException;
import org.openmbee.sdvc.twc.utilities.TwcPermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Set;

public class TwcBranchPermissionsDelegate implements PermissionsDelegate{

	private Branch branch;
	private TeamworkCloud teamworkCloud;
	private String workspaceId;
	private String resourceId;

	@Autowired
	private TwcPermissionUtils twcPermissionUtils;

	
	public TwcBranchPermissionsDelegate(Branch branch, TeamworkCloud teamworkCloud, String workspaceId,
			String resourceId) {
		this.branch = branch;
		this.teamworkCloud = teamworkCloud;
		this.workspaceId = workspaceId;
		this.resourceId = resourceId;
	}

	@Override
	public boolean hasPermission(String user, Set<String> groups, String privilege) {
		//TODO:: update once branch permissions are implemented in TWC
		boolean hasPermission = false;

		if (teamworkCloud.hasTwcRoles(privilege)) {
			hasPermission = twcPermissionUtils.hasPermissionToAccessProject(workspaceId, resourceId, teamworkCloud,
					user, privilege, teamworkCloud.getTwcmmsRolesMap().get(privilege));
		}
		
		return hasPermission;
	}

    @Override
    public void initializePermissions(String creator) {
        //Do nothing, permissions are already initialized in TWC
    }

    @Override
    public void initializePermissions(String creator, boolean inherit) {
        //Do nothing, permissions are already initialized in TWC
    }

    @Override
    public boolean setInherit(boolean isInherit) {
        //Do nothing, permission inheritance will be handled by TWC
        return false;
    }

	@Override
	public void setPublic(boolean isPublic) {
		throw new TwcConfigurationException(HttpStatus.BAD_REQUEST,
				"Cannot Modify Permissions.  Permissions for this branch are controlled by Teamwork Cloud ("
						+ teamworkCloud.getUrl() + ")");
	}

	@Override
	public PermissionUpdateResponse updateUserPermissions(PermissionUpdateRequest req) {
		throw new TwcConfigurationException(HttpStatus.BAD_REQUEST,
				"Cannot Update User Permissions.  Permissions for this branch are controlled by Teamwork Cloud ("
						+ teamworkCloud.getUrl() + ")");
	}

	@Override
	public PermissionUpdateResponse updateGroupPermissions(PermissionUpdateRequest req) {
		throw new TwcConfigurationException(HttpStatus.BAD_REQUEST,
				"Cannot Update Group Permissions.  Permissions for this branch are controlled by Teamwork Cloud ("
						+ teamworkCloud.getUrl() + ")");
	}

	@Override
	public PermissionResponse getUserRoles() {
		throw new TwcConfigurationException(HttpStatus.BAD_REQUEST,
				"Cannot Query User Roles.  Permissions for this branch are controlled by Teamwork Cloud ("
						+ teamworkCloud.getUrl() + ")");
	}

	@Override
	public PermissionResponse getGroupRoles() {
		throw new TwcConfigurationException(HttpStatus.BAD_REQUEST,
				"Cannot Query Group Roles.  Permissions for this branch are controlled by Teamwork Cloud ("
						+ teamworkCloud.getUrl() + ")");
	}

	@Override
	public PermissionUpdatesResponse recalculateInheritedPerms() {
		// Do nothing, permission inheritance will be handled by TWC
		return null;
	}

    public Branch getBranch() {
        return branch;
    }

    public TeamworkCloud getTeamworkCloud() {
        return teamworkCloud;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

	public String getResourceId() {
		return resourceId;
	}
}
