package com.kevin_leader.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kevin_leader.models.Attachment;
import com.kevin_leader.models.Message;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.repositories.GenericRepo;

public class UpdateRequestServiceImpl implements UpdateRequestService {

    public static final Logger log = Logger.getLogger(UpdateRequestServiceImpl.class);
    private GenericRepo<Attachment> aDao;
    private GenericRepo<Message> mDao;
    private GenericRepo<Reimbursement> rDao;
    
    public UpdateRequestServiceImpl(GenericRepo<Attachment> aDao,
            GenericRepo<Message> mDao, GenericRepo<Reimbursement> rDao) {
        log.info("Instantiate UpdateServiceImpl");
        this.aDao = aDao;
        this.mDao = mDao;
        this.rDao = rDao;
    }

    @Override
    public List<Reimbursement> getReimbursementsByEmployeeId(int employeeId) {
        
        List<Reimbursement> allReimbursements = rDao.getAll();
        List<Reimbursement> foundReimbursements = new ArrayList<>();
        
        for (Reimbursement reimbursement : allReimbursements) {
            if (reimbursement.getReimbursee().getId() == employeeId) {
                foundReimbursements.add(reimbursement);
            }
        }
        
        return foundReimbursements;
    }

    @Override
    public Attachment addAttachment(Attachment attachment) {
        log.info("Run add attachment");
        
        int id = aDao.add(attachment);
        attachment.setId(id);
        log.info(attachment.toString());
        
        return attachment;
    }
    
    @Override
    public List<Message> getMessagesForEmployee(int empId) {
        
        List<Message> allMessages = mDao.getAll();
        List<Message> foundMessages = new ArrayList<>();
        
        for (Message message : allMessages) {
            if (message.getReimbursement().getReimbursee().getId() == empId) {
                foundMessages.add(message);
            }
        }
        
        return foundMessages;
    }

    @Override
    public List<Attachment> getAttachmentsByEmployeeId(int empId) {
        List<Attachment> allAttachments = aDao.getAll();
        List<Attachment> foundAttachments = new ArrayList<>();
        
        for (Attachment attachment : allAttachments) {
            if (attachment.getReimbursement().getReimbursee().getId() == empId) {
                foundAttachments.add(attachment);
            }
        }
        
        return foundAttachments;
    }
    
    @Override
    public Reimbursement updateReimbursement(Reimbursement reimb) {
        
        Reimbursement updatedReimb = rDao.update(reimb);
        
        return updatedReimb;
    }

}
