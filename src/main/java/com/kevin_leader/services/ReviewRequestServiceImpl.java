package com.kevin_leader.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.kevin_leader.models.Attachment;
import com.kevin_leader.models.Employee;
import com.kevin_leader.models.Message;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.repositories.GenericRepo;

public class ReviewRequestServiceImpl implements ReviewRequestService {

    public static final Logger log = Logger
            .getLogger(ReviewRequestServiceImpl.class);
    private GenericRepo<Attachment> aDao;
    private GenericRepo<Employee> empDao;
    private GenericRepo<Message> mDao;
    private GenericRepo<Reimbursement> rDao;
    private List<Message> allMessages;
    private List<Attachment> allAttachments;

    public ReviewRequestServiceImpl(GenericRepo<Attachment> aDao,
            GenericRepo<Employee> empDao, GenericRepo<Message> mDao,
            GenericRepo<Reimbursement> rDao) {
        log.info("Instatiate ReviewRequestServiceImpl");
        this.aDao = aDao;
        this.empDao = empDao;
        this.mDao = mDao;
        this.rDao = rDao;
        allMessages = new ArrayList<>();
        allAttachments = new ArrayList<>();
    }

//    @Override
//    public List<List<Employee>> getSubordinates(int revId) {
//        log.info("Run getSubordinates");
//
//        List<Employee> allEmployees = empDao.getAll();
//
//        List<Employee> directSubs = new ArrayList<>();
//        List<Employee> departmentEmployees = new ArrayList<>();
//        List<Employee> benefitors = new ArrayList<>();
//
//        List<List<Employee>> subordinates = new ArrayList<>();
//
//        for (Employee employee : allEmployees) {
//
//            int supervisorId = 0;
//            if (employee.getSupervisor() != null) {
//                supervisorId = employee.getSupervisor().getId();
//            }
//            int depHeadId = 0;
//            if (employee.getDepartmentHead() != null) {
//                depHeadId = employee.getDepartmentHead().getId();
//            }
//            int benCoId = 0;
//            if (employee.getBenefitsCoordinator() != null) {
//                benCoId = employee.getBenefitsCoordinator().getId();
//            }
//
//            if (benCoId == revId) {
//                benefitors.add(employee);
//            } else if (depHeadId == revId) {
//                departmentEmployees.add(employee);
//            } else if (supervisorId == revId) {
//                directSubs.add(employee);
//            }
//
//        }
//
//        subordinates.add(directSubs);
//        subordinates.add(departmentEmployees);
//        subordinates.add(benefitors);
//
//        return subordinates;
//    }

    @Override
    public List<Reimbursement> getReimbursementsForReviewer(int empId) {
        log.info("Run getReimbursementsForReviewer()");

        log.info("empId = " + empId);

        List<Reimbursement> reimbsForReview = new ArrayList<>();
        List<Reimbursement> allReimbursements = rDao.getAll();

        for (Reimbursement reimb : allReimbursements) {

            if (reimb.getApprovalStep() != 5 && reimb.getApprovalStep() != 3
                    && reimb.getApprovalStep() != -1) {

                log.info("If the reimb needs an approval.");
                log.info("reimb = " + reimb.toString());
                if (reimb.getReimbursee().getBenefitsCoordinator() != null) {
                    log.info("BenCoId = " + reimb.getReimbursee()
                            .getBenefitsCoordinator().getId());
                }
                if (reimb.getReimbursee().getDepartmentHead() != null) {
                    log.info("DepHeadId = " + reimb.getReimbursee()
                            .getDepartmentHead().getId());
                }
                if (reimb.getReimbursee().getSupervisor() != null) {
                    log.info("SupervisorId = "
                            + reimb.getReimbursee().getSupervisor().getId());
                }

                if (reimb.getReimbursee().getBenefitsCoordinator() != null
                        && reimb.getReimbursee().getBenefitsCoordinator()
                                .getId() == empId) {
                    log.info("If it needs ben co approval");

                    reimbsForReview.add(reimb);

                } else if (reimb.getReimbursee().getDepartmentHead() != null
                        && reimb.getApprovalStep() != 2 && reimb.getReimbursee()
                                .getDepartmentHead().getId() == empId) {
                    log.info("If it needs dep head approval");

                    reimbsForReview.add(reimb);

                } else if (reimb.getReimbursee().getSupervisor() != null
                        && reimb.getApprovalStep() != 1 && reimb.getReimbursee()
                                .getSupervisor().getId() == empId) {
                    log.info("It needs direct super approval");

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
        
        log.info(message.getMessageType());

        if (message.getMessageType().equals("Denial")) {

            reimb.setApprovalStep(-1);
            
        } else if (message.getMessageType().equals("Approval")) {

            if (reimb.getApprovalStep() == 2) {

                reimb.setApprovalStep(3);

            } else if (reimb.getApprovalStep() == 1) {

                reimb.setApprovalStep(2);

            } else {

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
