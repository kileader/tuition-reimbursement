package com.kevin_leader.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.kevin_leader.models.Employee;
import com.kevin_leader.models.Event;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.repositories.GenericRepo;

public class ReimbursementServiceImpl implements ReimbursementService {

	private static final Logger log =
			Logger.getLogger(EventServiceImpl.class);
	private GenericRepo<Reimbursement> rDao;
	
	public ReimbursementServiceImpl(GenericRepo<Reimbursement> rDao) {
		log.info("Instantiate ReimbursementServiceImpl");
		this.rDao = rDao;
	}

	@Override
	public int add(Employee reimbursee, Event event, String description, 
			long submissionTime, Double hoursMissed, String finalGrade, 
			Double actualClaim) {
		Reimbursement reimb = new Reimbursement(reimbursee, event, description,
				submissionTime, hoursMissed, finalGrade, actualClaim);
		return rDao.add(reimb);
	}

	@Override
	public List<Reimbursement> getAll() {
		return rDao.getAll();
	}

	@Override
	public Reimbursement getById(int id) {
		return rDao.getById(id);
	}

	@Override
	public Reimbursement update(int id, Employee reimbursee, Event event,
			String description, long submissionTime, Double hoursMissed,
			String finalGrade, Double actualClaim) {
		Reimbursement reimb = new Reimbursement(id, reimbursee, event,
				description, submissionTime, hoursMissed, finalGrade,
				actualClaim);
		return rDao.update(reimb);
	}

	@Override
	public Reimbursement deleteById(int id) {
		Reimbursement reimb = rDao.getById(id);
		return rDao.delete(reimb);
	}
}
