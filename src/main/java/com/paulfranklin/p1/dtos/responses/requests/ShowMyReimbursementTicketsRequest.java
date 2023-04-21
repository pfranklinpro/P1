package com.paulfranklin.p1.dtos.responses.requests;

import javax.servlet.http.HttpServletRequest;

public class ShowMyReimbursementTicketsRequest {
    private String token;
    private String statusId;

    public ShowMyReimbursementTicketsRequest(HttpServletRequest request) {
        this.token = request.getParameter("token");
//        this.statusId = null;
        this.statusId = request.getParameter("statusId");
    }

    public String getToken() {
        return token;
    }

    public String getStatusId() {
        return statusId;
    }
}
