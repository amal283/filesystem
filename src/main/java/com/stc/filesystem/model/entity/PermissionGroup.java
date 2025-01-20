package com.stc.filesystem.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class PermissionGroup {

    public PermissionGroup() {
    }

    public PermissionGroup(Long id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="group_name")
    private String groupName;
}
