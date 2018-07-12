package com.nucleus.core;

import java.util.Map;

import javax.inject.Named;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Named
public class NeutrinoSpringAppContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		NeutrinoSpringAppContextUtil.applicationContext = applicationContext;
	}

	public static <T> T getBeanByName(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}

	public static <T> T getBeanByType(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> requiredType) {
		return applicationContext.getBeansOfType(requiredType);
	}
}
