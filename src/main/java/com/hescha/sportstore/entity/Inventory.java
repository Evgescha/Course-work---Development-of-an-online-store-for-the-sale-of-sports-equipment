package com.hescha.sportstore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Inventory extends AbstractEntity {

    private String name;

    private float price;

    @Column(length = 3000)
    private String description;

    private String image;

    @ManyToOne
    @JoinColumn(name = "type_inventory_id")
    private TypeInventory typeInventory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "inventory")
    private List<OrderList> orderLists = new ArrayList<OrderList>();

    @Override
    public String toString() {
        return name + ", " + price + ", " + typeInventory;
    }

}
