package com.kevin_leader.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kevin_leader.models.Employee;
import com.kevin_leader.models.Event;
import com.kevin_leader.models.Reimbursement;

public class ReimbursementDaoTests {

    private static GenericRepo<Reimbursement> rDao;
    private static GenericRepo<Employee> empDao;
    private static GenericRepo<Event> evDao;

    @BeforeClass
    public static void setUpBeforeClass() {
        rDao = new GenericRepoImpl<>(Reimbursement.class);
        empDao = new GenericRepoImpl<>(Employee.class);
    }

    @Test
    public void addSuccess() {
        Event event = evDao.getById(5);
        long currentTime = new Date().getTime();
        Reimbursement reimbursement = new Reimbursement(18, event,
                "I want to be reimbursed for a rock paper scissors death match.",
                currentTime, 8.0, 0, null, null);
        int id = rDao.add(reimbursement);
        assertNotEquals(0, id);
        assertNotEquals(-1, id);
    }

    @Test
    public void getAllSuccess() {
        List<Reimbursement> reimbursements = rDao.getAll();
        assertTrue(3 < reimbursements.size());
    }

    @Test
    public void getByIdSuccess() {
        Reimbursement actualReimbursement = rDao.getById(2);
        String expected = "Reimbursement [id=2, employeeId=4, eventId=2, de"
                + "scription=I am getting some supervisoral training., submissi"
                + "onTime=1442682000000, hoursMissed=40.0, approvalStep=4, finalGrade=91, actualCl"
                + "aim=899.2]";
        assertEquals(expected, actualReimbursement.toString());
    }

    @Test
    public void updateSuccess() {
        Reimbursement reimbToUpdate = rDao.getById(6);
        reimbToUpdate.setApprovalStep(1);
        Reimbursement updatedReimbursement = rDao.update(reimbToUpdate);
        assertEquals(reimbToUpdate.toString(), updatedReimbursement.toString());
    }

//    @Test
//    public void deleteSuccess() {
//        Reimbursement reimbToDelete = rDao.getById(1);
//        Reimbursement nulledReimb = new Reimbursement(1, null, null,
//                reimbToDelete.getDescription(),
//                reimbToDelete.getSubmissionTime(), null, null, null);
//        Reimbursement updatedReimb = rDao.update(nulledReimb);
//        Reimbursement deletedReimb = rDao.delete(updatedReimb);
//        assertEquals(updatedReimb.toString(), deletedReimb.toString());
//    }

}
