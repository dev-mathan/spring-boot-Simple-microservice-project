
package com.example.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.order_service.dto.OrderResponseDTO;
import com.example.order_service.dto.ProductDto;
import com.example.order_service.entity.Order;
import com.example.order_service.repository.OrderRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @PostMapping("/placeorder")
    public Mono<ResponseEntity<OrderResponseDTO>> placeOrder(
            @RequestBody Order order) {

        return webClientBuilder.build().get().uri("http://localhost:8081/products/" + order.getProductId()).retrieve()
                .bodyToMono(ProductDto.class).map(productDto -> {

                  
                    // Prepare response
                    OrderResponseDTO responseDTO = new OrderResponseDTO();

                    
                    responseDTO.setProductId(order.getProductId());
                    responseDTO.setQuantity(order.getQuantity());
                    

                    //set Product details
                    responseDTO.setProductName(productDto.getName());
                    responseDTO.setProductPrice(productDto.getPrice());
                    responseDTO.setTotalPrice(order.getQuantity() * productDto.getPrice());
                    
                    // Save the order
                   
                    orderRepository.save(order);
                    responseDTO.setOrderId(order.getId());
                    return ResponseEntity.ok(responseDTO);
                });
    }
    
    //Get All Orders
    @GetMapping
    public List<Order> getAllOrder(){
    	return orderRepository.findAll();
    }
    
    
}
