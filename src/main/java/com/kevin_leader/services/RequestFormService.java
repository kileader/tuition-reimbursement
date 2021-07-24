package com.kevin_leader.services;

import java.util.List;

import com.kevin_leader.models.Employee;
import com.kevin_leader.models.Event;
import com.kevin_leader.models.EventType;
import com.kevin_leader.models.GradingFormat;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.models.RequestForm;

public interface RequestFormService {
	
	public Reimbursement processRequestForm(RequestForm reqForm);
	
	public List<Employee> getAllEmployees();
	
	public List<Event> getAllEvents();
	
	public List<EventType> getAllEventTypes();
	
	public List<GradingFormat> getAllGradingFormats();
	
	public Long convertToEpoch(String date, String time);
	
}
