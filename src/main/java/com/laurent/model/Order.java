package com.laurent.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "`order`")
@IdClass(Order.OrderPK.class)
public class Order {
    @Id
    private String numero;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",
            referencedColumnName = "id", insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "customer_id_fk"))
    private Customer customer;

    @OneToMany
    @OrderColumn(name = "`order`")
    private List<Product> products = new ArrayList<>();

    public Order(String numero, Customer customer) {
        this.numero = numero;
        this.customer = customer;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @Getter
    @EqualsAndHashCode
    public static class OrderPK implements Serializable {
        private String numero;
        private Customer customer;
    }
}
