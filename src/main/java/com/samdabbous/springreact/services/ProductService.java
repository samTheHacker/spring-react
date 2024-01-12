package com.samdabbous.springreact.services;

import com.samdabbous.springreact.data.entities.ProductsEntity;
import com.samdabbous.springreact.data.repositories.ProductsRepository;
import com.samdabbous.springreact.web.errors.NotFoundException;
import com.samdabbous.springreact.web.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductsRepository productsRepository;

    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }


    public List<Product> getAllProduct(){
        Iterable<ProductsEntity> entities = this.productsRepository.findAll();
        List<Product> products = new ArrayList<>();
            entities.forEach(entity->{
                products.add(this.translateDbToWeb(entity));
            }); //lambda expression

        return products;
    }

    public Product getProduct(long id){
        Optional<ProductsEntity> optional = this.productsRepository.findById(id);
        if(optional.isEmpty()){
            throw new NotFoundException("product not found with id: " + id);
        }
        return this.translateDbToWeb(optional.get());
    }

    public Product createOrUpdate(Product product){
        ProductsEntity entity = this.translateWebToDb(product);
        entity = this.productsRepository.save(entity);
        return this.translateDbToWeb(entity);
    }


    public void deleteProduct(long id){
        this.productsRepository.deleteById(id);
    }

    private ProductsEntity translateWebToDb(Product product){
        ProductsEntity entity = new ProductsEntity();
        entity.setId(product.getProductId());
        entity.setName(product.getProductName());
        entity.setPrice(product.getPrice());
        entity.setVendorId(product.getVendorId());
        return entity;
    }

    private Product translateDbToWeb(ProductsEntity entity){
        return new Product(entity.getId(), entity.getName(), entity.getPrice(), entity.getVendorId());
    }

}
