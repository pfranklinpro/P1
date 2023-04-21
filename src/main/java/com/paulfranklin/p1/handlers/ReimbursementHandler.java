package com.paulfranklin.p1.handlers;

import com.paulfranklin.p1.dtos.responses.requests.*;
import com.paulfranklin.p1.dtos.responses.Principal;
import com.paulfranklin.p1.models.Reimbursement;
import com.paulfranklin.p1.services.ReimbursementService;
import com.paulfranklin.p1.services.TokenService;
import com.paulfranklin.p1.utils.custom_exceptions.InvalidAuthenticationException;
import com.paulfranklin.p1.utils.custom_exceptions.InvalidReimbursementException;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReimbursementHandler {
    private final Logger logger = LoggerFactory.getLogger(ReimbursementHandler.class);

//    Services
    private final ReimbursementService reimbursementService;
    private final TokenService tokenService;

//    Statuses
    private final Integer STATUS_ACCEPTED = 202;
    private final Integer STATUS_CREATED = 201;
    private final Integer STATUS_UNAUTHORIZED = 401;
    private final Integer STATUS_FORBIDDEN = 403;

    public ReimbursementHandler(ReimbursementService reimbursementService, TokenService tokenService) {
        this.reimbursementService = reimbursementService;
        this.tokenService = tokenService;
    }

    public void submitUserReimbursementTicket(Context context) {
        SubmitReimbursementTicketRequest requestToSubmitUserReimbursementTicket = new SubmitReimbursementTicketRequest(context.req);

        try {
            Principal principal = tokenService.retrievePrincipalFromToken(requestToSubmitUserReimbursementTicket.getToken());

            reimbursementService.submitNewReimbursementTicket(principal, requestToSubmitUserReimbursementTicket);

            context.status(STATUS_CREATED);
        } catch (InvalidAuthenticationException exception) {
            logger.error("Failed to get User by login from handler.");

            context.status(STATUS_UNAUTHORIZED);
        } catch (InvalidReimbursementException exception) {
            logger.error("Failed to create new Reimbursement from handler.");

            context.status(STATUS_FORBIDDEN);
        }
    }

    public void showAllReimbursementTickets(Context context) {
        ShowAllReimbursementTicketsRequest requestToShowAllReimbursementTickets = new ShowAllReimbursementTicketsRequest(context.req);

        try {
            Principal principal = tokenService.retrievePrincipalFromToken(requestToShowAllReimbursementTickets.getToken());

            if (!(principal.getRole().equals("ADMIN") || principal.getRole().equals("FINANCE MANAGER"))) {
                throw new InvalidAuthenticationException();
            }

            List<Reimbursement> listOfAllReimbursements = reimbursementService.showAllReimbursementTickets();

            context.status(STATUS_ACCEPTED);
            context.json(listOfAllReimbursements);
        } catch (InvalidAuthenticationException exception) {
            logger.warn("Failed to show all Reimbursements from handler.");

//            https://github.com/221114-Java-React/Resources/blob/5c14d956b8dc2e95878f21e42a959fda022fa3a4/week3-rest/intro-web-http.md
            context.status(STATUS_UNAUTHORIZED);
        }
    }

    public void showMyReimbursementTickets(Context context) {
        ShowMyReimbursementTicketsRequest requestToShowMyReimbursementTickets = new ShowMyReimbursementTicketsRequest(context.req);

        try {
            Principal principal = tokenService.retrievePrincipalFromToken(requestToShowMyReimbursementTickets.getToken());

            List<Reimbursement> listOfAllReimbursements;
            if (requestToShowMyReimbursementTickets.getStatusId() == null) {
                listOfAllReimbursements = reimbursementService.showMyReimbursementTickets(principal);
            }
            else {
                listOfAllReimbursements = reimbursementService.showMyReimbursementTickets(principal, requestToShowMyReimbursementTickets.getStatusId());
            }

            context.status(STATUS_ACCEPTED);
            context.json(listOfAllReimbursements);
        } catch (InvalidAuthenticationException exception) {
            logger.warn("Failed to show my Reimbursements from handler.");

//            https://github.com/221114-Java-React/Resources/blob/5c14d956b8dc2e95878f21e42a959fda022fa3a4/week3-rest/intro-web-http.md
            context.status(STATUS_UNAUTHORIZED);
        }
    }

    public void approveReimbursementTicket(Context context) {
        ApproveReimbursementTicketsRequest requestToApproveReimbursementTicket = new ApproveReimbursementTicketsRequest(context.req);

        try {
            Principal principal = tokenService.retrievePrincipalFromToken(requestToApproveReimbursementTicket.getToken());

            if (!(principal.getRole().equals("ADMIN") || principal.getRole().equals("FINANCE MANAGER"))) {
                throw new InvalidAuthenticationException();
            }

            reimbursementService.approveReimbursementTicket(requestToApproveReimbursementTicket.getReimbId(), principal);

            context.status(STATUS_ACCEPTED);
        } catch (InvalidAuthenticationException exception) {
            logger.warn("Failed to approve Reimbursement from handler.");

//            https://github.com/221114-Java-React/Resources/blob/5c14d956b8dc2e95878f21e42a959fda022fa3a4/week3-rest/intro-web-http.md
            context.status(STATUS_UNAUTHORIZED);
        } catch (InvalidReimbursementException exception) {
            logger.warn("Failed to approve Reimbursement from handler.");

            context.status(STATUS_FORBIDDEN);
        }
    }

    public void denyReimbursementTicket(Context context) {
        DenyReimbursementTicketsRequest requestToDenyReimbursementTicket = new DenyReimbursementTicketsRequest(context.req);

        try {
            Principal principal = tokenService.retrievePrincipalFromToken(requestToDenyReimbursementTicket.getToken());

            if (!(principal.getRole().equals("ADMIN") || principal.getRole().equals("FINANCE MANAGER"))) {
                throw new InvalidAuthenticationException();
            }

            reimbursementService.denyReimbursementTicket(requestToDenyReimbursementTicket.getReimbId(), principal);

            context.status(STATUS_ACCEPTED);
        } catch (InvalidAuthenticationException exception) {
            logger.warn("Failed to deny Reimbursement from handler.");

//            https://github.com/221114-Java-React/Resources/blob/5c14d956b8dc2e95878f21e42a959fda022fa3a4/week3-rest/intro-web-http.md
            context.status(STATUS_UNAUTHORIZED);
        } catch (InvalidReimbursementException exception) {
            logger.warn("Failed to deny Reimbursement from handler.");

            context.status(STATUS_FORBIDDEN);
        }
    }
}
