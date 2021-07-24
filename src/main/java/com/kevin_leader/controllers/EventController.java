package com.kevin_leader.controllers;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.kevin_leader.models.Event;
import com.kevin_leader.services.EventService;

import io.javalin.http.Handler;

public class EventController {
	
	private static final Logger log =
			Logger.getLogger(EventController.class);
	private EventService evServ;
	private Gson gson;
	
	public EventController(EventService evServ) {
		log.info("Instantiate EventController");
		this.evServ = evServ;
		gson = new Gson();
	}
	
//	public Handler getActorById = (context) -> {
//
//		String input = context.pathParam("id");
//		int id;
//		try {
//			id = Integer.parseInt(input);
//		} catch (NumberFormatException e) {
//			id = -1;
//		}
//		Actor a = ar.getActor(id);
//
//		populateResult(context, a);
//	};
//
//	public Handler addActor = (context) -> {
//
//		Actor a = gson.fromJson(context.body(), Actor.class);
//
//		a = ar.addActor(a);
//
//		populateResult(context, a);
//	};

	public Handler getAllEvents = (context) -> {
		log.info("Start Handler getAllEvents");

		List<Event> events = evServ.getAll();
		if (events != null) {
			context.result(gson.toJson(events));
			context.status(200);
		} else {
			context.result("[]");
			context.status(404);
		}

	};

//	public Handler updateActor = (context) -> {
//		Actor a = gson.fromJson(context.body(), Actor.class);
//		a.setId(Integer.parseInt(context.pathParam("id")));
//
//		a = ar.updateActor(a);
//
//		populateResult(context, a);
//	};
//
//	public Handler deleteActor = (context) -> {
//
//		int id = Integer.parseInt(context.pathParam("id"));
//
//		Actor a = ar.deleteActor(id);
//
//		populateResult(context, a);
//	};
//
//	private void getActorByName(Context context) {
//
//		String name = context.queryParam("name");
//		System.out.println(name);
//
//		Actor a = ar.getActor(name);
//
//		populateResult(context, a);
//	};
//
//	// Helper Method
//	private void populateResult(Context context, Event event) {
//		if (event != null) {
//			context.result(gson.toJson(event));
//		} else {
//			context.result("{}");
//		}
//	}
	
}
