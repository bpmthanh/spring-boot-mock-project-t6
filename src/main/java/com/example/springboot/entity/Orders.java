package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    @CreationTimestamp
    private Date createdDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties(value = { "applicantions", "hibernateLazyInitializer" })
    private Users users;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetailsList;
}
