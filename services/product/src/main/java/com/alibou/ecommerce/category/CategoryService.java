package com.alibou.ecommerce.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Integer createCategory(CategoryRequest categoryRequest) {
		Category category = new Category();
		category.setName(categoryRequest.name());
		category.setDescription(categoryRequest.description());
		Category c = categoryRepository.save(category);
		return c.getId();
	}
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	public Category findById(Integer id) {
		return categoryRepository.findById(id);
	}

}
