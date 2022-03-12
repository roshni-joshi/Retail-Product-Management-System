package com.product.microservices.proceedtobuy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.microservices.proceedtobuy.model.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

	List<Wishlist> findAllByUsername(final String username);
}
