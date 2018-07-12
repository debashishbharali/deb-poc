package com.nucleus.cache;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.infinispan.util.concurrent.ConcurrentHashSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;

import com.nucleus.cache.event.CacheEvent;

public abstract class NeutrinoCacheManager implements ApplicationListener<CacheEvent> {

	@Value(value = "${custom.cache.enabled}")
	private Boolean isCustomCacheEnabled;

	private final Set<NeutrinoCacheRegion> neutrinoCacheRegionSet = new ConcurrentHashSet<>();
	private final Map<String, NeutrinoCache> namedCacheMap = new ConcurrentHashMap<>();

	public final Boolean getIsCustomCacheEnabled() {
		return isCustomCacheEnabled;
	}

	public final void setIsCustomCacheEnabled(Boolean isCustomCacheEnabled) {
		this.isCustomCacheEnabled = isCustomCacheEnabled;
	}

	public final Set<NeutrinoCacheRegion> getNeutrinoCacheRegionSet() {
		return neutrinoCacheRegionSet;
	}

	public final NeutrinoCache getNeutrinoCacheInstance(String cacheName) {
		if (cacheName == null || cacheName.isEmpty()) {
			return null;
		}
		return namedCacheMap.get(cacheName);
	}

	protected final void registerNeutrinoCache(String cacheName, NeutrinoCache neutrinoCache) {
		namedCacheMap.put(cacheName, neutrinoCache);
	}

	public final void registerNeutrinoCacheRegion(NeutrinoCacheRegion neutrinoCacheRegion) {
		neutrinoCacheRegionSet.add(neutrinoCacheRegion);
	}

	@Override
	public final void onApplicationEvent(CacheEvent event) {
		this.registerNeutrinoCache();
		if (isCustomCacheEnabled) {
			for (String namedCache : namedCacheMap.keySet()) {
				namedCacheMap.get(namedCache).build();
			}
		}
	}

	protected abstract void registerNeutrinoCache();

}
