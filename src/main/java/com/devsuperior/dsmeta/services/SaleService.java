package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSumDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> getJpqlReport(String minDate, String maxDate, String name, Pageable pageable) {

		if (minDate.isEmpty() && maxDate.isEmpty()) {
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			LocalDate start = today.minusYears(1L);
			Page<SaleMinDTO> result = repository.getJpqlReport(start, today, name, pageable);

			return result;// .map(x -> new SaleMinDTO(x));

		} else if (minDate.isEmpty() && !maxDate.isEmpty()) {
			LocalDate end = LocalDate.parse(maxDate);
			LocalDate start = end.minusYears(1L);
			Page<SaleMinDTO> result = repository.getJpqlReport(start, end, name, pageable);

			return result;// .map(x -> new SaleMinDTO(x));

		} else if (maxDate.isEmpty() && !minDate.isEmpty()) {
			LocalDate start = LocalDate.parse(minDate);
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			Page<SaleMinDTO> result = repository.getJpqlReport(start, today, name, pageable);

			return result;// .map(x -> new SaleMinDTO(x));
		} else {
			LocalDate start = LocalDate.parse(minDate);
			LocalDate end = LocalDate.parse(maxDate);

			Page<SaleMinDTO> result = repository.getJpqlReport(start, end, name, pageable);

			return result;// .map(x -> new SaleMinDTO(x));
		}
	}

	public List<SaleSumDTO> getSummary(String minDate, String maxDate) {
		if (minDate.isEmpty() && maxDate.isEmpty()) {
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			LocalDate start = today.minusYears(1L);
			List<SaleSumDTO> result = repository.getJpqlSummary(start, today);

			return result;// .stream().map(x -> new SaleSumDTO(x)).toList();

		} else if (minDate.isEmpty() && !maxDate.isEmpty()) {
			LocalDate end = LocalDate.parse(maxDate);
			LocalDate start = end.minusYears(1L);
			List<SaleSumDTO> result = repository.getJpqlSummary(start, end);

			return result;// .stream().map(x -> new SaleSumDTO(x)).toList();

		} else if (maxDate.isEmpty() && !minDate.isEmpty()) {
			LocalDate start = LocalDate.parse(minDate);
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			List<SaleSumDTO> result = repository.getJpqlSummary(start, today);

			return result;// .stream().map(x -> new SaleSumDTO(x)).toList();

		} else {
			LocalDate start = LocalDate.parse(minDate);
			LocalDate end = LocalDate.parse(maxDate);

			List<SaleSumDTO> result = repository.getJpqlSummary(start, end);

			return result;// .stream().map(x -> new SaleSumDTO(x)).toList();
		}
	}
}
