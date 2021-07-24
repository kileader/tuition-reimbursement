package com.kevin_leader.controllers;

import org.apache.log4j.Logger;

import com.kevin_leader.services.AttachmentService;

public class AttachmentController {
	
	private static final Logger log =
			Logger.getLogger(AttachmentController.class);
	private AttachmentService aServ;
	
	public AttachmentController(AttachmentService aServ) {
		log.info("Instantiate AttachmentController");
		this.aServ = aServ;
	}
	
}
