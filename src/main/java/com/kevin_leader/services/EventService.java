package com.kevin_leader.services;

import java.util.List;

import com.kevin_leader.models.Event;
import com.kevin_leader.models.EventType;
import com.kevin_leader.models.GradingFormat;

public interface EventService {
	
	public int add(String eventName, long startTime, String location,
			double tuition, EventType eventType, GradingFormat gradingFormat,
			Long endTime);

	public List<Event> getAll();
	
	public Event getById(int id);
	
	public Event update(int id, String eventName, long startTime,
			String location, double tuition, EventType eventType,
			GradingFormat gradingFormat, Long endTime);
	
	public Event deleteById(int id);
	
}
