package com.nucleus.cache;

import javax.annotation.PostConstruct;

public abstract class NeutrinoCachePopulator {

	@PostConstruct
	public abstract void init();

	public abstract Object fallback(Object key);

	public abstract void build(NeutrinoCache neutrinoCache);

}
