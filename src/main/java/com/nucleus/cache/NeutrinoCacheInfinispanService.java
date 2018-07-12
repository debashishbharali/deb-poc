package com.nucleus.cache;

import java.util.Map;

import org.infinispan.Cache;

public interface NeutrinoCacheInfinispanService {

	void put(Cache<Object, Object> cache, Object key, Object value);

	Object putIfAbsent(Cache<Object, Object> cache, Object key, Object value);

	void remove(Cache<Object, Object> cache, Object key);

	void clear(Cache<Object, Object> cache);

	void putAll(Cache<Object, Object> cache, Map<Object, Object> map);

}
