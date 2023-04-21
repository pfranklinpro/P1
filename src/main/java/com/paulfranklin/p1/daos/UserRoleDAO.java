package com.paulfranklin.p1.daos;

import com.paulfranklin.p1.models.UserRole;
import com.paulfranklin.p1.utils.ConnectionFactory;
import com.paulfranklin.p1.utils.custom_exceptions.InvalidAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleDAO {
    private final Logger logger = LoggerFactory.getLogger(UserRoleDAO.class);

    public UserRole getUserRoleByRoleId(String roleId) {
        UserRole userRole = null;
        try (Connection sqlConnection = ConnectionFactory.getInstance().getConnection()) {
//            https://www.postgresql.org/docs/current/ddl-schemas.html
            PreparedStatement preparedSqlStatement = sqlConnection.prepareStatement("SELECT * FROM p1.ers_user_roles WHERE role_id = ?");
            preparedSqlStatement.setString(1, roleId);
            ResultSet sqlResultSet = preparedSqlStatement.executeQuery();

            if (sqlResultSet.next()) {
                userRole = new UserRole(sqlResultSet.getString("role_id"), sqlResultSet.getString("role"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            logger.error("Failed to get UserRole by role_id from database.");
            throw new InvalidAuthenticationException();
        }
        return userRole;
    }
}
