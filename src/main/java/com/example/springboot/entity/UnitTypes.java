package com.example.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Unit_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnitTypes {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "unitType", cascade = CascadeType.ALL)
    private List<Products> products;
}
