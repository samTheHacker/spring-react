package com.samdabbous.springreact.services;

import com.samdabbous.springreact.data.entities.ServicesEntity;
import com.samdabbous.springreact.data.repositories.ServicesRepository;
import com.samdabbous.springreact.web.errors.NotFoundException;
import com.samdabbous.springreact.web.models.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServicesRepository servicesRepository;

    public ServiceService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public List<Service> getAllService(){
        Iterable<ServicesEntity> entities = this.servicesRepository.findAll();

        List<Service> services = new ArrayList<>();
        entities.forEach(entity->{
            services.add(this.translateDbToWeb(entity));
        }); //lambda expression

        return services;
    }

    public Service getService(long id){
        Optional<ServicesEntity> optional = this.servicesRepository.findById(id);
        if(optional.isEmpty()){
            throw new NotFoundException("service not found with id");
        }
        return this.translateDbToWeb(optional.get());
    }

    public void deleteService(long id){
        this.servicesRepository.deleteById(id);
    }

    private ServicesEntity translateWebToDb(Service service){
        ServicesEntity entity = new ServicesEntity();
        entity.setId(service.getServiceId());
        entity.setName(service.getServiceName());
        entity.setPrice(service.getPrice());
        return entity;
    }

    private Service translateDbToWeb(ServicesEntity entity){
        return new Service(entity.getId(), entity.getName(), entity.getPrice());
    }

}
