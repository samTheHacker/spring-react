package com.samdabbous.springreact.data.repositories;

import com.samdabbous.springreact.data.entities.VendorsEntity;
import org.springframework.data.repository.CrudRepository;

public interface VendorsRepository extends CrudRepository<VendorsEntity, Long> {
}
