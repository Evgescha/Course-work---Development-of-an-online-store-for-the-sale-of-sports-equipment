package com.hescha.sportstore.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "myOrders")
@Data
public class Order extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderList> orderLists = new ArrayList<OrderList>();

    private Date dates;

    private Time times;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
