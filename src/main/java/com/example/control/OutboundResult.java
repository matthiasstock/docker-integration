package com.example.control;

import com.example.boundary.config.Config.IntegrationStrategy;

public class OutboundResult {

	private final IntegrationStrategy integrationStrategy;

	private String meetingPoint;

	public OutboundResult(IntegrationStrategy integrationStrategy) {
		this.integrationStrategy = integrationStrategy;
	}

	public IntegrationStrategy getIntegrationStrategy() {
		return integrationStrategy;
	}

	public String getMeetingPoint() {
		return meetingPoint;
	}

	public void setMeetingPoint(String meetingPoint) {
		this.meetingPoint = meetingPoint;
	}
	
}
