package com.nucleus.cache;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.infinispan.Cache;
import org.infinispan.context.Flag;

import com.nucleus.core.NeutrinoSpringAppContextUtil;

public class NeutrinoCacheInfinispan extends NeutrinoCache {

	private Cache<Object, Object> cache;
	private NeutrinoCacheInfinispanService neutrinoCacheInfinispanService;

	public NeutrinoCacheInfinispan(String neutrinoCacheName, NeutrinoCachePopulator neutrinoCachePopulator,
			Cache<Object, Object> cache) {
		super(neutrinoCacheName, neutrinoCachePopulator);
		this.neutrinoCacheInfinispanService = NeutrinoSpringAppContextUtil
				.getBeanByName("neutrinoCacheInfinispanService", NeutrinoCacheInfinispanService.class);
		this.cache = cache.getAdvancedCache().withFlags(Flag.SKIP_CACHE_LOAD, Flag.FAIL_SILENTLY);
	}

	public final void put(Object key, Object value) {
		this.neutrinoCacheInfinispanService.put(cache, key, value);
	}

	@Override
	public final Object get(Object key) {
		Object value = this.cache.get(key);
		if (value != null) {
			return value;
		}

		if (!cache.containsKey(key)) {
			value = this.getNeutrinoCachePopulator().fallback(key);
			this.put(key, value);
		}

		return value;
	}

	@Override
	public Set<Object> keySet() {
		return this.cache.keySet();
	}

	@Override
	public Object putIfAbsent(Object key, Object value) {
		return this.neutrinoCacheInfinispanService.putIfAbsent(this.cache, key, value);
	}

	@Override
	public void remove(Object key) {
		this.neutrinoCacheInfinispanService.remove(cache, key);
	}

	@Override
	public Set<Map.Entry<Object, Object>> entrySet() {
		return this.cache.entrySet();
	}

	@Override
	public boolean containsKey(Object key) {
		return this.cache.containsKey(key);
	}

	@Override
	public boolean isEmpty() {
		return this.cache.isEmpty();
	}

	@Override
	public void clear() {
		this.neutrinoCacheInfinispanService.clear(this.cache);
	}

	@Override
	public void putAll(Map<Object, Object> map) {
		this.neutrinoCacheInfinispanService.putAll(this.cache, map);
	}

	@Override
	public int size() {
		return this.cache.size();
	}

	@Override
	public String getCacheAsJson(ObjectMapper mapperObj)
			throws JsonGenerationException, JsonMappingException, IOException {
		return mapperObj.writeValueAsString(this.cache);
	}

}
