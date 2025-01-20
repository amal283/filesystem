package com.stc.filesystem.repository;

import com.stc.filesystem.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query(nativeQuery = true, value = "SELECT p.* FROM permission p " +
            "JOIN permission_group pg ON p.group_id = pg.id " +
            "WHERE p.user_email = :userEmail " +
            "AND p.permission_level = :permissionLevel " +  // permission_level is a string column
            "AND pg.id = :groupId")
    List<Permission> findByUserEmailAndPermissionLevelAndGroupId(@Param("userEmail") String userEmail,
                                                                 @Param("permissionLevel") String permissionLevel,
                                                                 @Param("groupId")Long groupId);

    List<Permission> findByUserEmail(String userEmail);
}
