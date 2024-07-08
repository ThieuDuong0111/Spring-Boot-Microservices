package com.thieuduong.product_service.service;

import com.thieuduong.product_service.dto.ProductRequest;
import com.thieuduong.product_service.dto.ProductResponse;
import com.thieuduong.product_service.model.Product;
import com.thieuduong.product_service.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	public void createProduct(ProductRequest productRequest) {
		Product product = modelMapper.map(productRequest, Product.class);
		productRepository.save(product);
	}

	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();

		return products.stream().map(this::mapToProductResponse).toList();
	}

	private ProductResponse mapToProductResponse(Product product) {
		return modelMapper.map(product, ProductResponse.class);
	}
}
