package com.example.control;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.boundary.config.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ConfigService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigService.class);

	@Autowired
	private ObjectMapper objectMapper;

	private File configFile;

	@PostConstruct
	public void init() {
		String configPath = System.getenv("INTEGRATION_CONFIG_PATH");
		String checkedConfigPath = StringUtils.defaultIfBlank(configPath, Config.getDefaultConfigPath());
		configFile = new File(checkedConfigPath);
	}

	public Config getConfig() {
		Config config;
		try {
			config = objectMapper.readValue(configFile, Config.class);
		} catch (IOException e) {
			LOGGER.error("could not read config file {}", configFile.getAbsolutePath());
			LOGGER.debug(e.toString(), e);
			config = new Config();
		}
		return config;
	}

}
