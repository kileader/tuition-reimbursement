package com.kevin_leader.controllers;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.kevin_leader.models.Attachment;
import com.kevin_leader.models.Message;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.services.ReviewRequestService;

import io.javalin.http.Handler;

public class ReviewRequestController {

    private static final Logger log = Logger
            .getLogger(ReviewRequestController.class);
    private ReviewRequestService rrServ;
    private Gson gson;

    public ReviewRequestController(ReviewRequestService rrServ) {
        this.rrServ = rrServ;
        gson = new Gson();
    }

    public Handler getReimbursementsForReviewer = (context) -> {
        log.info("Start Handler getReimbursementsForReviewer");

        int empId = Integer.valueOf(context.pathParam("empId"));

        List<Reimbursement> reimbursements = rrServ
                .getReimbursementsForReviewer(empId);

        if (reimbursements != null) {
            context.result(gson.toJson(reimbursements));
            context.status(200);
        } else {
            context.result("{}");
            context.status(404);
        }
    };

    public Handler getMessagesForReimbursement = (context) -> {
        log.info("Start Handler getMessagesForReimbursement");

        int rId = Integer.valueOf(context.pathParam("rId"));

        List<Message> messages = rrServ.getMessagesForReimbursement(rId);

        if (messages != null) {
            context.result(gson.toJson(messages));
            context.status(200);
        } else {
            context.result("{}");
            context.status(400);
        }
    };

    public Handler getAttachmentsForReimbursement = (context) -> {
        log.info("Start Handler getAttachmentsForReimbursement");

        int rId = Integer.valueOf(context.pathParam("rId"));

        List<Attachment> attachments = rrServ
                .getAttachmentsForReimbursement(rId);

        if (attachments != null) {
            context.result(gson.toJson(attachments));
            context.status(200);
        } else {
            context.result("{}");
            context.status(400);
        }
    };

    public Handler incrementStepAndAddMessage = (context) -> {
        log.info("Start Handler incrementStepAndAddMessage");

        Message message = gson.fromJson(context.body(), Message.class);

        Message messageAdded = rrServ.incrementStepAndAddMessage(message);

        if (messageAdded != null) {
            context.result(gson.toJson(messageAdded));
            context.status(201);
        } else {
            context.result("{}");
            context.status(400);
        }
    };

}
