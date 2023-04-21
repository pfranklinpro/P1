package com.paulfranklin.p1.daos;

import com.paulfranklin.p1.models.User;
import com.paulfranklin.p1.models.UserRole;
import com.paulfranklin.p1.utils.ConnectionFactory;
import com.paulfranklin.p1.utils.custom_exceptions.InvalidAuthenticationException;
import com.paulfranklin.p1.utils.custom_exceptions.InvalidUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private final Logger logger = LoggerFactory.getLogger(UserDAO.class);

//    DAOs
    private final UserRoleDAO userRoleDAO;

    public UserDAO(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    public void createUser(User user) {
        try (Connection sqlConnection = ConnectionFactory.getInstance().getConnection()) {
//            https://www.postgresql.org/docs/current/ddl-schemas.html
            PreparedStatement preparedSqlStatement = sqlConnection.prepareStatement("INSERT INTO p1.ers_users (user_id, username, email, \"password\", given_name, surname, is_active, role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedSqlStatement.setString(1, user.getUserId());
            preparedSqlStatement.setString(2, user.getUsername());
            preparedSqlStatement.setString(3, user.getEmail());
            preparedSqlStatement.setString(4, user.getPassword());
            preparedSqlStatement.setString(5, user.getGivenName());
            preparedSqlStatement.setString(6, user.getSurname());
            preparedSqlStatement.setBoolean(7, user.getActive());
            preparedSqlStatement.setString(8, user.getRole().getRoleId());
            preparedSqlStatement.executeUpdate();
            logger.info("Saved new User in database.");
        } catch (SQLException exception) {
            exception.printStackTrace();
            logger.error("Failed to save new User in database.");
            throw new InvalidUserException();
        }
    }

    public User getUserByLogin(String username, String password) {
        User user = null;
        try (Connection sqlConnection = ConnectionFactory.getInstance().getConnection()) {
//            https://www.postgresql.org/docs/current/ddl-schemas.html
            PreparedStatement preparedSqlStatement = sqlConnection.prepareStatement("SELECT * FROM p1.ers_users WHERE username = ? AND password = ?");
            preparedSqlStatement.setString(1, username);
            preparedSqlStatement.setString(2, password);
            ResultSet sqlResultSet = preparedSqlStatement.executeQuery();

            if (sqlResultSet.next()) {
                String roleId = sqlResultSet.getString("role_id");
                UserRole userRole = userRoleDAO.getUserRoleByRoleId(roleId);

                user = new User(sqlResultSet.getString("user_id"), sqlResultSet.getString("username"), sqlResultSet.getString("email"), sqlResultSet.getString("password"), sqlResultSet.getString("given_name"), sqlResultSet.getString("surname"), sqlResultSet.getBoolean("is_active"), userRole);
            }
            else {
                throw new InvalidAuthenticationException();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            logger.error("Failed to get User by login from database.");
            throw new InvalidAuthenticationException();
        } catch (InvalidAuthenticationException exception) {
            logger.error("Failed to get User by login from database because there was no user with the same username and password.");
            throw new InvalidAuthenticationException();
        }

        return user;
    }

//    public List<String> findAllUsernames() {
//        List<String> listOfAllUsernames = new ArrayList<>();
//
//        try (Connection sqlConnection = ConnectionFactory.getInstance().getConnection()) {
////            https://www.postgresql.org/docs/current/ddl-schemas.html
//            PreparedStatement preparedSqlStatement = sqlConnection.prepareStatement("SELECT username FROM p1.ers_users");
//            ResultSet sqlResultSet = preparedSqlStatement.executeQuery();
//
//            while (sqlResultSet.next()) {
//                String username = sqlResultSet.getString("username");
//                listOfAllUsernames.add(username);
//            }
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        }
//
//        return listOfAllUsernames;
//    }
}
