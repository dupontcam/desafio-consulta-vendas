package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSumDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleReportProjection;
import com.devsuperior.dsmeta.projections.SaleSumProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query(nativeQuery = true, value = "SELECT se.name, ROUND(SUM(sa.amount), 1) "
			+ "FROM tb_sales sa "
			+ "INNER JOIN tb_seller se ON se.id = sa.seller_id "
			+ "WHERE sa.date BETWEEN :start AND :end "
			+ "GROUP BY se.name")
	List<SaleSumProjection> getSummary(LocalDate start, LocalDate end);
	
	@Query(nativeQuery = true, value = "SELECT sa.id, sa.date, sa.amount, se.name as sellerName "
			+ "FROM tb_sales sa "
			+ "INNER JOIN tb_seller se ON se.id = sa.seller_id "
			+ "WHERE sa.date BETWEEN :start AND :end "
			+ "AND UPPER(se.name) "
			+ "LIKE UPPER(CONCAT('%',:name))")
	Page<SaleReportProjection> getReport(LocalDate start, LocalDate end, String name, Pageable pageable);

	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(obj.id, obj.date, obj.amount, obj.seller.name) "
			+ "FROM Sale obj "
			+ "WHERE obj.date BETWEEN :start AND :end "
			+ "AND UPPER(obj.seller.name) "
			+ "LIKE UPPER(CONCAT('% %',:name))")
	Page<SaleMinDTO> getJpqlReport(LocalDate start, LocalDate end, String name, Pageable pageable);
 
	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleSumDTO(obj.seller.name, ROUND(SUM(obj.amount), 1)) "
			+ "FROM Sale obj "
			+ "WHERE obj.date BETWEEN :start AND :end "
			+ "GROUP BY obj.seller.name")
	List<SaleSumDTO> getJpqlSummary(LocalDate start, LocalDate end);
}
