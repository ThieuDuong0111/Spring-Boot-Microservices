package com.thieuduong.order_service.dto;

import java.util.List;

public class OrderRequest {
	private List<OrderLineItemsDTO> orderLineItemsDtoList;

	public List<OrderLineItemsDTO> getOrderLineItemsDtoList() {
		return orderLineItemsDtoList;
	}

	public void setOrderLineItemsDtoList(
		List<OrderLineItemsDTO> orderLineItemsDtoList) {
		this.orderLineItemsDtoList = orderLineItemsDtoList;
	}

}
