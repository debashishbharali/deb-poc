package com.nucleus.deb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.nucleus.cache.NeutrinoCache;
import com.nucleus.deb.dao.BaseDao;
import com.nucleus.deb.domainobjects.City;
import com.nucleus.deb.domainobjects.Continent;
import com.nucleus.deb.domainobjects.Country;
import com.nucleus.deb.domainobjects.accounting.AccountingClassification;
import com.nucleus.deb.domainobjects.accounting.AccountingStructure;
import com.nucleus.deb.domainobjects.accounting.Currency;
import com.nucleus.deb.domainobjects.accounting.Product;
import com.nucleus.deb.domainobjects.accounting.Tenant;
import com.nucleus.deb.domainobjects.accounting.TransactionType;

@Named("debService")
public class DebServiceImpl implements DebService {

	private AtomicLong time;
	private AtomicLong count;
	private AtomicLong cacheTime;
	private AtomicLong cacheCount;

	private List<Long> tenantIdList;
	private List<Long> productIdList;
	private Map<Long, List<Long>> mapOfSchemeIdListBasedOnProductId;
	private List<Long> currencyIdList;
	private List<Long> accountingClassificationMstIdList;
	private List<String> transactionTypeList;

	private Random randomGeneratorForTenant;
	private Random randomGeneratorForProduct;
	private Random randomGeneratorForScheme;
	private Random randomGeneratorForCurrency;
	private Random randomGeneratorForAccClass;
	private Random randomGeneratorForTransactionType;

	@PostConstruct
	public void postContruct() {
		time = new AtomicLong();
		count = new AtomicLong();
		cacheTime = new AtomicLong();
		cacheCount = new AtomicLong();
		this.initDataMap();
	}

	private void initDataMap() {

		tenantIdList = populateLongIdList(Tenant.class.getSimpleName());
		productIdList = populateLongIdList(Product.class.getSimpleName());
		populateMapOfSchemeIdListBasedOnProductId();
		productIdList.add(null);
		currencyIdList = populateLongIdList(Currency.class.getSimpleName());
		accountingClassificationMstIdList = populateLongIdList(AccountingClassification.class.getSimpleName());
		transactionTypeList = populateStringIdList(TransactionType.class.getSimpleName());

		randomGeneratorForTenant = new Random();
		randomGeneratorForProduct = new Random();
		randomGeneratorForScheme = new Random();
		randomGeneratorForCurrency = new Random();
		randomGeneratorForAccClass = new Random();
		randomGeneratorForTransactionType = new Random();

	}

	private void populateMapOfSchemeIdListBasedOnProductId() {
		mapOfSchemeIdListBasedOnProductId = new HashMap<>();
		if (productIdList != null && !productIdList.isEmpty()) {
			for (Long productId : productIdList) {
				mapOfSchemeIdListBasedOnProductId.put(productId, getSchemeIdsBasedOnPrductId(productId));
			}
		}
	}

	private List<Long> populateLongIdList(String entityName) {
		List<Long> tempLongList = getAllLongIds(entityName);
		if (tempLongList == null || tempLongList.isEmpty()) {
			tempLongList = new ArrayList<>();
		}
		return tempLongList;
	}

	private List<String> populateStringIdList(String entityName) {
		List<String> tempLongList = getAllStringIds(entityName);
		if (tempLongList == null || tempLongList.isEmpty()) {
			tempLongList = new ArrayList<>();
		}
		return tempLongList;
	}

	@Inject
	@Named("baseDao")
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	@Override
	public City getCityById(Integer id) {
		return dao.findBaseEntityById(id, City.class);
	}

	@Override
	public City getCityByName(String cityName) {
		return dao.getCityByName(cityName);
	}

	@Override
	@Transactional
	public City createCity(String cityName, Country country) {
		return dao.createCity(cityName, country);
	}

	@Override
	public Country getCountryById(Integer id) {
		return dao.findBaseEntityById(id, Country.class);
	}

	@Override
	public Country getCountryByName(String countryName) {
		return dao.getCountryByName(countryName);
	}

	@Override
	@Transactional
	public Country createCountry(String countryName, Continent continent) {
		return dao.createCountry(countryName, continent);
	}

	@Override
	public Continent getContinentById(Integer id) {
		return dao.findBaseEntityById(id, Continent.class);
	}

	@Override
	public Continent getContinentByName(String continentName) {
		return dao.getContinentByName(continentName);
	}

	@Override
	@Transactional
	public Continent createContinent(String continentName) {
		return dao.createContinent(continentName);
	}

	@Override
	public List<AccountingStructure> getAccountingStructureByAllAttribtes(Long tenantId, Long productId, Long schemeId,
			Long currencyId, Long accountingClassificationMstId, String transactionType) {
		List<AccountingStructure> accountingStructureList = null;

		Long startTime = System.currentTimeMillis();
		accountingStructureList = dao.getAccountingStructureByAllAttribtes(tenantId, productId, schemeId, currencyId,
				accountingClassificationMstId, transactionType);
		updateTime(System.currentTimeMillis() - startTime);

		return accountingStructureList;
	}

	@Override
	public Map<String, Long> printTotalTimeTakenAndCount() {
		System.out.println("Total Time Taken : " + time);
		System.out.println("Total Count : " + count);
		System.out.println("Total Time Taken Cache : " + cacheTime);
		System.out.println("Total Count Cache : " + cacheCount);
		Map<String, Long> x = new HashMap<>();
		x.put("TIME", time.get());
		x.put("COUNT", count.get());
		x.put("TIME_CACHE", cacheTime.get());
		x.put("COUNT_CACHE", cacheCount.get());
		return x;
	}

	@Override
	public List<Long> getAllLongIds(String entityName) {
		return dao.getAllLongIds(entityName);
	}

	@Override
	public List<String> getAllStringIds(String entityName) {
		return dao.getAllStringIds(entityName);
	}

	@Override
	public List<Long> getSchemeIdsBasedOnPrductId(Long productId) {
		return dao.getSchemeIdsBasedOnPrductId(productId);
	}

	@Override
	public List<AccountingStructure> putAccountingStructureToCache(NeutrinoCache neutrinoCache, Long tenantId,
			Long productId, Long schemeId, Long currencyId, Long accountingClassificationMstId,
			String transactionType) {
		return dao.putAccountingStructureToCache(neutrinoCache, tenantId, productId, schemeId, currencyId,
				accountingClassificationMstId, transactionType);
	}

	@Override
	public List<AccountingStructure> getAccountingStructureFromCache(Long tenantId, Long productId, Long schemeId,
			Long currencyId, Long accountingClassificationMstId, String transactionType) {

		List<AccountingStructure> accountingStructureList = null;

		Long startTime = System.currentTimeMillis();
		accountingStructureList = dao.getAccountingStructureFromCache(tenantId, productId, schemeId, currencyId,
				accountingClassificationMstId, transactionType);
		updateCacheTime(System.currentTimeMillis() - startTime);

		return accountingStructureList;
	}

	@Override
	public void putAllAccountingStructuresIntoCache() {
		putAllAccountingStructuresIntoCache(null);
	}

	@Override
	public void putAllAccountingStructuresIntoCache(NeutrinoCache neutrinoCache) {

		for (Long tenantId : tenantIdList) {
			for (Long currencyId : currencyIdList) {
				for (Long accountingClassificationMstId : accountingClassificationMstIdList) {
					for (String transactionType : transactionTypeList) {
						for (Long productId : productIdList) {
							if (productId != null) {
								for (Long schemeId : mapOfSchemeIdListBasedOnProductId.get(productId)) {
									putAccountingStructureToCache(neutrinoCache, tenantId, productId, schemeId,
											currencyId, accountingClassificationMstId, transactionType);
								}
							} else {
								putAccountingStructureToCache(neutrinoCache, tenantId, null, null, currencyId,
										accountingClassificationMstId, transactionType);
							}
						}
					}

				}

			}
		}

	}

	@Override
	public void loopThroughAllAccountingStructures() {
		for (Long tenantId : tenantIdList) {
			for (Long currencyId : currencyIdList) {
				for (Long accountingClassificationMstId : accountingClassificationMstIdList) {
					for (String transactionType : transactionTypeList) {
						for (Long productId : productIdList) {
							if (productId != null) {
								for (Long schemeId : mapOfSchemeIdListBasedOnProductId.get(productId)) {
									getAccountingStructureByAllAttribtes(tenantId, productId, schemeId, currencyId,
											accountingClassificationMstId, transactionType);
								}
							} else {
								getAccountingStructureByAllAttribtes(tenantId, null, null, currencyId,
										accountingClassificationMstId, transactionType);

							}
						}
					}

				}

			}
		}
	}

	@Override
	public void loopThroughAllAccountingStructuresFromCache() {
		for (Long tenantId : tenantIdList) {
			for (Long currencyId : currencyIdList) {
				for (Long accountingClassificationMstId : accountingClassificationMstIdList) {
					for (String transactionType : transactionTypeList) {
						for (Long productId : productIdList) {
							if (productId != null) {
								for (Long schemeId : mapOfSchemeIdListBasedOnProductId.get(productId)) {
									getAccountingStructureFromCache(tenantId, productId, schemeId, currencyId,
											accountingClassificationMstId, transactionType);
								}
							} else {
								getAccountingStructureFromCache(tenantId, null, null, currencyId,
										accountingClassificationMstId, transactionType);
							}
						}
					}

				}

			}
		}
	}

	private void updateTime(Long timeTaken) {
		time.addAndGet(timeTaken);
		count.incrementAndGet();
	}

	private void updateCacheTime(Long timeTaken) {
		cacheTime.addAndGet(timeTaken);
		cacheCount.incrementAndGet();
	}

	public Long getRandomTenantId() {
		// int index = randomGeneratorForTenant.nextInt(tenantIdList.size());
		return tenantIdList.get(0);
	}

	@Override
	public Long getRandomProductId() {
		int index = randomGeneratorForProduct.nextInt(productIdList.size());
		return productIdList.get(index);
	}

	@Override
	public Long getRandomSchemeId(Long productId) {
		if (productId == null) {
			return null;
		}
		int index = randomGeneratorForScheme.nextInt(mapOfSchemeIdListBasedOnProductId.get(productId).size());
		return mapOfSchemeIdListBasedOnProductId.get(productId).get(index);
	}

	@Override
	public Long getRandomCurrencyId() {
		int index = randomGeneratorForCurrency.nextInt(currencyIdList.size());
		return currencyIdList.get(index);
	}

	@Override
	public Long getRandomAccClassMstId() {
		int index = randomGeneratorForAccClass.nextInt(accountingClassificationMstIdList.size());
		return accountingClassificationMstIdList.get(index);
	}

	@Override
	public String getRandomTransactionType() {
		int index = randomGeneratorForTransactionType.nextInt(transactionTypeList.size());
		return transactionTypeList.get(index);
	}

}
