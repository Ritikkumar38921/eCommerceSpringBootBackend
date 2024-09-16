package com.alibou.ecommerce.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<Integer> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
		return ResponseEntity.ok(categoryService.createCategory(categoryRequest));
	}

	@GetMapping("/{category-id}")
	public ResponseEntity<Category> findById(@PathVariable("category-id") Integer id) {
		return ResponseEntity.ok(categoryService.findById(id));
	}

	@GetMapping("/get")
	public ResponseEntity<List<Category>> findAll() {
		return ResponseEntity.ok(categoryService.findAll());
	}
}
