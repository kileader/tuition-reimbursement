package com.kevin_leader.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.kevin_leader.models.GradingFormat;
import com.kevin_leader.repositories.GenericRepo;
import com.kevin_leader.repositories.GenericRepoImpl;

public class GradingFormatServiceImpl implements GradingFormatService {
	
	private static final Logger log =
			Logger.getLogger(GradingFormatServiceImpl.class);
	private GenericRepo<GradingFormat> gfDao;
	
	public GradingFormatServiceImpl() {
		log.info("Instantiate GradingFormatServiceImpl");
		gfDao = new GenericRepoImpl<>(GradingFormat.class);
	}
	
	@Override
	public int add(String formatName, String description, String passingGradeCutoff) {
		GradingFormat gradingFormat = new
				GradingFormat(formatName, description, passingGradeCutoff);
		return gfDao.add(gradingFormat);
	}

	@Override
	public List<GradingFormat> getAll() {
		return gfDao.getAll();
	}

	@Override
	public GradingFormat getById(int id) {
		return gfDao.getById(id);
	}

	@Override
	public GradingFormat update(int id, String formatName, String description,
			String passingGradeCutoff) {
		GradingFormat gradingFormat = new 
				GradingFormat(id, formatName, description, passingGradeCutoff);
		return gfDao.update(gradingFormat);
	}

	@Override
	public GradingFormat deleteById(int id) {
		GradingFormat gradingFormat = gfDao.getById(id);
		return gfDao.delete(gradingFormat);
	}

}
