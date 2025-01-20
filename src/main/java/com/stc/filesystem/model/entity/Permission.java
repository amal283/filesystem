package com.stc.filesystem.model.entity;

import com.stc.filesystem.enums.PermissionLevel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class Permission {

    public Permission() {
    }

    public Permission(Long id, String userEmail, PermissionLevel permissionLevel, PermissionGroup permissionGroup) {
        this.id = id;
        this.userEmail = userEmail;
        this.permissionLevel = permissionLevel;
        this.permissionGroup = permissionGroup;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="user_email")
    private String userEmail;

    @Enumerated(EnumType.STRING)
    @Column(name=" permission_level")
    private PermissionLevel permissionLevel;

    @ManyToOne
    @JoinColumn(name = " group_id")
    private PermissionGroup permissionGroup;
}
