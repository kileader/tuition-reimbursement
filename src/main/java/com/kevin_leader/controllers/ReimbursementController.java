package com.kevin_leader.controllers;

import org.apache.log4j.Logger;

import com.kevin_leader.services.ReimbursementService;

public class ReimbursementController {

	private static final Logger log =
			Logger.getLogger(ReimbursementController.class);
	private ReimbursementService rServ;
	
	public ReimbursementController(ReimbursementService rServ) {
		log.info("Instantiate ReimbursementController");
		this.rServ = rServ;
	}
	
}
