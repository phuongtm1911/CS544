package edu.mum.cs544;

import javax.persistence.*;

@Entity
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer quantity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderLine() {
    }

    public OrderLine(Integer quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
