package com.kevin_leader.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "attachments")
public class Attachment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reimbursement_id")
	@Expose private Reimbursement reimbursement;
	
	@Column(name = "attachment_url")
	@Expose private String attachmentUrl;
	
	@Expose private String description;

	public Attachment() {
		super();
	}

	public Attachment(Reimbursement reimbursement, String attachmentUrl,
			String description) {
		super();
		this.reimbursement = reimbursement;
		this.attachmentUrl = attachmentUrl;
		this.description = description;
	}

	public Attachment(int id, Reimbursement reimbursement, String attachmentUrl,
			String description) {
		super();
		this.id = id;
		this.reimbursement = reimbursement;
		this.attachmentUrl = attachmentUrl;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Reimbursement getReimbursement() {
		return reimbursement;
	}

	public void setReimbursement(Reimbursement reimbursement) {
		this.reimbursement = reimbursement;
	}

	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		String reimbursementId = (reimbursement != null) ?
				String.valueOf(reimbursement.getId()) : "";
		return "Attachment [id=" + id + ", reimbursementId=" + reimbursementId +
				", attachmentUrl=" + attachmentUrl+ ", description=" + description + "]";
	}
	
}
