package com.ecommerce.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.ecommerce.order.entity.CartItem;

import jakarta.transaction.Transactional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	 CartItem findByUserIdAndProductId(String userId, String productId);

	 @Modifying
	 void deleteByUserIdAndProductId(String userId, String productId);

	 Optional<List<CartItem>> findByUserId(String userId);

	 @Transactional
	 @Modifying
	 void deleteByUserId(String userId);
}
