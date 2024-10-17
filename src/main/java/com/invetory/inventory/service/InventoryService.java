package com.invetory.inventory.service;

import com.invetory.inventory.dto.*;
import com.invetory.inventory.model.Inventory;
import com.invetory.inventory.repository.InventoryRepository;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;
    public SuccessResponse createStock(InventoryDtoRequest request) {

        try {

            validateImage(request.getImage());

            AdditionalInfo additionalInfo = AdditionalInfo.builder()
                    .example("test")
                    .build();

            Jsonb jsonb = JsonbBuilder.create();
            String additionalDataJson = jsonb.toJson(additionalInfo);

            Inventory inventory = new Inventory();
            inventory.setCreatedAt(new Date());
            inventory.setCreatedBy(request.getCreatedBy());
            inventory.setUpdatedAt(null);
            inventory.setUpdatedBy(null);
            inventory.setItemName(request.getItemName());
            inventory.setQuantity(request.getQuantity());
            inventory.setSeriItem(request.getSeriItem());
            inventory.setAdditionalInfo(additionalDataJson);
            inventory.setImage(request.getImage());

            inventoryRepository.save(inventory);

            return SuccessResponse.builder()
                    .isSuccess(true)
                    .build();

        } catch (Exception e) {
            log.error("Error", e);
            throw e;
        }
    }

    public SuccessResponse updateStock(InventoryDtoRequest request) {

        try {

            var inventoryExist = inventoryRepository.findById(request.getId()).orElseThrow(() -> new JsonbException("Id Not Found"));

            inventoryExist.setUpdatedBy(request.getUpdatedBy());
            inventoryExist.setUpdatedAt(new Date());
            inventoryExist.setItemName(request.getItemName());
            inventoryExist.setQuantity(request.getQuantity());
            inventoryExist.setSeriItem(request.getSeriItem());

            if (request.getImage() != null) {
                validateImage(request.getImage());
                inventoryExist.setImage(request.getImage());
            }

            inventoryRepository.save(inventoryExist);

            return SuccessResponse.builder()
                    .isSuccess(true)
                    .build();

        }catch (Exception e) {
            log.error("Error + ");
            throw e;
        }
    }

    public SuccessResponse deleteStock(Long id) {

        var dataExist = inventoryRepository.findById(id).orElseThrow(() -> new JsonbException("Id Not Found"));

        dataExist.setIsDeleted(true);
        inventoryRepository.save(dataExist);

        return SuccessResponse.builder()
                .isSuccess(true)
                .build();
    }

    public List<InventoryDtoResponse> listStock() {

        List<Inventory> stocks = inventoryRepository.findByIsDeleted(false);

        return stocks.stream().map(stock -> {
            return InventoryDtoResponse.builder()
                    .id(stock.getId())
                    .itemName(stock.getItemName())
                    .quantity(stock.getQuantity())
                    .seriItem(stock.getSeriItem())
                    .image(stock.getImage())
                    .build();
        }).collect(Collectors.toList());
    }

    public InventoryDetailDtoResp detailStock(Long id) {

        var exist = inventoryRepository.findById(id).orElseThrow(() -> new JsonbException("Id Not Found"));

        return InventoryDetailDtoResp.builder()
                .id(exist.getId())
                .createdAt(exist.getCreatedAt())
                .createdBy(exist.getCreatedBy())
                .updatedAt(exist.getUpdatedAt())
                .updatedBy(exist.getUpdatedBy())
                .itemName(exist.getItemName())
                .quantity(exist.getQuantity())
                .seriItem(exist.getSeriItem())
                .additionalInfo(exist.getAdditionalInfo())
                .image(exist.getImage())
                .isDeleted(exist.getIsDeleted())
                .build();

    }

    private void validateImage(String image) {
        if (image == null || (!image.endsWith(".jpg") && !image.endsWith(".png"))) {
            throw new IllegalArgumentException("type not supproted!");
        }
    }

}
