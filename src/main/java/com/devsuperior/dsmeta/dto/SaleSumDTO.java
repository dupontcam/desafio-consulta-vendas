package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleSumProjection;

public class SaleSumDTO {

	private String sellerName;
	private Double total;
	
	public SaleSumDTO() {
	}

	public SaleSumDTO(String sellerName, Double total) {
		this.sellerName = sellerName;
		this.total = total;
	}	
	
	public SaleSumDTO(SaleSumProjection projection) {
		sellerName = projection.getSellerName();
		total = projection.getTotal();
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "SaleSumDTO [sellerName=" + sellerName + ", total=" + total + "]";
	}	
}
