package com.nucleus.deb.cache;

import javax.inject.Inject;
import javax.inject.Named;

import com.nucleus.cache.NeutrinoCache;
import com.nucleus.cache.NeutrinoCachePopulator;
import com.nucleus.deb.service.DebService;

@Named("accountingStructureCachePopulator")
public class AccountingStructureCachePopulator extends NeutrinoCachePopulator {

	@Inject
	@Named("debService")
	private DebService debService;

	@Override
	public void init() {

	}

	@Override
	public void build(NeutrinoCache neutrinoCache) {
		debService.putAllAccountingStructuresIntoCache(neutrinoCache);
	}

	@Override
	public Object fallback(Object key) {
		String keyString = ((StringBuilder) key).toString();

		String[] keyArray = keyString.split(DebCacheConstants.REGEX_DELIMITER);
		Long tenantId = Long.parseLong(keyArray[0].trim());
		Long productId = Long.parseLong(keyArray[1].trim());
		Long schemeId = Long.parseLong(keyArray[2].trim());
		Long currencyId = Long.parseLong(keyArray[3].trim());
		Long accountingClassificationMstId = Long.parseLong(keyArray[4].trim());
		String transactionType = keyArray[5].trim();

		return debService.getAccountingStructureByAllAttribtes(tenantId, productId, schemeId, currencyId,
				accountingClassificationMstId, transactionType);
	}
}
