package com.bhaggan.cqrs.CqrsService.Command.Api.Aggregate;

import com.bhaggan.cqrs.CqrsService.Command.Api.Commands.CreateProductCommand;
import com.bhaggan.cqrs.CqrsService.Command.Api.Events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        //Perform validation
        ProductCreatedEvent productCreatedEvent =
                new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand,productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent);
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.quantity = productCreatedEvent.getQuantity();
        this.price = productCreatedEvent.getPrice();
        this.productId = productCreatedEvent.getProductId();
        this.name = productCreatedEvent.getName();
    }

    public ProductAggregate() {
    }
}
