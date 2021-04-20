package com.hescha.sportstore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Table(name = "myUsers")
@Data
public class User extends AbstractEntity {

    @Column(unique = true)
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
    private List<Order> myOrders = new ArrayList<Order>();

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
        super();
    }

    public List<String> getRoleListNames() {
        List<String> roleNames = new ArrayList<>();
        roleNames.add(role.getName());
        return roleNames;
    }

    public void update(String password, String firstname, String lastname,
                       String email) {
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

}
