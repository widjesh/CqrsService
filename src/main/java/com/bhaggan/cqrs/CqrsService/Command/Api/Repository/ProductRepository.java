package com.bhaggan.cqrs.CqrsService.Command.Api.Repository;

import com.bhaggan.cqrs.CqrsService.Command.Api.Data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
}
