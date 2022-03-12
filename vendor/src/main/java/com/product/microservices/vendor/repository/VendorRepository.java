package com.product.microservices.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.microservices.vendor.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
	
	@Query(value="select * from vendor where vendor_id in (select vendor_id from vendor_stock where product_id = ?1 and stock_in_hand>=?2) order by rating desc limit 1",nativeQuery=true)
	Vendor findVendor(Long id,Long qty);
}
