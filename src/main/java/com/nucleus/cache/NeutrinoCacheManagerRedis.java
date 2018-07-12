package com.nucleus.cache;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.redisson.api.RedissonClient;
import org.redisson.api.TransactionOptions;

public class NeutrinoCacheManagerRedis extends NeutrinoCacheManager {

	private RedissonClient redisson;
	private TransactionOptions redissonTransactionOptions;

	private Long syncSlavesTimeout;
	private Long responseTimeout;
	private Long retryInterval;
	private Integer retryAttempts;
	private Long timeout;

	public NeutrinoCacheManagerRedis(RedissonClient redisson, Long syncSlavesTimeout, Long responseTimeout,
			Long retryInterval, Integer retryAttempts, Long timeout) {
		this.redisson = redisson;
		this.syncSlavesTimeout = syncSlavesTimeout;
		this.responseTimeout = responseTimeout;
		this.retryInterval = retryInterval;
		this.retryAttempts = retryAttempts;
		this.timeout = timeout;
	}

	@PostConstruct
	public final void postConstruct() {
		redissonTransactionOptions = TransactionOptions.defaults()
				.syncSlavesTimeout(syncSlavesTimeout, TimeUnit.SECONDS)
				.responseTimeout(responseTimeout, TimeUnit.SECONDS).retryInterval(retryInterval, TimeUnit.SECONDS)
				.retryAttempts(retryAttempts).timeout(timeout, TimeUnit.SECONDS);
	}

	@Override
	protected final void registerNeutrinoCache() {
		for (NeutrinoCacheRegion neutrinoCacheRegion : getNeutrinoCacheRegionSet()) {
			for (Map.Entry<String, NeutrinoCachePopulator> entry : neutrinoCacheRegion.getNamedCaches().entrySet()) {
				this.registerNeutrinoCache(entry.getKey(),
						new NeutrinoCacheRedis(entry.getKey(), entry.getValue(), redisson, redissonTransactionOptions));
			}
		}

	}

}
