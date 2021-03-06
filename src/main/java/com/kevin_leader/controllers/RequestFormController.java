package com.kevin_leader.controllers;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.kevin_leader.models.Event;
import com.kevin_leader.models.EventType;
import com.kevin_leader.models.GradingFormat;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.models.RequestForm;
import com.kevin_leader.services.RequestFormService;

import io.javalin.http.Handler;

public class RequestFormController {

    private static final Logger log = Logger
            .getLogger(RequestFormController.class);
    private RequestFormService rfServ;
    private Gson gson;

    public RequestFormController(RequestFormService rfServ) {
        this.rfServ = rfServ;
        gson = new Gson();
    }

    public Handler getFutureEvents = (context) -> {
        log.info("Start Handler getFutureEvents");

        List<Event> events = rfServ.getFutureEvents();

        if (events != null) {
            context.result(gson.toJson(events));
            context.status(200);
        } else {
            context.result("{}");
            context.status(500);
        }

    };

    public Handler getAllEventTypes = (context) -> {
        log.info("Start Handler getAllEventTypes");

        List<EventType> types = rfServ.getAllEventTypes();
        if (types != null) {
            context.result(gson.toJson(types));
            context.status(200);
        } else {
            context.result("{}");
            context.status(500);
        }
    };

    public Handler getAllGradingFormats = (context) -> {
        log.info("Start Handler getAllGradingFormats");

        List<GradingFormat> formats = rfServ.getAllGradingFormats();
        if (formats != null) {
            context.result(gson.toJson(formats));
            context.status(200);
        } else {
            context.result("{}");
            context.status(500);
        }
    };

    public Handler processReimbursementRequest = (context) -> {
        log.info("Start Handler processReimbursementRequest");

        RequestForm reqForm = null;
        try {
            reqForm = gson.fromJson(context.body(), RequestForm.class);
        } catch (NumberFormatException e) {
            log.warn(e);
        }

        Reimbursement reimbursement = rfServ.processRequestForm(reqForm);

        if (reimbursement != null) {
            context.result(gson.toJson(reimbursement));
            context.status(201);
        } else {
            context.result("{}");
            context.status(400);
        }
    };

}
