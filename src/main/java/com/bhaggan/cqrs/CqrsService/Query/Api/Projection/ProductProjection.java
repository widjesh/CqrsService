package com.bhaggan.cqrs.CqrsService.Query.Api.Projection;

import com.bhaggan.cqrs.CqrsService.Command.Api.Data.Product;
import com.bhaggan.cqrs.CqrsService.Command.Api.Model.ProductRestModel;
import com.bhaggan.cqrs.CqrsService.Command.Api.Repository.ProductRepository;
import com.bhaggan.cqrs.CqrsService.Query.Api.Queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {
    private final ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery){
        List<Product> products = productRepository.findAll();
        List<ProductRestModel> productRestModels =
                products.stream()
                .map(product -> ProductRestModel
                        .builder()
                        .name(product.getName())
                        .quantity(product.getQuantity())
                        .price(product.getPrice())
                        .build()
                ).collect(Collectors.toList());
        return productRestModels;
    }
}
