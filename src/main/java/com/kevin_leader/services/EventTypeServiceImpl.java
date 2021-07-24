package com.kevin_leader.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.kevin_leader.models.EventType;
import com.kevin_leader.repositories.GenericRepo;

public class EventTypeServiceImpl implements EventTypeService {
	
	private static final Logger log =
			Logger.getLogger(EventTypeServiceImpl.class);
	private GenericRepo<EventType> etDao;
	
	public EventTypeServiceImpl(GenericRepo<EventType> etDao) {
		log.info("Instantiate EventTypeServiceImpl");
		this.etDao = etDao;
	}
	
	@Override
	public int add(String typeName, double percentCoverage) {
		EventType eventType = new EventType(typeName, percentCoverage);
		return etDao.add(eventType);
	}

	@Override
	public List<EventType> getAll() {
		return etDao.getAll();
	}

	@Override
	public EventType getById(int id) {
		return etDao.getById(id);
	}

	@Override
	public EventType update(int id, String typeName, double percentCoverage) {
		EventType eventType = new EventType(id, typeName, percentCoverage);
		return etDao.update(eventType);
	}

	@Override
	public EventType deleteById(int id) {
		EventType eventType = etDao.getById(id);
		return eventType;
	}

}
