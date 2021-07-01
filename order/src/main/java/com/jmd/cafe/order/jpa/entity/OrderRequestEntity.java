package com.jmd.cafe.order.jpa.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "t_cafe_order")
@Data
@Entity
public class OrderRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
