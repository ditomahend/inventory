package com.invetory.inventory.controller;

import com.invetory.inventory.InventoryApplication;
import com.invetory.inventory.dto.InventoryDetailDtoResp;
import com.invetory.inventory.dto.InventoryDtoRequest;
import com.invetory.inventory.dto.InventoryDtoResponse;
import com.invetory.inventory.dto.SuccessResponse;
import com.invetory.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@RestController
public class InventoryRest {

    private static final Logger logger = LogManager.getLogger(InventoryRest.class);

    @Autowired
    InventoryService inventoryService;

    @PostMapping("/create-stock")
    @ResponseBody
    public SuccessResponse createStock(@RequestBody InventoryDtoRequest request){
        logger.info("Received create stock request: {}", request);
        SuccessResponse response = inventoryService.createStock(request);
        logger.info("Response for create stock: {}", response);
        return response;
    }

    @PostMapping("/update-stock")
    @ResponseBody
    public SuccessResponse updateStock(@RequestBody InventoryDtoRequest request){
        logger.info("Received update stock request: {}", request);
        SuccessResponse response = inventoryService.updateStock(request);
        logger.info("Response for update stock: {}", response);
        return response;
    }

    @PostMapping("/delete-stock")
    @ResponseBody
    public SuccessResponse deleteStock(Long id){
        logger.info("Received delete stock request for ID: {}", id);
        SuccessResponse response = inventoryService.deleteStock(id);
        logger.info("Response for delete stock: {}", response);
        return response;
    }

    @PostMapping("/list-stock")
    @ResponseBody
    public List<InventoryDtoResponse> listStock(){
        logger.info("Received list stock request");
        List<InventoryDtoResponse> response = inventoryService.listStock();
        logger.info("Response for list stock: {}", response);
        return response;
    }

    @PostMapping("/detail-stock")
    @ResponseBody
    public InventoryDetailDtoResp detailStock(Long id){
        logger.info("Received detail stock request for ID: {}", id);
        InventoryDetailDtoResp response = inventoryService.detailStock(id);
        logger.info("Response for detail stock: {}", response);
        return response;
    }
}
