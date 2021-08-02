package com.kevin_leader.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kevin_leader.models.Message;
import com.kevin_leader.models.Reimbursement;

public class MessageDaoTests {

    private static GenericRepo<Message> mDao;
    private static GenericRepo<Reimbursement> rDao;

    @BeforeClass
    public static void setUpBeforeClass() {
        mDao = new GenericRepoImpl<>(Message.class);
        rDao = new GenericRepoImpl<>(Reimbursement.class);
    }

    @Test
    public void addSuccess() {
        Reimbursement reimbursement = rDao.getById(6);
        Message message = new Message(reimbursement, "supervisor", "request",
                1626909805000L,
                "You need assignments before you can get a reimbursement.");
        int id = mDao.add(message);
        assertNotEquals(0, id);
        assertNotEquals(-1, id);
    }

    @Test
    public void getAllSuccess() {
        List<Message> messages = mDao.getAll();
        assertTrue(9 < messages.size() && messages.size() < 13);
    }

    @Test
    public void getByIdSuccess() {
        String expected = "Message [id=10, reimbursementId=4, approverType="
                + "Benefits Coordinator, messageType=Denial, timeSent="
                + "1444064900000, message=Rozalin must have missed that the "
                + "class ended already! -Melvyn]";
        Message message = mDao.getById(10);
        assertEquals(expected, message.toString());
    }

//    @Test
//    public void updateSuccess() {
//        EventType newType = new EventType("Battle Arena", 33);
//        GradingFormat newFormat = new GradingFormat("Rock Paper Scissors",
//                "Best two out of three", "2 wins");
//        Employee reimbursee = new Employee("Super", "Saiyan",
//                "cucumber@vegeta.com", "3j1o5ino1!", null, null, null, null);
//        Event event = new Event("Doesn't Matter", 1632063600000L,
//                "180 Semi Circle Hell, MI", 100.0, newType, newFormat, null);
//        Reimbursement reimbursement = new Reimbursement(reimbursee, event,
//                "I want to be reimbursed for a rock paper"
//                        + " scissors death match.",
//                1558193400000L, 8.0, 0, null, null);
//        Message message = new Message(7, reimbursement, "supervisor", "request",
//                1626909805000L,
//                "You need assignments before you can get a reimbursement.");
//        Message updatedMessage = mDao.update(message);
//        assertEquals(message.toString(), updatedMessage.toString());
//    }

//    @Test
//    public void deleteSuccess() {
//        Message messageToDelete = mDao.getById(4);
//        Message nulledMessage = new Message(4, null,
//                messageToDelete.getApproverType(),
//                messageToDelete.getMessageType(), messageToDelete.getTimeSent(),
//                messageToDelete.getMessage());
//        Message updatedMessage = mDao.update(nulledMessage);
//        Message deletedMessage = mDao.delete(updatedMessage);
//        assertEquals(updatedMessage.toString(), deletedMessage.toString());
//    }

}