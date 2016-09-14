package com.example.boundary;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Task {

	private static final int TO_STRING_MAX_LENGTH = 32;
	private String input;

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("input", StringUtils.abbreviate(input, TO_STRING_MAX_LENGTH))
				.toString();
	}

}
