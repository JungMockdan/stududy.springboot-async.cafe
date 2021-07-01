package com.jmd.cafe.order.api.repository;

import com.jmd.cafe.order.jpa.entity.OrderRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderRequestEntity, Long> {
}
