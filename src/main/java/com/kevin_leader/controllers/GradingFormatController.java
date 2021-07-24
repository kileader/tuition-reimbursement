package com.kevin_leader.controllers;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.kevin_leader.models.GradingFormat;
import com.kevin_leader.services.GradingFormatService;

import io.javalin.http.Handler;

public class GradingFormatController {

	private static final Logger log =
			Logger.getLogger(GradingFormatController.class);
	private GradingFormatService gfServ;
	private Gson gson;
	
	public GradingFormatController(GradingFormatService gfServ) {
		log.info("Instantiate GradingFormatController");
		this.gfServ = gfServ;
		gson = new Gson();
	}
	
	public Handler getAllGradingFormats = (context) -> {
		log.info("Start Handler getAllGradingFormats");
		
		List<GradingFormat> formats = gfServ.getAll();
		if (formats != null) {
			context.result(gson.toJson(formats));
			context.status(200);
		} else {
			context.result("[]");
			context.status(404);
		}
	};
	
}
