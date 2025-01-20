package com.stc.filesystem.repository;

import com.stc.filesystem.model.entity.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {
}
