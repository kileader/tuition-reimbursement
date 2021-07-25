package com.kevin_leader.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.kevin_leader.models.Attachment;
import com.kevin_leader.models.Employee;
import com.kevin_leader.models.Event;
import com.kevin_leader.models.EventType;
import com.kevin_leader.models.GradingFormat;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.models.RequestForm;
import com.kevin_leader.repositories.GenericRepo;

public class RequestFormServiceImpl implements RequestFormService {
	
	private static final Logger log =
			Logger.getLogger(RequestFormServiceImpl.class);
	private GenericRepo<Employee> empDao;
	private GenericRepo<Event> evDao;
	private GenericRepo<EventType> etDao;
	private GenericRepo<GradingFormat> gfDao;
	private GenericRepo<Reimbursement> rDao;
	private GenericRepo<Attachment> aDao;
	private static List<Employee> allEmployees;
	private static List<EventType> allEventTypes;
	
	public RequestFormServiceImpl(GenericRepo<Employee> empDao,
			GenericRepo<Event> evDao, GenericRepo<EventType> etDao,
			GenericRepo<GradingFormat> gfDao, GenericRepo<Reimbursement> rDao,
			GenericRepo<Attachment> aDao) {
		log.info("Instantiate RequestFormServiceImpl");
		this.empDao = empDao;
		this.evDao = evDao;
		this.etDao = etDao;
		this.gfDao = gfDao;
		this.rDao = rDao;
		this.aDao = aDao;
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		if (allEmployees == null) {
			allEmployees = empDao.getAll();
		}
		return allEmployees;
	}

	@Override
	public List<Event> getAllEvents() {
		return evDao.getAll();
	}

	@Override
	public List<EventType> getAllEventTypes() {
		if (allEventTypes == null) {
			allEventTypes = etDao.getAll();
		}
		return allEventTypes;
	}

	@Override
	public List<GradingFormat> getAllGradingFormats() {
		return gfDao.getAll();
	}
	
	@Override
	public Reimbursement processRequestForm(RequestForm reqForm) {
		
		Reimbursement reimbursement = null;
		Employee reimbursee = null;
		boolean correctCredentials = false;
		
		List<Employee> employees = getAllEmployees();
		for (Employee employee : employees) {
			if (employee.getEmail().equals(reqForm.getEmail()) &&
					employee.getPassword().equals(reqForm.getPassword())) {
				correctCredentials = true;
				reimbursee = employee;
				break;
			}
		}
		
		if (correctCredentials) {
			Date dateTime = new Date();
			long currentTime = dateTime.getTime();
			
			Event event;
			if (reqForm.getEventId() != null) {
				event = evDao.getById(reqForm.getEventId());
			} else {
				Long startTime = convertToEpoch(
						reqForm.getStartDate(), reqForm.getStartTime());
				Long endTime = convertToEpoch(
						reqForm.getEndDate(), reqForm.getEndTime());
				
				GradingFormat format;
				if (reqForm.getFormatId() != null) {
					format = gfDao.getById(reqForm.getFormatId());
				} else {
					format = new GradingFormat(
							reqForm.getFormatName(),
							reqForm.getFormatDescription(),
							reqForm.getPassingGradeCutoff());
					int id = gfDao.add(format);
					format.setId(id);
				}
				
				event = new Event(reqForm.getEventName(), startTime,
						reqForm.getLocation(), reqForm.getTuition(),
						etDao.getById(reqForm.getTypeId()), format, endTime);
				
				int id = evDao.add(event);
				event.setId(id);
			}
			
			reimbursement = new Reimbursement(reimbursee, event,
					reqForm.getDescription(), currentTime,
					reqForm.getHoursMissed(), null, null);
			
			int id = rDao.add(reimbursement);
			reimbursement.setId(id);
			
			List<Attachment> attachments = reqForm.getAttachments();
			if (attachments != null) {
				for (Attachment attachment : attachments) {
					attachment.setReimbursement(reimbursement);
					aDao.add(attachment);
				}
			}
			
		}
		return reimbursement;
	}
	
	@Override
	public Long convertToEpoch(String date, String time) {
		String dateAndTime = date + " " + time + " EST";
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm zzz");
		
		Date dateTime = null;
		try {
			dateTime = sdf.parse(dateAndTime);
		} catch (ParseException e){
			log.warn(e);
		}
		
		Long epoch = null;
		if (dateTime != null) {
			epoch = dateTime.getTime();
		}
		
		return epoch;
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		return rDao.getById(id);
	}

	@Override
	public List<Attachment> getAttachmentsByReimbursementId(int rId) {
		
		List<Attachment> allAttachments = aDao.getAll();
		List<Attachment> filteredAttachments = new ArrayList<>();
		
		for (Attachment attachment : allAttachments) {
			if (attachment.getReimbursement().getId() == rId) {
				filteredAttachments.add(attachment);
			}
		}
		
		return filteredAttachments;
	}

	@Override
	public Event getEventById(int id) {
		return evDao.getById(id);
	}

	@Override
	public GradingFormat getGradingFormatById(int id) {
		return gfDao.getById(id);
	}
	
}
