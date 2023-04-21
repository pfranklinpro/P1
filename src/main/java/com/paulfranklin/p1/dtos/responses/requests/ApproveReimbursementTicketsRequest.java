package com.paulfranklin.p1.dtos.responses.requests;

import javax.servlet.http.HttpServletRequest;

public class ApproveReimbursementTicketsRequest {
    private String token;
    private String reimbId;

    public ApproveReimbursementTicketsRequest(HttpServletRequest request) {
        this.token = request.getParameter("token");
        this.reimbId = request.getParameter("reimbId");
    }

    public String getToken() {
        return token;
    }

    public String getReimbId() {
        return reimbId;
    }
}
