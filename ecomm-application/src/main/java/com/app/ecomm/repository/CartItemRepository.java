package com.app.ecomm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.app.ecomm.entity.CartItem;
import com.app.ecomm.entity.Product;
import com.app.ecomm.entity.User;

import jakarta.transaction.Transactional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	 CartItem findByUserAndProduct(User user, Product productRes);

	 @Modifying
	 void deleteByUserAndProduct(User user, Product productRes);

	 List<CartItem> findByUser(User user);

	 @Transactional
	 @Modifying
	 void deleteByUser(User u);
}
