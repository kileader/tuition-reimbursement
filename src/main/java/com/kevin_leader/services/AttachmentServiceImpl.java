package com.kevin_leader.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.kevin_leader.models.Attachment;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.repositories.GenericRepo;

public class AttachmentServiceImpl implements AttachmentService {

	private static final Logger log =
			Logger.getLogger(AttachmentServiceImpl.class);
	private GenericRepo<Attachment> aDao;
	
	public AttachmentServiceImpl(GenericRepo<Attachment> aDao) {
		log.info("Instantiate AttachmentServiceImpl");
		this.aDao = aDao;
	}
	
	@Override
	public int add(Reimbursement reimbursement, String attachmentUrl,
			String description) {
		Attachment attachment = new Attachment(reimbursement, attachmentUrl,
				description);
		return aDao.add(attachment);
	}

	@Override
	public List<Attachment> getAll() {
		return aDao.getAll();
	}

	@Override
	public List<Attachment> getAllByReimbursementId(int rIdIn) {
		List<Attachment> allAttachments = aDao.getAll();
		
		List<Attachment> filteredAttachments = null;
		for (Attachment attachment : allAttachments) {
			
			int rId = attachment.getReimbursement().getId();
			
			if (rId == rIdIn) {
				filteredAttachments.add(attachment);
			}
			
		}
		return filteredAttachments;
	}

	@Override
	public Attachment getById(int id) {
		return aDao.getById(id);
	}

	@Override
	public Attachment update(int id, Reimbursement reimbursement,
			String attachmentUrl, String description) {
		Attachment attachment = new Attachment(id, reimbursement, attachmentUrl,
				description);
		return aDao.update(attachment);
	}

	@Override
	public Attachment deleteById(int id) {
		Attachment attachment = aDao.getById(id);
		return aDao.delete(attachment);
	}

}
