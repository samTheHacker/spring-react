package com.samdabbous.springreact.data.repositories;

import com.samdabbous.springreact.data.entities.ServicesEntity;
import org.springframework.data.repository.CrudRepository;

public interface ServicesRepository extends CrudRepository<ServicesEntity, Long> {
}
