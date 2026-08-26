package com.app.ecomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ecomm.entity.Order;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Long>{

}
