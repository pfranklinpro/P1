package com.paulfranklin.p1.utils;

import com.paulfranklin.p1.daos.ReimbursementDAO;
import com.paulfranklin.p1.daos.UserDAO;
import com.paulfranklin.p1.daos.UserRoleDAO;
import com.paulfranklin.p1.handlers.AuthenticationHandler;
import com.paulfranklin.p1.handlers.ReimbursementHandler;
import com.paulfranklin.p1.handlers.UserHandler;
import com.paulfranklin.p1.services.ReimbursementService;
import com.paulfranklin.p1.services.TokenService;
import com.paulfranklin.p1.services.UserService;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class Router {
    public static void handle(Javalin application) {
        final JwtConfiguration jwtConfiguration = new JwtConfiguration();

//        DAOs
        final UserRoleDAO userRoleDAO = new UserRoleDAO();
        final UserDAO userDAO = new UserDAO(userRoleDAO);
        final ReimbursementDAO reimbursementDAO = new ReimbursementDAO();

//        Services
        final UserService userService = new UserService(userDAO);
        final TokenService tokenService = new TokenService(jwtConfiguration);
        final ReimbursementService reimbursementService = new ReimbursementService(reimbursementDAO);

//        Handlers
        final UserHandler userHandler = new UserHandler(userService);
        final AuthenticationHandler authenticateHandler = new AuthenticationHandler(userService, tokenService);
        final ReimbursementHandler reimbursementHandler = new ReimbursementHandler(reimbursementService, tokenService);

        application.routes(() -> {
            path("/user", () -> {
                post(context -> userHandler.createNewUser(context));
            });
            path("/authenticate", () -> {
                post(context -> authenticateHandler.authenticateUser(context));
            });
            path("/submit_reimbursement_ticket", () -> {
                post(context -> reimbursementHandler.submitUserReimbursementTicket(context));
            });
            path("/show_reimbursement_tickets", () -> {
                post(context -> reimbursementHandler.showAllReimbursementTickets(context));
            });
            path("/approve_reimbursement_ticket", () -> {
                post(context -> reimbursementHandler.approveReimbursementTicket(context));
            });
            path("/deny_reimbursement_ticket", () -> {
                post(context -> reimbursementHandler.denyReimbursementTicket(context));
            });
            path("/show_my_reimbursement_tickets", () -> {
                post(context -> reimbursementHandler.showMyReimbursementTickets(context));
            });
        });
    }
}
