package com.kevin_leader.services;

import java.util.List;

import com.kevin_leader.models.Attachment;
import com.kevin_leader.models.Message;
import com.kevin_leader.models.Reimbursement;

public interface ReviewRequestService {
    
    public List<Reimbursement> getReimbursementsForReviewer(int empId);
    
    public List<Attachment> getAttachmentsForReimbursement(int rId);

    public List<Message> getMessagesForReimbursement(int rId);

    public Message incrementStepAndAddMessage(Message message);

    public void loadAllAttachments();

    public void loadAllMessages();

}
