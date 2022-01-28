package com.bhaggan.cqrs.CqrsService.Command.Api.Events;

import com.bhaggan.cqrs.CqrsService.Command.Api.Data.Product;
import com.bhaggan.cqrs.CqrsService.Command.Api.Repository.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductsEventsHandler {

    private final ProductRepository productRepository;

    public ProductsEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        Product product = new Product();
        BeanUtils.copyProperties(productCreatedEvent,product);
        productRepository.save(product);
    }
}
