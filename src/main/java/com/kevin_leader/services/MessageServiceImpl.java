package com.kevin_leader.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.kevin_leader.models.Message;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.repositories.GenericRepo;

public class MessageServiceImpl implements MessageService {
	
	private static final Logger log =
			Logger.getLogger(EventServiceImpl.class);
	private GenericRepo<Message> mDao;
	
	public MessageServiceImpl(GenericRepo<Message> mDao) {
		log.info("Instantiate MessageServiceImpl");
		this.mDao = mDao;
	}

	@Override
	public int add(Reimbursement reimbursement, String approverType,
			String messageType, long timeSent, String messageBody) {
		Message message = new Message(reimbursement, approverType,
				messageType, timeSent, messageBody);
		return mDao.add(message);
	}

	@Override
	public List<Message> getAll() {
		return mDao.getAll();
	}

	@Override
	public Message getById(int id) {
		return mDao.getById(id);
	}

	@Override
	public Message update(int id, Reimbursement reimbursement,
			String approverType, String messageType, long timeSent,
			String messageBody) {
		Message message = new Message(id, reimbursement, approverType,
				messageType, timeSent, messageBody);
		return mDao.update(message);
	}

	@Override
	public Message deleteById(int id) {
		Message message = mDao.getById(id);
		return message;
	}
	
}
