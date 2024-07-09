package com.thieuduong.inventory_service.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thieuduong.inventory_service.dto.InventoryResponse;
import com.thieuduong.inventory_service.model.Inventory;
import com.thieuduong.inventory_service.repository.InventoryRepository;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		return inventoryRepository.findBySkuCodeIn(skuCode).stream()
			.map(this::mapToInventoryResponse)
			.toList();
	}

	private InventoryResponse mapToInventoryResponse(Inventory inventory) {
		InventoryResponse response = modelMapper.map(inventory,
			InventoryResponse.class);
		response.setInStock(inventory.getQuantity() > 0);
		return response;
	}
}
