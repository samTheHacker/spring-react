package com.samdabbous.springreact.web.rest;

import com.samdabbous.springreact.services.VendorService;
import com.samdabbous.springreact.web.errors.BadRequestException;
import com.samdabbous.springreact.web.models.Vendor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public List<Vendor> getAll(){
        return this.vendorService.getAllVendor();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendor createVendor(@RequestBody Vendor vendor){
        return this.vendorService.createOrUpdate(vendor);
    }

    @GetMapping("/{id}")
    public Vendor getVendor(@PathVariable("id")long id){
        return this.vendorService.getVendor(id);
    }

    @PutMapping("/{id}")
    public Vendor updateVendor(@PathVariable("id")long id, @RequestBody Vendor vendor){
        if(id != vendor.getVendorId()){
            throw new BadRequestException("ids do not match");
        }

        return this.vendorService.createOrUpdate(vendor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteService(@PathVariable("id")long id){
        this.vendorService.deleteVendor(id);
    }
}
