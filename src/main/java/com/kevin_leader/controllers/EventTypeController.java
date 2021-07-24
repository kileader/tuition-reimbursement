package com.kevin_leader.controllers;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.kevin_leader.models.EventType;
import com.kevin_leader.services.EventTypeService;

import io.javalin.http.Handler;

public class EventTypeController {

	private static final Logger log =
			Logger.getLogger(EventTypeController.class);
	private EventTypeService etServ;
	private Gson gson;
	
	public EventTypeController(EventTypeService etServ) {
		log.info("Instantiate EventTypeController");
		this.etServ = etServ;
	}
	
	public Handler getAllEventTypes = (context) -> {
		log.info("Start Handler getAllEventTypes");
		
		List<EventType> types = etServ.getAll();
		if (types != null) {
			context.result(gson.toJson(types));
			context.status(200);
		} else {
			context.result("[]");
			context.status(404);
		}
	};
	
}
