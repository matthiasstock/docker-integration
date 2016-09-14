package com.example.control;

import com.example.boundary.OutboundException;
import com.example.boundary.Task;

public interface OutboundStrategy {

	void forward(Task task) throws OutboundException;

}
