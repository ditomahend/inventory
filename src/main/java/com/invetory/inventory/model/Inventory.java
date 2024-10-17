package com.invetory.inventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Table(name = "inventory")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_seq_generator")
    @SequenceGenerator(name = "inventory_seq_generator", sequenceName = "inventory_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "seri_item")
    private String seriItem;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "image")
    private String image;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
