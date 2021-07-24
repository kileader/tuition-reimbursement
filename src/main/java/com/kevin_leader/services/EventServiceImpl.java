package com.kevin_leader.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.kevin_leader.models.Event;
import com.kevin_leader.models.EventType;
import com.kevin_leader.models.GradingFormat;
import com.kevin_leader.repositories.GenericRepo;
import com.kevin_leader.repositories.GenericRepoImpl;

public class EventServiceImpl implements EventService {
	
	private static final Logger log =
			Logger.getLogger(EventServiceImpl.class);
	private GenericRepo<Event> evDao;
	
	public EventServiceImpl() {
		log.info("Instantiate EventServiceImpl");
		evDao = new GenericRepoImpl<>(Event.class);
	}
	
	@Override
	public int add(String eventName, long startTime, String location, double tuition, EventType eventType,
			GradingFormat gradingFormat, Long endTime) {
		Event event = new Event(eventName, startTime, location, tuition,
				eventType, gradingFormat, endTime);
		return evDao.add(event);
	}

	@Override
	public List<Event> getAll() {
		return evDao.getAll();
	}

	@Override
	public Event getById(int id) {
		return evDao.getById(id);
	}

	@Override
	public Event update(int id, String eventName, long startTime, String location, double tuition, EventType eventType,
			GradingFormat gradingFormat, Long endTime) {
		Event event = new Event(id, eventName, startTime, location, tuition,
				eventType, gradingFormat, endTime);
		return evDao.update(event);
	}

	@Override
	public Event deleteById(int id) {
		Event event = evDao.getById(id);
		return evDao.delete(event);
	}

}
