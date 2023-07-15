package com.devsuperior.dsmeta.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.repositories.SellerRepository;

@Service
public class SellerService {

	@Autowired
	private SellerRepository repository;

	public List<SellerMinDTO> findByName(String name) {
		List<Seller> result = repository.findByNameContainingIgnoreCase(name);

		return result.stream().map(x -> new SellerMinDTO(x)).toList();
	}
}
