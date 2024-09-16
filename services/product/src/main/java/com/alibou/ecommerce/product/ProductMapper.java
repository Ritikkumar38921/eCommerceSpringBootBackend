package com.alibou.ecommerce.product;

import com.alibou.ecommerce.category.Category;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

	public Product toProduct(ProductRequest request) {
		Product product = new Product();
		product.setId(request.id());
		product.setName(request.name());
		product.setDescription(request.description());
		product.setAvailableQuantity(request.availableQuantity());
		product.setPrice(request.price());
		Category category = new Category();
		category.setId(request.categoryId());
		product.setCategory(category);
		return product;
	}

	public ProductResponse toProductResponse(Product product) {
		return new ProductResponse(product.getId(), product.getName(), product.getDescription(),
				product.getAvailableQuantity(), product.getPrice(), product.getCategory().getId(),
				product.getCategory().getName(), product.getCategory().getDescription());
	}

	public ProductPurchaseResponse toproductPurchaseResponse(Product product, double quantity) {
		return new ProductPurchaseResponse(product.getId(), product.getName(), product.getDescription(),
				product.getPrice(), quantity);
	}
}
