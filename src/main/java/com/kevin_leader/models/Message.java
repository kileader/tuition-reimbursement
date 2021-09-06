package com.kevin_leader.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "reimbursement_id")
    private Reimbursement reimbursement;

    @Column(name = "approver_type")
    private String approverType;

    @Column(name = "message_type")
    private String messageType;

    @Column(name = "time_sent")
    private long timeSent;

    private String message;

    public Message() {
        super();
    }

    public Message(Reimbursement reimbursement, String approverType,
            String messageType, long timeSent, String message) {
        super();
        this.reimbursement = reimbursement;
        this.approverType = approverType;
        this.messageType = messageType;
        this.timeSent = timeSent;
        this.message = message;
    }

    public Message(int id, Reimbursement reimbursement, String approverType,
            String messageType, long timeSent, String message) {
        super();
        this.id = id;
        this.reimbursement = reimbursement;
        this.approverType = approverType;
        this.messageType = messageType;
        this.timeSent = timeSent;
        this.message = message;
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

    public String getApproverType() {
        return approverType;
    }

    public void setApproverType(String approverType) {
        this.approverType = approverType;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public long getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(long timeSent) {
        this.timeSent = timeSent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        String reimbursementId = (reimbursement != null)
                ? String.valueOf(reimbursement.getId())
                : "";
        return "Message [id=" + id + ", reimbursementId=" + reimbursementId
                + ", approverType=" + approverType + ", messageType="
                + messageType + ", timeSent=" + timeSent + ", message="
                + message + "]";
    }

}
