package com.nucleus.cache;

import java.util.Map;

public class NeutrinoCacheRegion {

	private Map<String, NeutrinoCachePopulator> namedCaches;

	public NeutrinoCacheRegion(Map<String, NeutrinoCachePopulator> namedCaches,
			NeutrinoCacheManager neutrinoCacheManager) {
		super();
		this.namedCaches = namedCaches;
		this.merge(neutrinoCacheManager);
	}

	public Map<String, NeutrinoCachePopulator> getNamedCaches() {
		return namedCaches;
	}

	public final void merge(NeutrinoCacheManager neutrinoCacheManager) {
		neutrinoCacheManager.registerNeutrinoCacheRegion(this);
	}

}
