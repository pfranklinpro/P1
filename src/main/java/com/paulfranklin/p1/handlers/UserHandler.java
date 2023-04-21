package com.paulfranklin.p1.handlers;

import com.paulfranklin.p1.dtos.responses.requests.NewUserRequest;
import com.paulfranklin.p1.services.UserService;
import com.paulfranklin.p1.utils.custom_exceptions.InvalidUserException;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserHandler {
    private final Logger logger = LoggerFactory.getLogger(UserHandler.class);

//    Services
    private final UserService userService;

//    Statuses
    private final Integer STATUS_CREATED = 201;
    private final Integer STATUS_FORBIDDEN = 403;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public void createNewUser(Context context) {
        NewUserRequest requestToCreateNewUser = new NewUserRequest(context.req);

        try {
            userService.createUser(requestToCreateNewUser);

            context.status(STATUS_CREATED);
        } catch (InvalidUserException exception) {
            logger.error("Failed to create new User from handler.");
            context.status(STATUS_FORBIDDEN);
        }
    }
}
