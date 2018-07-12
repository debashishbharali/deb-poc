package com.nucleus.core;

import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nucleus.cache.event.CacheEventPublisher;
import com.nucleus.deb.service.DebService;

@Named("genericContextListener")
public class GenericContextListener implements ServletContextListener {

	private static ApplicationContext appCtx;

	@Override
	public void contextDestroyed(ServletContextEvent paramServletContextEvent) {
		System.out.println("GenericContextListener contextDestroyed()");

	}

	@Override
	public void contextInitialized(ServletContextEvent paramServletContextEvent) {
		this.setApplicationContext(paramServletContextEvent.getServletContext());
		this.publishFwCache();
	}

	private void setApplicationContext(ServletContext servletConext) {
		appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletConext);
	}

	private void publishFwCache() {

		System.out.println(" Start Publishing CacheEvent from GenericContextListener ");
		DebService debService = (DebService) appCtx.getBean("debService");
		Long tenantId = debService.getRandomTenantId();

		CacheEventPublisher cacheEventPublisher = (CacheEventPublisher) appCtx.getBean("cacheEventPublisher");
		System.out.println(" Publishing Cache Map from GenericContextListener with Tenant Id: " + tenantId.longValue());
		cacheEventPublisher.publish(tenantId);

		System.out.println(" Finished Publishing CacheEvent ");

	}

}
