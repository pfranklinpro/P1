package com.paulfranklin.p1.handlers;

import com.paulfranklin.p1.dtos.responses.requests.LoginRequest;
import com.paulfranklin.p1.dtos.responses.Principal;
import com.paulfranklin.p1.services.TokenService;
import com.paulfranklin.p1.services.UserService;
import com.paulfranklin.p1.utils.custom_exceptions.InvalidAuthenticationException;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationHandler {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationHandler.class);

//    Services
    private final UserService userService;
    private final TokenService tokenService;

//    Statuses
    private final Integer STATUS_ACCEPTED = 202;
    private final Integer STATUS_UNAUTHORIZED = 401;

    public AuthenticationHandler(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    public void authenticateUser(Context context) {
        LoginRequest requestToLoginUser = new LoginRequest(context.req);
        try {
            Principal principalSubject = userService.loginUser(requestToLoginUser);

            String token = tokenService.createNewToken(principalSubject);
            context.res.setHeader("authorization", token);

            context.status(STATUS_ACCEPTED);
            context.json(principalSubject);
        } catch (InvalidAuthenticationException exception) {
            logger.error("Failed to get User by login from handler.");

//            https://github.com/221114-Java-React/Resources/blob/5c14d956b8dc2e95878f21e42a959fda022fa3a4/week3-rest/intro-web-http.md
            context.status(STATUS_UNAUTHORIZED);
        }
    }
}
