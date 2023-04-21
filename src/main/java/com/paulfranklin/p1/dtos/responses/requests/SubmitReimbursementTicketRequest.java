package com.paulfranklin.p1.dtos.responses.requests;

import javax.servlet.http.HttpServletRequest;

public class SubmitReimbursementTicketRequest {
    private String description;
    private Double amount;
    private String typeId;
    private String token;

    public SubmitReimbursementTicketRequest(HttpServletRequest request) {
        this.description = request.getParameter("description");
        this.amount = Double.parseDouble(request.getParameter("amount"));
        this.typeId = request.getParameter("typeId");
        this.token = request.getParameter("token");
    }

    public String getDescription() {
        return description;
    }

    public Double getAmount() {
        return amount;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "SubmitReimbursementTicketRequest{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", typeId='" + typeId + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
