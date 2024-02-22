package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String userName;
    @Column
    private String fullName;
    @Column
    private String hashPassword;
    @Column(unique = true)
    private String email;
    @Column
    @CreationTimestamp
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    private LocalDateTime createDate;
    @Column
    private String imgUrl;
    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;
    @OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
    private List<Orders> orders;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="roleId")
    @JsonIgnoreProperties(value={"applicantions","hibernateLazyInitializer"})
    private Roles roles;
}
