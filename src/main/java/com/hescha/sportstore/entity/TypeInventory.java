package com.hescha.sportstore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class TypeInventory extends AbstractEntity {
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typeInventory")
    private List<Inventory> inventories = new ArrayList<Inventory>();

    @Override
    public String toString() {
        return name;
    }
}
