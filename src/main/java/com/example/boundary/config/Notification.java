package com.example.boundary.config;

import com.fasterxml.jackson.annotation.JsonValue;

public class Notification {

	private static final String NOP = ":";

	private Type type = Type.SHELL;

	private String action = NOP;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public static enum Type {

		SHELL("shell");

		private final String name;

		Type(String name) {
			this.name = name;
		}

		@JsonValue
		public String getName() {
			return name;
		}

	}
}
