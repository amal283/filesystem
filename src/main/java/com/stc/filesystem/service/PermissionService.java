package com.stc.filesystem.service;

import com.stc.filesystem.enums.PermissionLevel;
import com.stc.filesystem.model.entity.Permission;
import com.stc.filesystem.model.entity.PermissionGroup;
import com.stc.filesystem.repository.PermissionGroupRepository;
import com.stc.filesystem.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService implements PermissionServiceInterface{

    private final PermissionGroupRepository permissionGroupRepository;
    private final PermissionRepository permissionRepository;
    private static final String ADMIN = "admin";
    private static final String VIEW_ACCESS_USER_EMAIL = "view_access_user";
    private static final String EDIT_ACCESS_USER_EMAIL = "edit_access_user";

    public PermissionService(PermissionGroupRepository permissionGroupRepository, PermissionRepository permissionRepository) {
        this.permissionGroupRepository = permissionGroupRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public PermissionGroup createAdminPermissionGroup() {
        try {
            PermissionGroup permissionGroup = createPermissionGroup(ADMIN);
            createViewAccessPermission(VIEW_ACCESS_USER_EMAIL, permissionGroup);
            createEditAccessPermission(EDIT_ACCESS_USER_EMAIL, permissionGroup);
            return permissionGroup;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<Permission> getPermission(String userEmail, String permissionLevel, Long groupId) {
        return permissionRepository.findByUserEmailAndPermissionLevelAndGroupId(userEmail, permissionLevel, groupId);
    }

    private PermissionGroup createPermissionGroup(String name) {
        PermissionGroup permissionGroup = PermissionGroup.builder()
                .groupName(name)
                .build();
        return permissionGroupRepository.save(permissionGroup);
    }

    private void createViewAccessPermission(String userEmail, PermissionGroup permissionGroup) {
        Permission permission = Permission.builder()
                .userEmail(userEmail)
                .permissionLevel(PermissionLevel.VIEW)
                .permissionGroup(permissionGroup)
                .build();
        permissionRepository.save(permission);
    }

    private void createEditAccessPermission(String userEmail, PermissionGroup permissionGroup) {
        Permission permission = Permission.builder()
                .userEmail(userEmail)
                .permissionLevel(PermissionLevel.EDIT)
                .permissionGroup(permissionGroup)
                .build();
        permissionRepository.save(permission);
    }
}
