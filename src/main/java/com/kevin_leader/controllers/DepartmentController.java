package com.kevin_leader.controllers;

import org.apache.log4j.Logger;

import com.kevin_leader.services.DepartmentService;

public class DepartmentController {
	
	private static final Logger log =
			Logger.getLogger(DepartmentController.class);
	private DepartmentService dServ;
	
	public DepartmentController(DepartmentService dServ) {
		log.info("Instantiate DepartmentController");
		this.dServ = dServ;
	}
	
}
