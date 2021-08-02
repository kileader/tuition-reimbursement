package com.kevin_leader.services;

import java.util.List;

import com.kevin_leader.models.Attachment;
import com.kevin_leader.models.Message;
import com.kevin_leader.models.Reimbursement;

public interface UpdateRequestService {
    
    public List<Reimbursement> getReimbursementsByEmployeeId(int employeeId);

    public List<Message> getMessagesForEmployee(int empId);

    public List<Attachment> getAttachmentsByEmployeeId(int empId);

    public Attachment addAttachment(Attachment attachment);

    public Reimbursement updateReimbursement(Reimbursement reimbursement);
    
}
