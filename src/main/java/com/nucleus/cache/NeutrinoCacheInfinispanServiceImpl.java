package com.nucleus.cache;

import java.util.Map;

import javax.inject.Named;

import org.infinispan.Cache;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Named("neutrinoCacheInfinispanService")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NeutrinoCacheInfinispanServiceImpl implements NeutrinoCacheInfinispanService {

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void put(Cache<Object, Object> cache, Object key, Object value) {
		cache.put(key, value);

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Object putIfAbsent(Cache<Object, Object> cache, Object key, Object value) {
		return cache.putIfAbsent(key, value);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void remove(Cache<Object, Object> cache, Object key) {
		cache.remove(key);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void clear(Cache<Object, Object> cache) {
		cache.clear();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void putAll(Cache<Object, Object> cache, Map<Object, Object> map) {
		cache.putAll(map);
	}

}
