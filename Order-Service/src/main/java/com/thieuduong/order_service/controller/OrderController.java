package com.thieuduong.order_service.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.thieuduong.order_service.dto.OrderRequest;
import com.thieuduong.order_service.service.OrderService;

public class OrderController {
	private OrderService orderService;

//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
//	@TimeLimiter(name = "inventory")
//	@Retry(name = "inventory")
//	public CompletableFuture<String> placeOrder(
//		@RequestBody OrderRequest orderRequest) {
//		return CompletableFuture
//			.supplyAsync(() -> orderService.placeOrder(orderRequest));
//	}
//
//	public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest,
//		RuntimeException runtimeException) {
//		return CompletableFuture.supplyAsync(
//			() -> "Oops! Something went wrong, please order after some time!");
//	}
}
