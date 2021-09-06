package com.kevin_leader.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kevin_leader.models.Attachment;
import com.kevin_leader.models.Reimbursement;

public class AttachmentDaoTests {

    private static GenericRepo<Attachment> aDao;
    private static GenericRepo<Reimbursement> rDao;

    @BeforeClass
    public static void setUpBeforeClass() {
        aDao = new GenericRepoImpl<>(Attachment.class);
        rDao = new GenericRepoImpl<>(Reimbursement.class);
    }

    @Test
    public void addSuccess() {
        Reimbursement reimbursement = rDao.getById(6);
        Attachment attachment = new Attachment(reimbursement,
                "fakedatabucket.com/jfgiouhj234ijo", "Here's my scorecard.");
        int id = aDao.add(attachment);
        assertTrue(id > 0);
    }

    @Test
    public void getAllSuccess() {
        List<Attachment> attachments = aDao.getAll();
        assertTrue(1 < attachments.size());
    }

    @Test
    public void getByIdSuccess() {
        Attachment expected = new Attachment(2, rDao.getById(2),
                "fakedatabucket.com/fatij124314ij", "Here is my presentation");
        Attachment actual = aDao.getById(2);
        assertEquals(expected.toString(), actual.toString());
    }

//    @Test
//    public void updateSuccess() {
//        EventType newType = new EventType("Battle Arena", 33);
//        GradingFormat newFormat = new GradingFormat("Rock Paper Scissors",
//                "Best two out of three", "2 wins");
//        Employee reimbursee = new Employee("Super", "Saiyan",
//                "attachment@vegeta.com", "3j1o5ino1!", null, null, null, null);
//        Event event = new Event("Doesn't Matter", 1632063600000L,
//                "180 Semi Circle Hell, MI", 100.0, newType, newFormat, null);
//        Reimbursement reimbursement = new Reimbursement(reimbursee, event,
//                "I want to be reimbursed for a rock paper"
//                        + " scissors death match.",
//                1558193400000L, 8.0, 0, null, null);
//        Attachment attachment = new Attachment(4, reimbursement,
//                "fakedatabucket.com/jfgiouhj234ijo", "Here's my scorecard.");
//        Attachment updatedAttachment = aDao.update(attachment);
//        assertEquals(attachment.toString(), updatedAttachment.toString());
//    }

//    @Test
//    public void deleteSuccess() {
//        Attachment attachmentToDelete = aDao.getById(1);
//        Attachment deletedAttachment = aDao.delete(attachmentToDelete);
//        assertEquals(deletedAttachment.toString(),
//                attachmentToDelete.toString());
//    }

}
