package com.paulfranklin.p1.models;

public class ReimbursementType {
//    CREATE TABLE ERS_REIMBURSEMENT_TYPES (
//            TYPE_ID VARCHAR(255) PRIMARY KEY,
//    TYPE VARCHAR(255) UNIQUE
//);
    private String typeId;
    private String type;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
