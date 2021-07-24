package com.kevin_leader.services;

import java.util.List;

import com.kevin_leader.models.EventType;

public interface EventTypeService {
	
	public int add(String typeName, double percentCoverage);
	
	public List<EventType> getAll();
	
	public EventType getById(int id);
	
	public EventType update(int id, String typeName, double percentCoverage);
	
	public EventType deleteById(int id);
	
}
