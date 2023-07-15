package com.devsuperior.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.services.SellerService;

@RestController
@RequestMapping(value = "/seller")
public class SellerController {

	@Autowired
	private SellerService service;

	@GetMapping
	public ResponseEntity<List<SellerMinDTO>> findByNameContainingIgnoreCase(
			@RequestParam(name = "name", defaultValue = "") String name) {

		List<SellerMinDTO> dto = service.findByName(name);

		return ResponseEntity.ok(dto);
	}
}
