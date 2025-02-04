package com.thieuduong.order_service.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.thieuduong.order_service.dto.InventoryResponse;
import com.thieuduong.order_service.dto.OrderLineItemsDTO;
import com.thieuduong.order_service.dto.OrderRequest;
import com.thieuduong.order_service.event.OrderPlacedEvent;
import com.thieuduong.order_service.model.Order;
import com.thieuduong.order_service.model.OrderLineItems;
import com.thieuduong.order_service.repository.OrderRepository;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private WebClient.Builder webClientBuilder;

	private ObservationRegistry observationRegistry;

	private ApplicationEventPublisher applicationEventPublisher;

	public String placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());

		List<OrderLineItems> orderLineItems = orderRequest
			.getOrderLineItemsDtoList()
			.stream()
			.map(this::mapToEntity)
			.toList();

		order.setOrderLineItemsList(orderLineItems);

		List<String> skuCodes = order.getOrderLineItemsList().stream()
			.map(OrderLineItems::getSkuCode)
			.toList();

		InventoryResponse[] inventoryResponseArray = webClientBuilder
			.build().get()
			.uri("http://localhost:8082/api/inventory",
				uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes)
					.build())
			.retrieve()
			.bodyToMono(InventoryResponse[].class)
			.block();
		boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
			.allMatch(InventoryResponse::isInStock);

		if (allProductsInStock) {
//			orderRepository.save(order);
			// publish Order Placed Event
//			applicationEventPublisher.publishEvent(
//				new OrderPlacedEvent(this, order.getOrderNumber()));
			return "Order Placed";
		} else {
			throw new IllegalArgumentException(
				"Product is not in stock, please try again later");
		}
		// Call Inventory Service, and place order if product is in
		// stock
//		Observation inventoryServiceObservation = Observation.createNotStarted(
//			"inventory-service-lookup",
//			this.observationRegistry);
//		inventoryServiceObservation.lowCardinalityKeyValue("call",
//			"inventory-service");
//		return inventoryServiceObservation.observe(() -> {
//			InventoryResponse[] inventoryResponseArray = webClientBuilder
//				.build().get()
//				.uri("http://inventory-service/api/inventory",
//					uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes)
//						.build())
//				.retrieve()
//				.bodyToMono(InventoryResponse[].class)
//				.block();
//
//			boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
//				.allMatch(InventoryResponse::isInStock);
//
//			if (allProductsInStock) {
//				orderRepository.save(order);
//				// publish Order Placed Event
//				applicationEventPublisher.publishEvent(
//					new OrderPlacedEvent(this, order.getOrderNumber()));
//				return "Order Placed";
//			} else {
//				throw new IllegalArgumentException(
//					"Product is not in stock, please try again later");
//			}
//		});
	}

	private OrderLineItems mapToEntity(OrderLineItemsDTO orderLineItemsDTO) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDTO.getPrice());
		orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());
		return orderLineItems;
	}
}
