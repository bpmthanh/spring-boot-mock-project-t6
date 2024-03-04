package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Order_Details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer quantity;
    @Column
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    @JsonIgnoreProperties("OrderDetails")
    private Products products;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    @JsonIgnoreProperties("OrderDetails")
    private Orders orders;
}
