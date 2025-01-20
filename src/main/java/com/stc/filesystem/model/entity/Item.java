package com.stc.filesystem.model.entity;

import com.stc.filesystem.enums.ItemType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class Item {

    public Item() {
    }

    public Item(Long id, ItemType type, String name, PermissionGroup permissionGroup) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.permissionGroup = permissionGroup;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private ItemType type;

    @Column(name="name", unique=true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "permission_group_id")
    private PermissionGroup permissionGroup;
}
