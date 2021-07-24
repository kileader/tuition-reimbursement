package com.kevin_leader.controllers;

import org.apache.log4j.Logger;

import com.kevin_leader.services.MessageService;

public class MessageController {

	private static final Logger log =
			Logger.getLogger(MessageController.class);
	private MessageService mServ;
	
	public MessageController(MessageService mServ) {
		log.info("Instantiate MessageController");
		this.mServ = mServ;
	}
	
}
