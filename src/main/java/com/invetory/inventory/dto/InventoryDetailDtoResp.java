package com.invetory.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDetailDtoResp {
    private Long id;

    private Date createdAt;

    private String createdBy;

    private Date updatedAt;

    private String updatedBy;

    private String itemName;

    private Double quantity;

    private String seriItem;

    private String additionalInfo;

    private String image;

    private Boolean isDeleted;
}
