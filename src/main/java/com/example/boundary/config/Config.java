package com.example.boundary.config;

import com.fasterxml.jackson.annotation.JsonValue;

public class Config {
	
	private static final String DEFAULT_BASE_PATH = "/var/docker-integration";
	
	private static final String DEFAULT_CONFIG_PATH = DEFAULT_BASE_PATH + "/config.json";
	
	private static final String DEFAULT_WORK_PATH = DEFAULT_BASE_PATH + "/work";

	private IntegrationStrategy strategy = IntegrationStrategy.FILE;

	private Notification notification = new Notification();

	public IntegrationStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(IntegrationStrategy strategy) {
		this.strategy = strategy;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public String getWorkPath() {
		return DEFAULT_WORK_PATH;
	}

	public static String getDefaultConfigPath() {
		return DEFAULT_CONFIG_PATH;
	}

	public static enum IntegrationStrategy {

		FILE("file");

		private final String name;

		IntegrationStrategy(String name) {
			this.name = name;
		}

		@JsonValue
		public String getName() {
			return name;
		}

	}

}
