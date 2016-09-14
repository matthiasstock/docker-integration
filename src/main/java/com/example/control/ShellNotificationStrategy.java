package com.example.control;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.boundary.OutboundException;
import com.example.boundary.config.Config.IntegrationStrategy;
import com.example.boundary.config.Notification;

public class ShellNotificationStrategy implements NotificationStrategy {

	private static final int NORMAL_TERMINATION = 0;
	private static final Logger LOGGER = LoggerFactory.getLogger(ShellNotificationStrategy.class);
	
	private final Notification notification;

	public ShellNotificationStrategy(Notification notification) {
		this.notification = notification;
	}

	@Override
	public void notify(OutboundResult outboundResult) {
		String parsedAction = parseAction(outboundResult);
		try {
			LOGGER.info("Running {}", parsedAction);
			Process exec = Runtime.getRuntime().exec(parsedAction);
			exec.waitFor(1L, TimeUnit.MINUTES);
			if (exec.exitValue() != NORMAL_TERMINATION) {
				InputStream errorStream = exec.getErrorStream();
				String errorFromShell = IOUtils.toString(errorStream);
				IOUtils.closeQuietly(errorStream);
				throw new OutboundException(errorFromShell);
			}
		} catch (IOException | InterruptedException e) {
			throw new OutboundException(e);
		}
	}

	private String parseAction(OutboundResult outboundResult) {
		IntegrationStrategy strategy = outboundResult.getIntegrationStrategy();
		String parsed = outboundResult.getMeetingPoint();
		switch(strategy) {
		case FILE:
			parsed = StringUtils.replace(notification.getAction(), "${file}", outboundResult.getMeetingPoint());
			break;
		}
		return parsed;
	}

}
