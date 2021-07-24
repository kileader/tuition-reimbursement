package com.kevin_leader.services;

import java.util.List;

import com.kevin_leader.models.Employee;
import com.kevin_leader.models.Event;
import com.kevin_leader.models.Reimbursement;

public interface ReimbursementService {
	
	public int add(Employee reimbursee, Event event, String description,
			long submissionTime, Double hoursMissed, String finalGrade,
			Double actualClaim);
	
	public List<Reimbursement> getAll();
	
	public Reimbursement getById(int id);
	
	public Reimbursement update(int id, Employee reimbursee, Event event,
			String description, long submissionTime, Double hoursMissed,
			String finalGrade, Double actualClaim);
	
	public Reimbursement deleteById(int id);
	
}