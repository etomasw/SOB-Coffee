package com.ernest.sobpractica.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "product_size")
public class ProductSize implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mida;
    private BigDecimal preu;

    @ManyToOne
    @JoinColumn(name = "FK_PRODUCT", nullable = false, updatable = false)
    private Product product;

    public ProductSize(String mida, BigDecimal preu) {
        this.mida = mida;
        this.preu = preu;
    }

    public ProductSize() {
    }

    public String getMida() {
        return mida;
    }

    public void setMida(String mida) {
        this.mida = mida;
    }

    public BigDecimal getPreu() {
        return preu;
    }

    public void setPreu(BigDecimal preu) {
        this.preu = preu;
    }
}
