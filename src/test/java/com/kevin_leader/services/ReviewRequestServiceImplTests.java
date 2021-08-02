package com.kevin_leader.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kevin_leader.models.Attachment;
import com.kevin_leader.models.Employee;
import com.kevin_leader.models.Message;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.repositories.GenericRepo;
import com.kevin_leader.repositories.GenericRepoImpl;

public class ReviewRequestServiceImplTests {
    
    private static GenericRepo<Attachment> aDao;
    private static GenericRepo<Employee> empDao;
    private static GenericRepo<Message> mDao;
    private static GenericRepo<Reimbursement> rDao;
    private static ReviewRequestService rrServ;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        aDao = new GenericRepoImpl<>(Attachment.class);
        empDao = new GenericRepoImpl<>(Employee.class);
        mDao = new GenericRepoImpl<>(Message.class);
        rDao = new GenericRepoImpl<>(Reimbursement.class);
        rrServ = new ReviewRequestServiceImpl(aDao, empDao, mDao, rDao);
    }

    @Test
    public void testGetReimbursementsForReviewer() {

        List<Reimbursement> reimbsForReview = rrServ
                .getReimbursementsForReviewer(1);
        
        assertEquals(1, reimbsForReview.size());

    }

}
