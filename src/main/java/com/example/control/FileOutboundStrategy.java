package com.example.control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.boundary.OutboundException;
import com.example.boundary.Task;
import com.example.boundary.config.Config;
import com.example.boundary.config.Config.IntegrationStrategy;

public class FileOutboundStrategy implements OutboundStrategy {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileOutboundStrategy.class);

	private static final String PREFIX = "input";
	private static final String SUFFIX = ".tmp";

	private final NotificationStrategy notificationStrategy;

	private final Config config;

	public FileOutboundStrategy(Config config, NotificationStrategy notificationStrategy) {
		this.config = config;
		this.notificationStrategy = notificationStrategy;
	}

	@Override
	public void forward(Task task) {
		FileWriter writer = null;
		File file;
		OutboundResult outboundResult = new OutboundResult(IntegrationStrategy.FILE);
		try {
			File workdir = new File(config.getWorkPath());
			file = File.createTempFile(PREFIX, SUFFIX, workdir);
			outboundResult.setMeetingPoint(file.getAbsolutePath());
			LOGGER.info("Writing to file {}", file.getAbsolutePath());
			writer = new FileWriter(file);
			IOUtils.write(task.getInput(), writer);
			writer.flush();
		} catch (IOException e) {
			throw new OutboundException(e);
		} finally {
			IOUtils.closeQuietly(writer);
		}
		notificationStrategy.notify(outboundResult);
	}

}
