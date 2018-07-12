package com.nucleus.deb.service;

import java.util.List;
import java.util.Map;

import com.nucleus.cache.NeutrinoCache;
import com.nucleus.deb.domainobjects.City;
import com.nucleus.deb.domainobjects.Continent;
import com.nucleus.deb.domainobjects.Country;
import com.nucleus.deb.domainobjects.accounting.AccountingStructure;

public interface DebService {

	public City getCityById(Integer id);

	public City getCityByName(String cityName);

	public City createCity(String cityName, Country country);

	public Country getCountryById(Integer id);

	public Country getCountryByName(String countryName);

	public Country createCountry(String countryName, Continent continent);

	public Continent getContinentById(Integer id);

	public Continent getContinentByName(String continentName);

	public Continent createContinent(String continentName);

	public List<AccountingStructure> getAccountingStructureByAllAttribtes(Long tenantId, Long productId, Long schemeId,
			Long currencyId, Long accountingClassificationMstId, String transactionType);

	public List<Long> getAllLongIds(String entityName);

	public List<String> getAllStringIds(String entityName);

	public List<Long> getSchemeIdsBasedOnPrductId(Long productId);

	public Map<String, Long> printTotalTimeTakenAndCount();

	public List<AccountingStructure> putAccountingStructureToCache(NeutrinoCache neutrinoCache, Long tenantId,
			Long productId, Long schemeId, Long currencyId, Long accountingClassificationMstId, String transactionType);

	public List<AccountingStructure> getAccountingStructureFromCache(Long tenantId, Long productId, Long schemeId,
			Long currencyId, Long accountingClassificationMstId, String transactionType);

	void loopThroughAllAccountingStructures();

	void loopThroughAllAccountingStructuresFromCache();

	public void putAllAccountingStructuresIntoCache();

	public void putAllAccountingStructuresIntoCache(NeutrinoCache neutrinoCache);

	public Long getRandomTenantId();

	public Long getRandomProductId();

	public Long getRandomSchemeId(Long productId);

	public Long getRandomCurrencyId();

	public Long getRandomAccClassMstId();

	public String getRandomTransactionType();
}
