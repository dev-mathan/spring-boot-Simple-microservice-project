package com.example.product_service.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product_service.entity.Product;
import com.example.product_service.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductContoller {
	  
	@Autowired
	private ProductService productService;
	
 	 @PostMapping("")
	 public Product addProduct(@RequestBody Product p) {
		 return productService.addProduct(p);
	 }
 	 
 	 @GetMapping
 	 public ResponseEntity<List<Product>> getallProduct(){
 		 return new ResponseEntity<List<Product>>(productService.getallProduct(),HttpStatus.OK);
 	 }
 	 
 	 @GetMapping("/{id}")
 	 public ResponseEntity<Product> getProductById(@PathVariable Long id) {
 		 return new ResponseEntity<Product>(productService.getProductById(id),HttpStatus.OK);
 	 }
 	 
// 	 @DeleteMapping("/{id}")
// 	 public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
// 		 return new ResponseEntity<String>(productService.deleteProductById(id),HttpStatus.OK);
// 	 }

}
