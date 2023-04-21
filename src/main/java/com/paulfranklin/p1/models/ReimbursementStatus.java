package com.paulfranklin.p1.models;

public class ReimbursementStatus {
//    CREATE TABLE ERS_REIMBURSEMENT_STATUSES (
//            STATUS_ID VARCHAR(255) PRIMARY KEY,
//    STATUS VARCHAR(255) UNIQUE
//);
    private String statusId;
    private String status;

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
