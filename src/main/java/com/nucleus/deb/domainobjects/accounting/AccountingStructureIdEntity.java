package com.nucleus.deb.domainobjects.accounting;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
//@Table(indexes = { @Index(name = "TENANT_ID_IDX1", columnList = "tenantId"),
//		@Index(name = "SCHEME_ID_IDX2", columnList = "schemeId"),
//		@Index(name = "PRODUCT_ID_IDX3", columnList = "productId"),
//		@Index(name = "CURRENCY_ID_IDX4", columnList = "currencyId"),
//		@Index(name = "ACC_CLASS_MASTER_ID_IDX5", columnList = "accountingClassificationMstId"),
//		@Index(name = "TRANSACTION_TYPE_IDX6", columnList = "transactionType") })
public class AccountingStructureIdEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "tenantId")
	private Long tenantId;

	@Column(name = "schemeId")
	private Long schemeId;

	@Column(name = "productId")
	private Long productId;

	@Column(name = "currencyId")
	private Long currencyId;

	@Column(name = "accountingClassificationMstId")
	private Long accountingClassificationMstId;

	@Column(name = "transactionType", length = 3)
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

	@Override
	public String toString() {
		return "AccountingStructureIdEntity [tenantId=" + tenantId + ", schemeId=" + schemeId + ", productId="
				+ productId + ", currencyId=" + currencyId + ", accountingClassificationMstId="
				+ accountingClassificationMstId + ", transactionType=" + transactionType + "]";
	}

}
