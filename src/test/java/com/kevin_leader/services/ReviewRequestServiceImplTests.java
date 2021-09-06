package com.kevin_leader.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kevin_leader.models.Attachment;
import com.kevin_leader.models.Message;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.repositories.GenericRepo;
import com.kevin_leader.repositories.GenericRepoImpl;

public class ReviewRequestServiceImplTests {

    private static GenericRepo<Attachment> aDao;
    private static GenericRepo<Message> mDao;
    private static GenericRepo<Reimbursement> rDao;
    private static ReviewRequestService rrServ;

    @BeforeClass
    public static void setUpBeforeClass() {
        aDao = new GenericRepoImpl<>(Attachment.class);
        mDao = new GenericRepoImpl<>(Message.class);
        rDao = new GenericRepoImpl<>(Reimbursement.class);
        rrServ = new ReviewRequestServiceImpl(aDao, mDao, rDao);
        rrServ.loadAllAttachments();
        rrServ.loadAllMessages();
    }

    @Test
    public void getReimbursementsForReviewerPass() {
        List<Reimbursement> reimbsForReview = rrServ
                .getReimbursementsForReviewer(1);
        assertTrue(0 < reimbsForReview.size());
    }

    @Test
    public void getAttachmentsForReimbursementPass() {
        List<Attachment> attachments = rrServ.getAttachmentsForReimbursement(2);
        assertTrue(0 < attachments.size());
    }

    @Test
    public void getMessagesForReimbursementPass() {
        List<Message> messages = rrServ.getMessagesForReimbursement(2);
        assertTrue(1 < messages.size());
    }

    @Test
    public void denialPass() {
        Reimbursement reimbursement = new Reimbursement();
        int rId = rDao.add(reimbursement);
        Message messageBefore = new Message(reimbursement, "Direct Supervisor",
                "Denial", 0, "hi");
        rrServ.incrementStepAndAddMessage(messageBefore);
        Reimbursement retreivedReimb = rDao.getById(rId);
        assertEquals(-1, retreivedReimb.getApprovalStep());
    }

    @Test
    public void step1Pass() {
        Reimbursement reimbursement = new Reimbursement();
        int rId = rDao.add(reimbursement);
        Message messageBefore = new Message(reimbursement, "Direct Supervisor",
                "Approval", 0, "hi");
        rrServ.incrementStepAndAddMessage(messageBefore);
        Reimbursement retreivedReimb = rDao.getById(rId);
        assertEquals(1, retreivedReimb.getApprovalStep());
    }

    @Test
    public void step2Pass() {
        Reimbursement reimbursement = new Reimbursement();
        int rId = rDao.add(reimbursement);
        Message messageBefore = new Message(reimbursement, "Department Head",
                "Approval", 0, "hi");
        rrServ.incrementStepAndAddMessage(messageBefore);
        Reimbursement retreivedReimb = rDao.getById(rId);
        assertEquals(2, retreivedReimb.getApprovalStep());
    }

    @Test
    public void step3Pass() {
        Reimbursement reimbursement = new Reimbursement();
        int rId = rDao.add(reimbursement);
        Message messageBefore = new Message(reimbursement,
                "Benefits Coordinator", "Approval", 0, "hi");
        rrServ.incrementStepAndAddMessage(messageBefore);
        Reimbursement retreivedReimb = rDao.getById(rId);
        assertEquals(3, retreivedReimb.getApprovalStep());
    }

}
