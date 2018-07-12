package com.nucleus.cache.event;

import org.springframework.context.ApplicationEvent;

public class CacheEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private Long tenantId;

	public CacheEvent(Object source) {
		super(source);
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

}
