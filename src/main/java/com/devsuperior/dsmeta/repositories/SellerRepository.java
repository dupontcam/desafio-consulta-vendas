package com.devsuperior.dsmeta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {

	List<Seller> findByNameContainingIgnoreCase(String name);
}
