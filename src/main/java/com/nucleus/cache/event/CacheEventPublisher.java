package com.nucleus.cache.event;

import javax.inject.Named;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

@Named("cacheEventPublisher")
public class CacheEventPublisher implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher publisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

	public void publish(Long tenantId) {
		CacheEvent cacheEvent = new CacheEvent(this);
		cacheEvent.setTenantId(tenantId);
		publisher.publishEvent(cacheEvent);

	}
}
