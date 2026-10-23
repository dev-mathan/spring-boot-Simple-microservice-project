
package com.example.product_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //save the Product
    public Product addProduct(Product p) {
        return productRepository.save(p);
    }
    
    //get all product
    
    public List<Product> getallProduct(){
    	return productRepository.findAll();
    }
    
    //get product by id
    
    public Product getProductById(Long id) {
    Product p = productRepository.findById(id)
    		.orElseThrow(()->new RuntimeException("Id Not Found : " + id));
   
    return p;
    }
    
//    public String deleteProductById(Long id) {
//    	 Product p = productRepository.findById(id)
//    	    		.orElseThrow(()->new RuntimeException("Product Not Found :" + id));
//    	 
//    	 productRepository.delete(p);
//    	   
//    	    return "Product Deleted";
//    }
}
