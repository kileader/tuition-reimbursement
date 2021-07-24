package com.kevin_leader.services;

import java.util.List;

import com.kevin_leader.models.GradingFormat;

public interface GradingFormatService {
	
	public int add(String formatName, String description,
			String passingGradeCutoff);
	
	public List<GradingFormat> getAll();
	
	public GradingFormat getById(int id);
	
	public GradingFormat update(int id, String formatName, String description,
			String passingGradeCutoff);
	
	public GradingFormat deleteById(int id);
	
}
