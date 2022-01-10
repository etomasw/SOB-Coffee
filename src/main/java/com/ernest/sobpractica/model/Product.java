package com.ernest.sobpractica.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String productType;
    private float deliveryTime;
    private String nutritionalInformation;
    private String edulcorants;
    private BigDecimal price;
    private String image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductSize> sizes;

    @JsonBackReference
    @ManyToMany(mappedBy = "products")
    private List<Store> stores;

    public Product(Long id, String name, String productType, float deliveryTime, String nutritionalInformation,
                   String edulcorants, BigDecimal price, String image, List<ProductSize> sizes, List<Store> stores) {
        this.id = id;
        this.name = name;
        this.productType = productType;
        this.deliveryTime = deliveryTime;
        this.nutritionalInformation = nutritionalInformation;
        this.edulcorants = edulcorants;
        this.price = price;
        this.image = image;
        this.sizes = sizes;
        this.stores = stores;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public float getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(float deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getNutritionalInformation() {
        return nutritionalInformation;
    }

    public void setNutritionalInformation(String nutritionalInformation) {
        this.nutritionalInformation = nutritionalInformation;
    }

    public String getEdulcorants() {
        return edulcorants;
    }

    public void setEdulcorants(String edulcorants) {
        this.edulcorants = edulcorants;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<ProductSize> getSizes() {
        return sizes;
    }

    public void setSizes(List<ProductSize> sizes) {
        this.sizes = sizes;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
