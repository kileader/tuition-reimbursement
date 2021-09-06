package com.kevin_leader.controllers;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.kevin_leader.models.Attachment;
import com.kevin_leader.models.Message;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.services.UpdateRequestService;

import io.javalin.http.Handler;

public class UpdateRequestController {

    private static final Logger log = Logger
            .getLogger(UpdateRequestController.class);
    private UpdateRequestService urServ;
    private Gson gson;

    public UpdateRequestController(UpdateRequestService urServ) {
        this.urServ = urServ;
        gson = new Gson();
    }

    public Handler getReimbursementsByEmployeeId = (context) -> {
        log.info("Start Handler getReimbursementsByEmployeeId");

        int empId = 0;
        try {
            empId = Integer.valueOf(context.pathParam("empId"));
        } catch (NumberFormatException e) {
            log.warn(e);
        }

        List<Reimbursement> reimbursements = urServ
                .getReimbursementsByEmployeeId(empId);

        if (reimbursements != null) {
            context.result(gson.toJson(reimbursements));
            context.status(200);
        } else {
            context.result("{}");
            context.status(404);
        }

    };

    public Handler getAttachmentsByEmployeeId = (context) -> {
        log.info("Start Handler getAttachmentsByEmployeeId");
        
        int empId = Integer.valueOf(context.pathParam("empId"));
        
        List<Attachment> attachments = urServ.getAttachmentsByEmployeeId(empId);
        
        if (attachments != null) {
            context.result(gson.toJson(attachments));
            context.status(200);
        } else {
            context.result("{}");
            context.status(404);
        }
        
    };


    public Handler getMessagesForEmployee = (context) -> {
        log.info("Start Handler getMessagesForEmployee");

        int empId = Integer.valueOf(context.pathParam("empId"));

        List<Message> messages = urServ.getMessagesForEmployee(empId);

        if (messages != null) {
            context.result(gson.toJson(messages));
            context.status(200);
        } else {
            context.result("{}");
            context.status(404);
        }
    };

    public Handler addAttachment = (context) -> {
        log.info("Start Handler addAttachment");

        Attachment attachment = gson.fromJson(context.body(), Attachment.class);
        log.info(attachment.toString());
        
        Attachment addedAttachment = urServ.addAttachment(attachment);
//        log.info(updatedAttachment.toString());
        
        if (addedAttachment != null) {
            context.result(gson.toJson(addedAttachment));
            context.status(201);
        } else {
            context.result("{}");
            context.status(400);
        }
    };
    
    public Handler updateReimbursement = (context) -> {
        log.info("Start Handler updateReimbursement");
        
        Reimbursement reimbursement = gson.fromJson(context.body(), Reimbursement.class);
        Reimbursement updatedReimbursement = urServ.updateReimbursement(reimbursement);
        
        if (updatedReimbursement != null) {
            context.result(gson.toJson(updatedReimbursement));
            context.status(200);
        } else {
            context.result("{}");
            context.status(400);
        }
        
    };

}
