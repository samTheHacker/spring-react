package com.samdabbous.springreact.data.repositories;

import com.samdabbous.springreact.data.entities.ProductsEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository<ProductsEntity, Long> {
}
