package com.jmd.cafe.order.jpa.repository;

import com.jmd.cafe.order.jpa.entity.OrderRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManageKeyRepository extends JpaRepository<OrderRequestEntity, Long> {
}
