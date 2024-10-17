package com.invetory.inventory.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class InventoryDtoRequest {
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

}
