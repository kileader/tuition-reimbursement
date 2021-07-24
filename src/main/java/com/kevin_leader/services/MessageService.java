package com.kevin_leader.services;

import java.util.List;

import com.kevin_leader.models.Message;
import com.kevin_leader.models.Reimbursement;

public interface MessageService {
	
	public int add(Reimbursement reimbursement, String approverType,
			String messageType, long timeSent, String message);
	
	public List<Message> getAll();
	
	public Message getById(int id);
	
	public Message update(int id, Reimbursement reimbursement,
			String approverType, String messageType, long timeSent,
			String message);
	
	public Message deleteById(int id);
	
}