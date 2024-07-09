package com.thieuduong.inventory_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.thieuduong.inventory_service.dto.InventoryResponse;
import com.thieuduong.inventory_service.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	// http://localhost:8082/api/inventory?skuCode=111&skuCode=222
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> isInStock(
		@RequestParam List<String> skuCode) {
		System.out.println("---Received inventory check request for skuCode: " +
			skuCode + "---");
		return inventoryService.isInStock(skuCode);
	}
}
