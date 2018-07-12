package com.nucleus.deb.valueobjects;

import java.io.Serializable;

public class AccountingStructureVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long tenantId;

	private Long schemeId;

	private Long productId;

	private Long currencyId;

	private Long accountingClassificationMstId;

	private String transactionType;

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(Long schemeId) {
		this.schemeId = schemeId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	public Long getAccountingClassificationMstId() {
		return accountingClassificationMstId;
	}

	public void setAccountingClassificationMstId(Long accountingClassificationMstId) {
		this.accountingClassificationMstId = accountingClassificationMstId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

}
