package com.stc.filesystem.service;

import com.stc.filesystem.model.entity.Permission;
import com.stc.filesystem.model.entity.PermissionGroup;

import java.util.List;

public interface PermissionServiceInterface {

    PermissionGroup createAdminPermissionGroup();

    List<Permission> getPermission(String userEmail, String permissionLevel, Long groupId);

}
