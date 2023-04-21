package com.paulfranklin.p1.dtos.responses.requests;

import javax.servlet.http.HttpServletRequest;

public class ShowAllReimbursementTicketsRequest {
    private String token;

    public ShowAllReimbursementTicketsRequest(HttpServletRequest request) {
        this.token = request.getParameter("token");
    }

    public String getToken() {
        return token;
    }
}
