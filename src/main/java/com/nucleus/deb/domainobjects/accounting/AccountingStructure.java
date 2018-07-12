package com.nucleus.deb.domainobjects.accounting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "AccountingStructure", indexes = { @Index(name = "TENANT_ID_IDX1", columnList = "tenantId"),
		@Index(name = "SCHEME_ID_IDX2", columnList = "schemeId"),
		@Index(name = "PRODUCT_ID_IDX3", columnList = "productId"),
		@Index(name = "CURRENCY_ID_IDX4", columnList = "currencyId"),
		@Index(name = "ACC_CLASS_MST_ID_IDX5", columnList = "accountingClassificationMstId"),
		@Index(name = "TRANSACTION_TYPE_IDX6", columnList = "transactionType") })
public class AccountingStructure extends LongBaseEntity {

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

	@Column(name = "strategyId")
	private Long strategyId;

	@Column(name = "debitChartOfAccountMstId")
	private Long debitChartOfAccountMstId;

	@Column(name = "creditChartOfAccountMstId")
	private Long creditChartOfAccountMstId;

	@Column(name = "chargeCodeMstId")
	private Long chargeCodeMstId;

	@Column(name = "debitGlAccountingClassMstId")
	private Long debitGlAccountingClassMstId;

	@Column(name = "creditGlAccountingClassMstId")
	private Long creditGlAccountingClassMstId;

	@Column(name = "debitMnemonicTypeId")
	private Long debitMnemonicTypeId;

	@Column(name = "creditMnemonicTypeId")
	private Long creditMnemonicTypeId;

	@Column(name = "debitSubLedgerRequired", length = 1)
	private String debitSubLedgerRequired;

	@Column(name = "creditSubLedgerRequired", length = 1)
	private String creditSubLedgerRequired;

	@Column(name = "narration", length = 1000)
	private String narration;

	@Column(name = "debitGlCode", length = 12)
	private String debitGlCode;

	@Column(name = "creditGlCode", length = 12)
	private String creditGlCode;

	@Column(name = "chargeCode", length = 19)
	private String chargeCode;

	@Column(name = "debitAccountType")
	private Long debitAccountType;

	@Column(name = "creditAccountType")
	private Long creditAccountType;

	@Column(name = "debitAccountTypeCode", length = 2)
	private String debitAccountTypeCode;

	@Column(name = "creditAccountTypeCode", length = 2)
	private String creditAccountTypeCode;

	@Column(name = "accountingPolicyCode", length = 24)
	private String accountingPolicyCode;

	@Column(name = "debitGlAccountingClass", length = 255)
	private String debitGlAccountingClass;

	@Column(name = "creditGlAccountingClass", length = 255)
	private String creditGlAccountingClass;

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

	public Long getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(Long strategyId) {
		this.strategyId = strategyId;
	}

	public Long getDebitChartOfAccountMstId() {
		return debitChartOfAccountMstId;
	}

	public void setDebitChartOfAccountMstId(Long debitChartOfAccountMstId) {
		this.debitChartOfAccountMstId = debitChartOfAccountMstId;
	}

	public Long getCreditChartOfAccountMstId() {
		return creditChartOfAccountMstId;
	}

	public void setCreditChartOfAccountMstId(Long creditChartOfAccountMstId) {
		this.creditChartOfAccountMstId = creditChartOfAccountMstId;
	}

	public Long getChargeCodeMstId() {
		return chargeCodeMstId;
	}

	public void setChargeCodeMstId(Long chargeCodeMstId) {
		this.chargeCodeMstId = chargeCodeMstId;
	}

	public Long getDebitGlAccountingClassMstId() {
		return debitGlAccountingClassMstId;
	}

	public void setDebitGlAccountingClassMstId(Long debitGlAccountingClassMstId) {
		this.debitGlAccountingClassMstId = debitGlAccountingClassMstId;
	}

	public Long getCreditGlAccountingClassMstId() {
		return creditGlAccountingClassMstId;
	}

	public void setCreditGlAccountingClassMstId(Long creditGlAccountingClassMstId) {
		this.creditGlAccountingClassMstId = creditGlAccountingClassMstId;
	}

	public Long getDebitMnemonicTypeId() {
		return debitMnemonicTypeId;
	}

	public void setDebitMnemonicTypeId(Long debitMnemonicTypeId) {
		this.debitMnemonicTypeId = debitMnemonicTypeId;
	}

	public Long getCreditMnemonicTypeId() {
		return creditMnemonicTypeId;
	}

	public void setCreditMnemonicTypeId(Long creditMnemonicTypeId) {
		this.creditMnemonicTypeId = creditMnemonicTypeId;
	}

	public String getDebitSubLedgerRequired() {
		return debitSubLedgerRequired;
	}

	public void setDebitSubLedgerRequired(String debitSubLedgerRequired) {
		this.debitSubLedgerRequired = debitSubLedgerRequired;
	}

	public String getCreditSubLedgerRequired() {
		return creditSubLedgerRequired;
	}

	public void setCreditSubLedgerRequired(String creditSubLedgerRequired) {
		this.creditSubLedgerRequired = creditSubLedgerRequired;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getDebitGlCode() {
		return debitGlCode;
	}

	public void setDebitGlCode(String debitGlCode) {
		this.debitGlCode = debitGlCode;
	}

	public String getCreditGlCode() {
		return creditGlCode;
	}

	public void setCreditGlCode(String creditGlCode) {
		this.creditGlCode = creditGlCode;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public Long getDebitAccountType() {
		return debitAccountType;
	}

	public void setDebitAccountType(Long debitAccountType) {
		this.debitAccountType = debitAccountType;
	}

	public Long getCreditAccountType() {
		return creditAccountType;
	}

	public void setCreditAccountType(Long creditAccountType) {
		this.creditAccountType = creditAccountType;
	}

	public String getDebitAccountTypeCode() {
		return debitAccountTypeCode;
	}

	public void setDebitAccountTypeCode(String debitAccountTypeCode) {
		this.debitAccountTypeCode = debitAccountTypeCode;
	}

	public String getCreditAccountTypeCode() {
		return creditAccountTypeCode;
	}

	public void setCreditAccountTypeCode(String creditAccountTypeCode) {
		this.creditAccountTypeCode = creditAccountTypeCode;
	}

	public String getAccountingPolicyCode() {
		return accountingPolicyCode;
	}

	public void setAccountingPolicyCode(String accountingPolicyCode) {
		this.accountingPolicyCode = accountingPolicyCode;
	}

	public String getDebitGlAccountingClass() {
		return debitGlAccountingClass;
	}

	public void setDebitGlAccountingClass(String debitGlAccountingClass) {
		this.debitGlAccountingClass = debitGlAccountingClass;
	}

	public String getCreditGlAccountingClass() {
		return creditGlAccountingClass;
	}

	public void setCreditGlAccountingClass(String creditGlAccountingClass) {
		this.creditGlAccountingClass = creditGlAccountingClass;
	}

	@Override
	public String toString() {
		return "AccountingStructure [tenantId=" + tenantId + ", schemeId=" + schemeId + ", productId=" + productId
				+ ", currencyId=" + currencyId + ", accountingClassificationMstId=" + accountingClassificationMstId
				+ ", transactionType=" + transactionType + ", strategyId=" + strategyId + ", debitChartOfAccountMstId="
				+ debitChartOfAccountMstId + ", creditChartOfAccountMstId=" + creditChartOfAccountMstId
				+ ", chargeCodeMstId=" + chargeCodeMstId + ", debitGlAccountingClassMstId="
				+ debitGlAccountingClassMstId + ", creditGlAccountingClassMstId=" + creditGlAccountingClassMstId
				+ ", debitMnemonicTypeId=" + debitMnemonicTypeId + ", creditMnemonicTypeId=" + creditMnemonicTypeId
				+ ", debitSubLedgerRequired=" + debitSubLedgerRequired + ", creditSubLedgerRequired="
				+ creditSubLedgerRequired + ", narration=" + narration + ", debitGlCode=" + debitGlCode
				+ ", creditGlCode=" + creditGlCode + ", chargeCode=" + chargeCode + ", debitAccountType="
				+ debitAccountType + ", creditAccountType=" + creditAccountType + ", debitAccountTypeCode="
				+ debitAccountTypeCode + ", creditAccountTypeCode=" + creditAccountTypeCode + ", accountingPolicyCode="
				+ accountingPolicyCode + ", debitGlAccountingClass=" + debitGlAccountingClass
				+ ", creditGlAccountingClass=" + creditGlAccountingClass + "]";
	}
	
	

}
