package com.paulfranklin.p1.models;

import java.sql.Timestamp;
import java.util.Arrays;

public class Reimbursement {
//            REIMB_ID VARCHAR(255) PRIMARY KEY,
//    AMOUNT NUMERIC(6,2) NOT NULL,
//    SUBMITTED TIMESTAMP NOT NULL,
//    RESOLVED TIMESTAMP,
//    DESCRIPTION VARCHAR(255) NOT NULL,
//    RECEIPT BYTEA,
//    PAYMENT_ID VARCHAR(255),
//    AUTHOR_ID VARCHAR(255) NOT NULL REFERENCES ERS_USERS(USER_ID),
//    RESOLVER_ID VARCHAR(255) REFERENCES ERS_USERS,
//    STATUS_ID VARCHAR(255) NOT NULL REFERENCES ERS_REIMBURSEMENT_STATUSES(STATUS_ID),
//    TYPE_ID VARCHAR(255) NOT NULL REFERENCES ERS_REIMBURSEMENT_TYPES(TYPE_ID)
    private String reimbId;
    private Double amount;
//    https://www.postgresql.org/docs/9.1/datatype-datetime.html
    private Timestamp submitted;
    private Timestamp resolved;
    private String description;
    private byte[] receipt;
    private String paymentId;
    private String authorId;
    private String resolverId;
    private String statusId;
    private String typeId;

    public Reimbursement(String reimbId, Double amount, Timestamp submitted, String description, String authorId, String typeId) {
        this.reimbId = reimbId;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = null;
        this.description = description;
        this.receipt = null;
        this.paymentId = null;
        this.authorId = authorId;
        this.resolverId = null;
        this.statusId = "0";
        this.typeId = typeId;
    }

    public Reimbursement(String reimbId, Double amount, Timestamp submitted, Timestamp resolved, String description, byte[] receipt, String paymentId, String authorId, String resolverId, String statusId, String typeId) {
        this.reimbId = reimbId;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receipt = receipt;
        this.paymentId = paymentId;
        this.authorId = authorId;
        this.resolverId = resolverId;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public String getReimbId() {
        return reimbId;
    }

    public Double getAmount() {
        return amount;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getReceipt() {
        return receipt;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getResolverId() {
        return resolverId;
    }

    public String getStatusId() {
        return statusId;
    }

    public String getTypeId() {
        return typeId;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbId='" + reimbId + '\'' +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", receipt=" + Arrays.toString(receipt) +
                ", paymentId='" + paymentId + '\'' +
                ", authorId='" + authorId + '\'' +
                ", resolverId='" + resolverId + '\'' +
                ", statusId='" + statusId + '\'' +
                ", typeId='" + typeId + '\'' +
                '}';
    }
}
