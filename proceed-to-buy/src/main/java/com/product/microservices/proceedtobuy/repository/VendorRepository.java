package com.product.microservices.proceedtobuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.microservices.proceedtobuy.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
