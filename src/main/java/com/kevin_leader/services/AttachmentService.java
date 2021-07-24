package com.kevin_leader.services;

import java.util.List;

import com.kevin_leader.models.Attachment;
import com.kevin_leader.models.Reimbursement;

public interface AttachmentService {
	
	public int add(Reimbursement reimbursement, String attachmentUrl,
			String description);

	public List<Attachment> getAll();
	
	public List<Attachment> getAllByReimbursementId(int rId);
	
	public Attachment getById(int id);
	
	public Attachment update(int id, Reimbursement reimbursement,
			String attachmentUrl, String description);
	
	public Attachment deleteById(int id);
	
}
