package com.kevin_leader.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kevin_leader.models.Employee;
import com.kevin_leader.models.Event;
import com.kevin_leader.models.EventType;
import com.kevin_leader.models.GradingFormat;
import com.kevin_leader.models.Reimbursement;

public class ReimbursementDaoTests {

	private static GenericRepo<Reimbursement> rDao;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		rDao = new GenericRepoImpl<>(Reimbursement.class);
	}
	
	@Test
	public void addSuccess() {
		EventType newType = new EventType("Battle Arena", 33);
		GradingFormat newFormat = new GradingFormat("Rock Paper Scissors",
				"Best two out of three", "2 wins");
		Employee reimbursee = new Employee("Super", "Saiyan",
				"somethingelse@gohan.com", "3j1o5ino1!", null, null, null, null);
		Event event = new Event("Doesn't Matter", 1632063600000L,
				"180 Semi Circle Hell, MI", 100.0, newType, newFormat, null);
		Reimbursement reimbursement = new Reimbursement(
				reimbursee, event, "I want to be reimbursed for a rock paper"
				+ " scissors death match.", 1558193400000L, 8.0, null, null);
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
				+ "onTime=1442682000000, hoursMissed=40.0, finalGrade=91, actualCl"
				+ "aim=899.2]";
		assertEquals(expected, actualReimbursement.toString());
	}
	
	@Test
	public void updateSuccess() {
		EventType newType = new EventType("Battle Arena", 33);
		GradingFormat newFormat = new GradingFormat("Rock Paper Scissors",
				"Best two out of three", "2 wins");
		Employee reimbursee = new Employee("Super", "Saiyan",
				"ultrasaiyan@vegeta.com", "3j1o5ino1!", null, null, null, null);
		Event event = new Event("Doesn't Matter", 1632063600000L,
				"180 Semi Circle Hell, MI", 100.0, newType, newFormat, null);
		Reimbursement reimbursement = new Reimbursement(3, 
				reimbursee, event, "I want to be reimbursed for a rock paper"
				+ " scissors death match.", 1558193400000L, 8.0, null, null);
		Reimbursement updatedReimbursement = rDao.update(reimbursement);
		assertEquals(reimbursement.toString(), updatedReimbursement.toString());
	}
	
	@Test
	public void deleteSuccess() {
		Reimbursement reimbToDelete = rDao.getById(1);
		Reimbursement nulledReimb = new Reimbursement(1, null, null,
				reimbToDelete.getDescription(), reimbToDelete.getSubmissionTime(),
				null, null, null);
		Reimbursement updatedReimb = rDao.update(nulledReimb);
		Reimbursement deletedReimb = rDao.delete(updatedReimb);
		assertEquals(updatedReimb.toString(), deletedReimb.toString());
	}

}
