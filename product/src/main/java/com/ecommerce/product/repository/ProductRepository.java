package com.ecommerce.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.product.entity.Product;

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	boolean existsByIdAndActive(long id, boolean b);

	@Transactional
	@Modifying
	@Query("update Product p set p.active=false where p.id =:id")
	void updateActiveStatus(@Param("id") long id);

	@Query("SELECT p from Product p where p.active=true and p.stockQuantity>0 and (LOWER(p.name) "
			+ "LIKE LOWER(CONCAT('%',:keyword,'%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%',:keyword,'%')))")
	List<Product> findByKeyword(@Param("keyword") String keyword);

}
