package com.kevin_leader.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.kevin_leader.models.Attachment;
import com.kevin_leader.models.Message;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.repositories.GenericRepo;

public class ReviewRequestServiceImpl implements ReviewRequestService {

    public static final Logger log = Logger
            .getLogger(ReviewRequestServiceImpl.class);
    private GenericRepo<Attachment> aDao;
    private GenericRepo<Message> mDao;
    private GenericRepo<Reimbursement> rDao;
    private List<Message> allMessages;
    private List<Attachment> allAttachments;

    public ReviewRequestServiceImpl(GenericRepo<Attachment> aDao,
            GenericRepo<Message> mDao, GenericRepo<Reimbursement> rDao) {
        log.info("Instatiate ReviewRequestServiceImpl");
        this.aDao = aDao;
        this.mDao = mDao;
        this.rDao = rDao;
        allMessages = new ArrayList<>();
        allAttachments = new ArrayList<>();
    }

    @Override
    public List<Reimbursement> getReimbursementsForReviewer(int empId) {
        log.info("Run getReimbursementsForReviewer(" + empId + ")");

        List<Reimbursement> reimbsForReview = new ArrayList<>();
        List<Reimbursement> allReimbursements = rDao.getAll();

        for (Reimbursement reimb : allReimbursements) {
            int step = reimb.getApprovalStep();
            if (step != 5 && step != -1) {
                log.info("reimbursement = " + reimb.toString());

                if ((step == 2 || step == 4)
                        && reimb.getReimbursee().getBenCoEmpId() == empId) {
                    log.info("Need ben co approval");
                    reimbsForReview.add(reimb);

                } else if (step == 1
                        && reimb.getReimbursee().getDepHeadEmpId() == empId) {
                    log.info("Need dep head approval");
                    reimbsForReview.add(reimb);

                } else if (step == 0 && reimb.getReimbursee()
                        .getSupervisorEmpId() == empId) {
                    log.info("Need direct super approval");
                    reimbsForReview.add(reimb);
                }
            }
        }

        loadAllAttachments();
        loadAllMessages();

        return reimbsForReview;
    }

    public void loadAllAttachments() {
        allAttachments = aDao.getAll();
    }

    public void loadAllMessages() {
        allMessages = mDao.getAll();
    }

    @Override
    public List<Attachment> getAttachmentsForReimbursement(int rId) {

        List<Attachment> foundAttachments = new ArrayList<>();

        for (Attachment attachment : allAttachments) {
            if (attachment.getReimbursement().getId() == rId) {
                foundAttachments.add(attachment);
            }
        }
        return foundAttachments;
    }

    @Override
    public List<Message> getMessagesForReimbursement(int rId) {

        List<Message> foundMessages = new ArrayList<>();

        for (Message message : allMessages) {
            if (message.getReimbursement().getId() == rId) {
                foundMessages.add(message);
            }
        }
        return foundMessages;
    }

    @Override
    public Message incrementStepAndAddMessage(Message message) {

        log.info("Run incrementStepAndAddMessage()");

        int reimbId = message.getReimbursement().getId();
        Reimbursement reimb = rDao.getById(reimbId);

        log.info(message.getMessageType());

        if (message.getMessageType().equals("Denial")) {

            reimb.setApprovalStep(-1);

        } else if (message.getMessageType().equals("Approval")) {

            if (reimb.getApprovalStep() == 2) {

                reimb.setApprovalStep(3);

            } else if (reimb.getApprovalStep() == 1) {

                reimb.setApprovalStep(2);

            } else if (reimb.getApprovalStep() == 0) {

                reimb.setApprovalStep(1);
            }
        }

        rDao.update(reimb);
        message.setReimbursement(reimb);

        log.info(message.getReimbursement().getApprovalStep());

        Date dateTime = new Date();
        long currentTime = dateTime.getTime();

        message.setTimeSent(currentTime);

        int id = mDao.add(message);
        message.setId(id);

        return message;

    }
}
