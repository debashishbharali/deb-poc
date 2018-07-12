package com.nucleus.cache;

import java.util.Map;

public class NeutrinoCacheRegionInfinispan extends NeutrinoCacheRegion {

	private String configFile;

	public NeutrinoCacheRegionInfinispan(Map<String, NeutrinoCachePopulator> namedCaches,
			NeutrinoCacheManager neutrinoCacheManager, String configFile) {
		super(namedCaches, neutrinoCacheManager);
		this.configFile = configFile;
	}

	public final String getConfigFile() {
		return configFile;
	}
}
