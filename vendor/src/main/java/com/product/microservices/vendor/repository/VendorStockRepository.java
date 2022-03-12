package com.product.microservices.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.microservices.vendor.model.VendorStock;

@Repository
public interface VendorStockRepository extends JpaRepository<VendorStock, Long> {
	
}
