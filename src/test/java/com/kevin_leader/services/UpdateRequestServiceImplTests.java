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

public class UpdateRequestServiceImplTests {

    private static GenericRepo<Attachment> aDao;
    private static GenericRepo<Message> mDao;
    private static GenericRepo<Reimbursement> rDao;
    private static UpdateRequestService urServ;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        aDao = new GenericRepoImpl<>(Attachment.class);
        mDao = new GenericRepoImpl<>(Message.class);
        rDao = new GenericRepoImpl<>(Reimbursement.class);
        urServ = new UpdateRequestServiceImpl(aDao, mDao, rDao);
    }

    @Test
    public void getReimbursementsByEmployeeIdPass() {
        List<Reimbursement> reimbs = urServ.getReimbursementsByEmployeeId(2);
        assertTrue(1 < reimbs.size());
    }

    @Test
    public void addAttachmentPass() {
        Reimbursement reimb = rDao.getById(6);
        Attachment attachment = new Attachment(reimb,
                "fakeBucket.com/iaghjia2jiu.txt", "My awesome text.");
        Attachment addedAttachment = urServ.addAttachment(attachment);
        int id = addedAttachment.getId();
        attachment.setId(id);
        assertEquals(attachment.toString(), addedAttachment.toString());
    }

    @Test
    public void getMessagesForEmployeePass() {
        List<Message> messages = urServ.getMessagesForEmployee(10);
        assertTrue(3 < messages.size());
    }

    @Test
    public void getAttachmentsByEmployeeIdPass() {
        List<Attachment> attachments = urServ.getAttachmentsByEmployeeId(2);
        assertTrue(0 < attachments.size());
    }

    @Test
    public void updateReimbursementPass() {
        Reimbursement reimbToUpdate = rDao.getById(6);
        reimbToUpdate.setFinalGrade("A");
        Reimbursement updatedReimbursement = urServ
                .updateReimbursement(reimbToUpdate);
        assertEquals(updatedReimbursement.toString(), reimbToUpdate.toString());
    }

}
