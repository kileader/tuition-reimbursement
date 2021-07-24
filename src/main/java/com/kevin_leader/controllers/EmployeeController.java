package com.kevin_leader.controllers;

import org.apache.log4j.Logger;

import com.kevin_leader.services.EmployeeService;

public class EmployeeController {
	
	private static final Logger log =
			Logger.getLogger(EmployeeController.class);
	private EmployeeService empServ;
	
	public EmployeeController(EmployeeService empServ) {
		log.info("Instantiate EmployeeController");
		this.empServ = empServ;
	}
	
}
