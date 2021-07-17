package com.kevin_leader.models.reimbursement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attachments")
public class Attachment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reimbursement_id")
	private Reimbursement reimbursement;
	
	@Column(name = "attachment_url")
	private String attachmentUrl;
	
	private String description;

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

	@Override
	public String toString() {
		return "Attachment [id=" + id + ", reimbursement=" + reimbursement + ", attachmentUrl=" + attachmentUrl
				+ ", description=" + description + "]";
	}
	
}
