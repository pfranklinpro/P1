package com.paulfranklin.p1;

import com.paulfranklin.p1.dtos.responses.Principal;
import com.paulfranklin.p1.models.UserRole;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrincipalTest {
    private Principal principalSut;

    @Before
    public void init() {
        principalSut = new Principal(
                "0",
                "paul219",
                "paul219@pf.com",
                "paul",
                "franklin",
                new UserRole("0","EMPLOYEE")
        );
    }

    @Test
    public void test_getUserId_setUserId() {
        // Arrange
        Principal sut = principalSut;
        assertEquals("0",sut.getUserId());

        // Act
        sut.setUserId("");

        // Assert
        assertEquals("",sut.getUserId());
    }

    @Test
    public void test_getUsername_setUsername() {
        // Arrange
        Principal sut = principalSut;
        assertEquals("paul219",sut.getUsername());

        // Act
        sut.setUsername("");

        // Assert
        assertEquals("",sut.getUsername());
    }

    @Test
    public void test_getEmail_setEmail() {
        // Arrange
        Principal sut = principalSut;
        assertEquals("paul219@pf.com",sut.getEmail());

        // Act
        sut.setEmail("");

        // Assert
        assertEquals("",sut.getEmail());
    }

    @Test
    public void test_getGivenName_setGivenName() {
        // Arrange
        Principal sut = principalSut;
        assertEquals("paul",sut.getGivenName());

        // Act
        sut.setGivenName("");

        // Assert
        assertEquals("",sut.getGivenName());
    }

    @Test
    public void test_getSurname_setSurname() {
        // Arrange
        Principal sut = principalSut;
        assertEquals("franklin",sut.getSurname());

        // Act
        sut.setSurname("");

        // Assert
        assertEquals("",sut.getSurname());
    }

    @Test
    public void test_getRole_setRole() {
        // Arrange
        Principal sut = principalSut;
        assertEquals("EMPLOYEE",sut.getRole());

        // Act
        sut.setRole("");

        // Assert
        assertEquals("",sut.getRole());
    }
}
