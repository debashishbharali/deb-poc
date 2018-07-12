package com.nucleus.cache;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public abstract class NeutrinoCache {

	private NeutrinoCachePopulator neutrinoCachePopulator;
	private String neutrinoCacheName;

	protected NeutrinoCache(String neutrinoCacheName, NeutrinoCachePopulator neutrinoCachePopulator) {
		this.neutrinoCacheName = neutrinoCacheName;
		this.neutrinoCachePopulator = neutrinoCachePopulator;
	}

	public final NeutrinoCachePopulator getNeutrinoCachePopulator() {
		return neutrinoCachePopulator;
	}

	protected final String getNeutrinoCacheName() {
		return neutrinoCacheName;
	}

	public abstract Object get(Object key);

	public abstract void put(Object key, Object value);

	public abstract Set<Object> keySet();

	public abstract void clear();

	public abstract void putAll(Map<Object, Object> map);

	public abstract int size();

	public abstract boolean containsKey(Object key);

	public abstract boolean isEmpty();

	public abstract Object putIfAbsent(Object key, Object value);

	public abstract void remove(Object key);

	public abstract Set<Entry<Object, Object>> entrySet();

	public abstract String getCacheAsJson(ObjectMapper mapperObj)
			throws JsonGenerationException, JsonMappingException, IOException;

	public final void build() {
		this.neutrinoCachePopulator.build(this);
	}
}
