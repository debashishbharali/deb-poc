package com.nucleus.deb.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.nucleus.cache.NeutrinoCache;
import com.nucleus.cache.NeutrinoCacheManager;
import com.nucleus.deb.cache.DebCacheConstants;
import com.nucleus.deb.domainobjects.BaseEntity;
import com.nucleus.deb.domainobjects.City;
import com.nucleus.deb.domainobjects.Continent;
import com.nucleus.deb.domainobjects.Country;
import com.nucleus.deb.domainobjects.accounting.AccountingStructure;

@Named("baseDao")
public class BaseDao {

	private static final String ACCOUNTING_STRUCTURE_QUERY = "FROM AccountingStructure a WHERE a.tenantId = :tenantId "
			+ "AND a.productId = :productId " + "AND a.schemeId = :schemeId " + "AND a.currencyId = :currencyId "
			+ "AND a.accountingClassificationMstId = :accountingClassificationMstId "
			+ "AND a.transactionType = :transactionType";

	@PersistenceContext
	EntityManager em;

	@Inject
	@Named("DEB_CACHE_MANAGER")
	private NeutrinoCacheManager debCacheManager;

	public EntityManager getEm() {
		System.out.println("***************getting em****************");
		return em;
	}

	public void setEm(EntityManager em) {
		System.out.println("***************setting em****************");
		this.em = em;
	}

	public City createCity(String cityName, Country country) {
		City city = new City();
		city.setCityName(cityName);
		city.setCountry(country);
		em.persist(city);
		return city;
	}

	public Country createCountry(String countryName, Continent continent) {
		Country country = new Country();
		country.setCountryName(countryName);
		country.setContinent(continent);
		em.persist(country);
		return country;
	}

	public Continent createContinent(String continentName) {
		Continent continent = new Continent();
		continent.setContinentName(continentName);
		em.persist(continent);
		return continent;
	}

	public <T extends BaseEntity> T findBaseEntityById(int id, Class<T> entityClass) {
		return em.find(entityClass, id);
	}

	public Continent getContinentByName(String continentName) {
		String qlString = "FROM Continent c WHERE c.continentName = :continentName";
		Query qry = em.createQuery(qlString);
		qry.setParameter("continentName", continentName);
		return (Continent) qry.getSingleResult();
	}

	public Country getCountryByName(String countryName) {
		String qlString = "FROM Country c WHERE c.countryName = :countryName";
		Query qry = em.createQuery(qlString);
		qry.setParameter("countryName", countryName);
		return (Country) qry.getSingleResult();
	}

	public City getCityByName(String cityName) {
		String qlString = "FROM City c WHERE c.cityName = :cityName";
		Query qry = em.createQuery(qlString);
		qry.setParameter("cityName", cityName);
		return (City) qry.getSingleResult();
	}

	public List<AccountingStructure> getAccountingStructureByAllAttribtes(Long tenantId, Long productId, Long schemeId,
			Long currencyId, Long accountingClassificationMstId, String transactionType) {

		Query qry = em.createQuery(ACCOUNTING_STRUCTURE_QUERY);
		qry.setParameter("tenantId", tenantId);
		qry.setParameter("productId", productId);
		qry.setParameter("schemeId", schemeId);
		qry.setParameter("currencyId", currencyId);
		qry.setParameter("accountingClassificationMstId", accountingClassificationMstId);
		qry.setParameter("transactionType", transactionType);
		return qry.getResultList();
	}

	public List<Long> getSchemeIdsBasedOnPrductId(Long productId) {
		String qlString = "SELECT distinct a.schemeId FROM AccountingStructure a WHERE a.productId = :productId ";
		Query qry = em.createQuery(qlString);
		qry.setParameter("productId", productId);
		List<Long> schemeIdList = qry.getResultList();
		if (schemeIdList == null) {
			schemeIdList = new ArrayList<>();
		}
		return schemeIdList;
	}

	public List<Long> getAllLongIds(String entityName) {
		String qlString = "SELECT distinct t.id FROM " + entityName + " t WHERE t.id IS NOT NULL";
		Query qry = em.createQuery(qlString);
		return qry.getResultList();
	}

	public List<String> getAllStringIds(String entityName) {
		String qlString = "SELECT distinct t.name FROM " + entityName + " t WHERE t.name IS NOT NULL";
		Query qry = em.createQuery(qlString);
		return qry.getResultList();
	}

	public List<AccountingStructure> putAccountingStructureToCache(NeutrinoCache neutrinoCache, Long tenantId,
			Long productId, Long schemeId, Long currencyId, Long accountingClassificationMstId,
			String transactionType) {

		List<AccountingStructure> accountingStructuresList = this.getAccountingStructureByAllAttribtes(tenantId,
				productId, schemeId, currencyId, accountingClassificationMstId, transactionType);

		StringBuilder key = new StringBuilder().append(tenantId).append(DebCacheConstants.DELIMITER).append(currencyId)
				.append(DebCacheConstants.DELIMITER).append(productId).append(DebCacheConstants.DELIMITER)
				.append(schemeId).append(DebCacheConstants.DELIMITER).append(accountingClassificationMstId)
				.append(DebCacheConstants.DELIMITER).append(transactionType);

		if (neutrinoCache == null) {
			neutrinoCache = debCacheManager.getNeutrinoCacheInstance(DebCacheConstants.ACCOUNTING_STRUCTURE);
		}
		neutrinoCache.put(key, accountingStructuresList);
		return accountingStructuresList;
	}

	@SuppressWarnings("unchecked")
	public List<AccountingStructure> getAccountingStructureFromCache(Long tenantId, Long productId, Long schemeId,
			Long currencyId, Long accountingClassificationMstId, String transactionType) {

		StringBuilder key = new StringBuilder().append(tenantId).append(DebCacheConstants.DELIMITER).append(currencyId)
				.append(DebCacheConstants.DELIMITER).append(productId).append(DebCacheConstants.DELIMITER)
				.append(schemeId).append(DebCacheConstants.DELIMITER).append(accountingClassificationMstId)
				.append(DebCacheConstants.DELIMITER).append(transactionType);

		NeutrinoCache neutrinoCacheInstance = debCacheManager
				.getNeutrinoCacheInstance(DebCacheConstants.ACCOUNTING_STRUCTURE);
		return (List<AccountingStructure>) neutrinoCacheInstance.get(key);
	}

}
