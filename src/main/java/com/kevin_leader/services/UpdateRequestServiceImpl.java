package com.kevin_leader.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kevin_leader.models.Attachment;
import com.kevin_leader.models.Employee;
import com.kevin_leader.models.Message;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.repositories.GenericRepo;

public class UpdateRequestServiceImpl implements UpdateRequestService {

    public static final Logger log = Logger
            .getLogger(UpdateRequestServiceImpl.class);
    private GenericRepo<Attachment> aDao;
    private GenericRepo<Employee> empDao;
    private GenericRepo<Message> mDao;
    private GenericRepo<Reimbursement> rDao;

    public UpdateRequestServiceImpl(GenericRepo<Attachment> aDao,
            GenericRepo<Employee> empDao, GenericRepo<Message> mDao,
            GenericRepo<Reimbursement> rDao) {
        log.info("Instantiate UpdateServiceImpl");
        this.aDao = aDao;
        this.empDao = empDao;
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

        int reimbId = attachment.getReimbursement().getId();
        Reimbursement reimb = rDao.getById(reimbId);

        int reimburseeId = reimb.getReimbursee().getId();
        Employee reimbursee = empDao.getById(reimburseeId);

        int benCoId = 0;
        if (reimbursee.getBenefitsCoordinator() != null) {
            benCoId = reimbursee.getBenefitsCoordinator().getId();
        }
        Employee benCo = null;
        if (benCoId != 0) {
            benCo = empDao.getById(benCoId);
        }

        int depHeadId = 0;
        if (reimbursee.getDepartmentHead() != null) {
            depHeadId = reimbursee.getDepartmentHead().getId();
        }
        Employee depHead = null;
        if (depHeadId != 0) {
            depHead = empDao.getById(depHeadId);
        }

        int supervisorId = 0;
        if (reimbursee.getSupervisor() != null) {
            supervisorId = reimbursee.getSupervisor().getId();
        }
        Employee supervisor = null;
        if (supervisorId != 0) {
            supervisor = empDao.getById(supervisorId);
        }

        reimbursee.setBenefitsCoordinator(benCo);
        reimbursee.setDepartmentHead(depHead);
        reimbursee.setSupervisor(supervisor);

        reimb.setReimbursee(reimbursee);
        
        attachment.setReimbursement(reimb);

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
            if (attachment.getReimbursement().getReimbursee()
                    .getId() == empId) {
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
