package com.example.entity.user;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "ROLES")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ACCOUNT_ROLES",
            joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID")
    )
    private List<Account> accounts;

}