package com.samdabbous.springreact.services;

import com.samdabbous.springreact.data.entities.VendorsEntity;
import com.samdabbous.springreact.data.repositories.VendorsRepository;
import com.samdabbous.springreact.web.errors.NotFoundException;
import com.samdabbous.springreact.web.models.Vendor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    private final VendorsRepository vendorsRepository;

    public VendorService(VendorsRepository vendorsRepository) {
        this.vendorsRepository = vendorsRepository;
    }

    public List<Vendor> getAllVendor(){
        Iterable<VendorsEntity> entities = this.vendorsRepository.findAll();

        List<Vendor> vendors = new ArrayList<>();
        entities.forEach(entity->{
            vendors.add(this.translateDbToWeb(entity));
        }); //lambda expression

        return vendors;
    }

    public Vendor getVendor(long id){
        Optional<VendorsEntity> optional = this.vendorsRepository.findById(id);
        if(optional.isEmpty()){
            throw new NotFoundException("vendor not found with id");
        }
        return this.translateDbToWeb(optional.get());
    }

    public Vendor createOrUpdate(Vendor vendor){
        VendorsEntity entity = this.translateWebToDb(vendor);
        entity = this.vendorsRepository.save(entity);
        return this.translateDbToWeb(entity);
    }


    public void deleteVendor(long id){
        this.vendorsRepository.deleteById(id);
    }


    private VendorsEntity translateWebToDb(Vendor vendor){
        VendorsEntity entity = new VendorsEntity();
        entity.setId(vendor.getVendorId());
        entity.setName(vendor.getVendorName());
        entity.setContact(vendor.getContact());
        entity.setPhone(vendor.getPhoneNumber());
        entity.setEmail(vendor.getEmail());
        entity.setAddress(vendor.getAddress());
        return entity;
    }

    private Vendor translateDbToWeb(VendorsEntity entity){
        return new Vendor(entity.getId(), entity.getName(), entity.getContact(), entity.getPhone(), entity.getEmail()
        , entity.getAddress());
    }
}
