package com.example.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.boundary.Task;
import com.example.boundary.config.Config;
import com.example.boundary.config.Notification;

@Service
public class OutboundService {

	@Autowired
	private ConfigService configService;

	public void forward(Task task) {
		Config config = configService.getConfig();
		Notification notification = config.getNotification();
		OutboundStrategy outboundStrategy;
		NotificationStrategy notificationStrategy;
		switch (notification.getType()) {
		case SHELL:
		default:
			notificationStrategy = new ShellNotificationStrategy(config.getNotification());
			break;
		}
		switch (config.getStrategy()) {
		case FILE:
		default:
			outboundStrategy = new FileOutboundStrategy(config, notificationStrategy);
			break;
		}
		outboundStrategy.forward(task);
	}

}
