package com.example.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.control.OutboundService;

@RestController
public class InboundController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InboundController.class);
	
	@Autowired
	private OutboundService outboundService;

	@RequestMapping(path = "/task", method = RequestMethod.POST)
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		LOGGER.info("Received a new task: {}", task);
		outboundService.forward(task);
		return new ResponseEntity<>(task, HttpStatus.OK);
	}

}
