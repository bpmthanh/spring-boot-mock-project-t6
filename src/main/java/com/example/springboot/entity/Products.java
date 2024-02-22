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
@Table(name = "Products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Integer quantity;
    @Column
    private Double price;
    @Column
    private String imgUrl;
    @Column
    private String slug;
    @Column
    private String des;
    @Column
    private Boolean isDeleted;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "typeId")
    @JsonIgnoreProperties("products")
    private ProductTypes productType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unitId")
    @JsonIgnoreProperties("products")
    private UnitTypes unitType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "OrderDetails",
            joinColumns = @JoinColumn(name = "productId"),
            inverseJoinColumns = @JoinColumn(name = "orderId")
    )
    @JsonIgnoreProperties("products")
    private List<Orders> orders;
}
