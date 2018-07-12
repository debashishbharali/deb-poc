package com.nucleus.cache;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.redisson.api.RMap;
import org.redisson.api.RTransaction;
import org.redisson.api.RedissonClient;
import org.redisson.api.TransactionOptions;

public class NeutrinoCacheRedis extends NeutrinoCache {

	private RedissonClient redisson;
	private TransactionOptions redissonTransactionOptions;

	public NeutrinoCacheRedis(String neutrinoCacheName, NeutrinoCachePopulator neutrinoCachePopulator,
			RedissonClient redisson, TransactionOptions redissonTransactionOptions) {
		super(neutrinoCacheName, neutrinoCachePopulator);
		this.redissonTransactionOptions = redissonTransactionOptions;
		this.redisson = redisson;
		this.redisson.getMap(neutrinoCacheName);
	}

	@Override
	public final void put(Object key, Object value) {
		RTransaction transaction = redisson.createTransaction(redissonTransactionOptions);
		RMap<Object, Object> cache = transaction.getMap(this.getNeutrinoCacheName());
		cache.fastPut(key, value);
		transaction.commit();
	}

	@Override
	public final Object get(Object key) {
		RMap<Object, Object> cache = redisson.getMap(this.getNeutrinoCacheName());
		Object value = cache.get(key);
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
	public final void clear() {
		RTransaction transaction = redisson.createTransaction(redissonTransactionOptions);
		RMap<Object, Object> cache = transaction.getMap(this.getNeutrinoCacheName());
		cache.clear();
		transaction.commit();
	}

	@Override
	public Set<Object> keySet() {
		RMap<Object, Object> cache = redisson.getMap(this.getNeutrinoCacheName());
		return cache.keySet();
	}

	@Override
	public void putAll(Map<Object, Object> map) {
		RTransaction transaction = redisson.createTransaction(redissonTransactionOptions);
		RMap<Object, Object> cache = transaction.getMap(this.getNeutrinoCacheName());
		cache.putAll(map);
		transaction.commit();

	}

	@Override
	public int size() {
		RMap<Object, Object> cache = redisson.getMap(this.getNeutrinoCacheName());
		return cache.size();
	}

	@Override
	public boolean containsKey(Object key) {
		RMap<Object, Object> cache = redisson.getMap(this.getNeutrinoCacheName());
		return cache.containsKey(key);
	}

	@Override
	public boolean isEmpty() {
		RMap<Object, Object> cache = redisson.getMap(this.getNeutrinoCacheName());
		return cache.isEmpty();
	}

	@Override
	public Object putIfAbsent(Object key, Object value) {
		RMap<Object, Object> cache = redisson.getMap(this.getNeutrinoCacheName());
		return cache.fastPutIfAbsent(key, value);
	}

	@Override
	public void remove(Object key) {
		RTransaction transaction = redisson.createTransaction(redissonTransactionOptions);
		RMap<Object, Object> cache = transaction.getMap(this.getNeutrinoCacheName());
		cache.fastRemove(key);
		transaction.commit();
	}

	@Override
	public Set<Entry<Object, Object>> entrySet() {
		RMap<Object, Object> cache = redisson.getMap(this.getNeutrinoCacheName());
		return cache.entrySet();
	}

	@Override
	public String getCacheAsJson(ObjectMapper mapperObj)
			throws JsonGenerationException, JsonMappingException, IOException {
		RMap<Object, Object> cache = redisson.getMap(this.getNeutrinoCacheName());
		return mapperObj.writeValueAsString(cache);
	}

}
