package com.thieuduong.order_service.event;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class OrderPlacedEvent extends ApplicationEvent {
	private String orderNumber;

	public OrderPlacedEvent(Object source, String orderNumber) {
		super(source);
		this.orderNumber = orderNumber;
	}

	public OrderPlacedEvent(String orderNumber) {
		super(orderNumber);
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
}
