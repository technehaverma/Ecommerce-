package com.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.order.entity.Order;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Long>{

}
