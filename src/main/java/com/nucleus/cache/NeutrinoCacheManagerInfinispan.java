package com.nucleus.cache;

import java.io.IOException;
import java.util.Map;

import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

public class NeutrinoCacheManagerInfinispan extends NeutrinoCacheManager {

	@Override
	protected final void registerNeutrinoCache() {
		for (NeutrinoCacheRegion neutrinoCacheRegion : getNeutrinoCacheRegionSet()) {
			EmbeddedCacheManager embeddedCacheManager = null;
			try {
				embeddedCacheManager = new DefaultCacheManager(
						((NeutrinoCacheRegionInfinispan) neutrinoCacheRegion).getConfigFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (Map.Entry<String, NeutrinoCachePopulator> entry : neutrinoCacheRegion.getNamedCaches().entrySet()) {
				String cacheName = entry.getKey();
				this.registerNeutrinoCache(cacheName, new NeutrinoCacheInfinispan(cacheName, entry.getValue(),
						embeddedCacheManager.getCache(cacheName)));
			}
		}

	}

}
