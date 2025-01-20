package com.stc.filesystem.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class File {

    public File() {
    }

    public File(Long id, byte[] binaryData, Item item) {
        this.id = id;
        this.binaryData = binaryData;
        this.item = item;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "binary_data")
    @Lob
    private byte[] binaryData;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
