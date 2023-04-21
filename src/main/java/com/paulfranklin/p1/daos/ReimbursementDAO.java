package com.paulfranklin.p1.daos;

import com.paulfranklin.p1.models.Reimbursement;
import com.paulfranklin.p1.utils.ConnectionFactory;
import com.paulfranklin.p1.utils.custom_exceptions.InvalidReimbursementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO {
    private final Logger logger = LoggerFactory.getLogger(ReimbursementDAO.class);

    public void createReimbursementTicket(Reimbursement reimbursement) {
        try (Connection sqlConnection = ConnectionFactory.getInstance().getConnection()) {
//            https://www.postgresql.org/docs/current/ddl-schemas.html
            PreparedStatement preparedSqlStatement = sqlConnection.prepareStatement("INSERT INTO p1.ERS_REIMBURSEMENTS\n" +
                    "(REIMB_ID,AMOUNT,SUBMITTED,RESOLVED,DESCRIPTION,RECEIPT,PAYMENT_ID,AUTHOR_ID,RESOLVER_ID,STATUS_ID,TYPE_ID)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedSqlStatement.setString(1, reimbursement.getReimbId());
            preparedSqlStatement.setDouble(2, reimbursement.getAmount());
            preparedSqlStatement.setTimestamp(3, reimbursement.getSubmitted());
            preparedSqlStatement.setTimestamp(4, reimbursement.getResolved());
            preparedSqlStatement.setString(5, reimbursement.getDescription());
            preparedSqlStatement.setBytes(6, reimbursement.getReceipt());
            preparedSqlStatement.setString(7, reimbursement.getPaymentId());
            preparedSqlStatement.setString(8, reimbursement.getAuthorId());
            preparedSqlStatement.setString(9, reimbursement.getResolverId());
            preparedSqlStatement.setString(10, reimbursement.getStatusId());
            preparedSqlStatement.setString(11, reimbursement.getTypeId());
            preparedSqlStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            logger.error("Failed to save new Reimbursement in database.");
            throw new InvalidReimbursementException();
        }
    }

    public List<Reimbursement> getAllPendingReimbursementTickets() {
        List<Reimbursement> listOfAllReimbursements = new ArrayList<>();

        try (Connection sqlConnection = ConnectionFactory.getInstance().getConnection()) {
//            https://www.postgresql.org/docs/current/ddl-schemas.html
            PreparedStatement preparedSqlStatement = sqlConnection.prepareStatement("SELECT * FROM p1.ERS_REIMBURSEMENTS WHERE RESOLVED IS NULL");
            ResultSet sqlResultSet = preparedSqlStatement.executeQuery();

            while (sqlResultSet.next()) {
                Reimbursement reimbursement = new Reimbursement(
                        sqlResultSet.getString("REIMB_ID"),
                        sqlResultSet.getDouble("AMOUNT"),
                        sqlResultSet.getTimestamp("SUBMITTED"),
                        sqlResultSet.getTimestamp("RESOLVED"),
                        sqlResultSet.getString("DESCRIPTION"),
                        sqlResultSet.getBytes("RECEIPT"),
                        sqlResultSet.getString("PAYMENT_ID"),
                        sqlResultSet.getString("AUTHOR_ID"),
                        sqlResultSet.getString("RESOLVER_ID"),
                        sqlResultSet.getString("STATUS_ID"),
                        sqlResultSet.getString("TYPE_ID")
                );

                listOfAllReimbursements.add(reimbursement);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            logger.error("Failed to get all Reimbursements from database.");
            throw new InvalidReimbursementException();
        }

        return listOfAllReimbursements;
    }

    public List<Reimbursement> getAllUserReimbursementTickets(String userId) {
        List<Reimbursement> listOfReimbursements = new ArrayList<>();

        try (Connection sqlConnection = ConnectionFactory.getInstance().getConnection()) {
//            https://www.postgresql.org/docs/current/ddl-schemas.html
            PreparedStatement preparedSqlStatement = sqlConnection.prepareStatement("SELECT * FROM p1.ERS_REIMBURSEMENTS WHERE AUTHOR_ID = ?");
            preparedSqlStatement.setString(1, userId);
            ResultSet sqlResultSet = preparedSqlStatement.executeQuery();

            while (sqlResultSet.next()) {
                Reimbursement reimbursement = new Reimbursement(
                        sqlResultSet.getString("REIMB_ID"),
                        sqlResultSet.getDouble("AMOUNT"),
                        sqlResultSet.getTimestamp("SUBMITTED"),
                        sqlResultSet.getTimestamp("RESOLVED"),
                        sqlResultSet.getString("DESCRIPTION"),
                        sqlResultSet.getBytes("RECEIPT"),
                        sqlResultSet.getString("PAYMENT_ID"),
                        sqlResultSet.getString("AUTHOR_ID"),
                        sqlResultSet.getString("RESOLVER_ID"),
                        sqlResultSet.getString("STATUS_ID"),
                        sqlResultSet.getString("TYPE_ID")
                );

                listOfReimbursements.add(reimbursement);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            logger.error("Failed to get User Reimbursements from database.");
            throw new InvalidReimbursementException();
        }

        return listOfReimbursements;
    }

    public List<Reimbursement> getAllUserReimbursementTickets(String userId, String statusId) {
        List<Reimbursement> listOfReimbursements = new ArrayList<>();

        try (Connection sqlConnection = ConnectionFactory.getInstance().getConnection()) {
//            https://www.postgresql.org/docs/current/ddl-schemas.html
            PreparedStatement preparedSqlStatement = sqlConnection.prepareStatement("SELECT * FROM p1.ERS_REIMBURSEMENTS WHERE AUTHOR_ID = ? AND STATUS_ID = ?");
            preparedSqlStatement.setString(1, userId);
            preparedSqlStatement.setString(2, statusId);
            ResultSet sqlResultSet = preparedSqlStatement.executeQuery();

            while (sqlResultSet.next()) {
                Reimbursement reimbursement = new Reimbursement(
                        sqlResultSet.getString("REIMB_ID"),
                        sqlResultSet.getDouble("AMOUNT"),
                        sqlResultSet.getTimestamp("SUBMITTED"),
                        sqlResultSet.getTimestamp("RESOLVED"),
                        sqlResultSet.getString("DESCRIPTION"),
                        sqlResultSet.getBytes("RECEIPT"),
                        sqlResultSet.getString("PAYMENT_ID"),
                        sqlResultSet.getString("AUTHOR_ID"),
                        sqlResultSet.getString("RESOLVER_ID"),
                        sqlResultSet.getString("STATUS_ID"),
                        sqlResultSet.getString("TYPE_ID")
                );

                listOfReimbursements.add(reimbursement);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            logger.error("Failed to get User Reimbursements from database.");
            throw new InvalidReimbursementException();
        }

        return listOfReimbursements;
    }

    public void approveReimbursementTicket(String reimbId, Timestamp myTime, String userId) {
        try (Connection sqlConnection = ConnectionFactory.getInstance().getConnection()) {
//            https://www.postgresql.org/docs/current/ddl-schemas.html
//            https://www.postgresql.org/docs/current/sql-update.html
            PreparedStatement preparedSqlStatement = sqlConnection.prepareStatement("UPDATE p1.ERS_REIMBURSEMENTS SET RESOLVED = ? WHERE REIMB_ID = ?");
            preparedSqlStatement.setTimestamp(1, myTime);
            preparedSqlStatement.setString(2, reimbId);
            preparedSqlStatement.executeUpdate();

            preparedSqlStatement = sqlConnection.prepareStatement("UPDATE p1.ERS_REIMBURSEMENTS SET STATUS_ID = ? WHERE REIMB_ID = ?");
            preparedSqlStatement.setString(1, "1");
            preparedSqlStatement.setString(2, reimbId);
            preparedSqlStatement.executeUpdate();

            preparedSqlStatement = sqlConnection.prepareStatement("UPDATE p1.ERS_REIMBURSEMENTS SET RESOLVER_ID = ? WHERE REIMB_ID = ?");
            preparedSqlStatement.setString(1, userId);
            preparedSqlStatement.setString(2, reimbId);
            preparedSqlStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            logger.error("Failed to get approve Reimbursement in database.");
            throw new InvalidReimbursementException();
        }
    }

    public void denyReimbursementTicket(String reimbId, Timestamp myTime, String userId) {
        try (Connection sqlConnection = ConnectionFactory.getInstance().getConnection()) {
//            https://www.postgresql.org/docs/current/ddl-schemas.html
//            https://www.postgresql.org/docs/current/sql-update.html
            PreparedStatement preparedSqlStatement = sqlConnection.prepareStatement("UPDATE p1.ERS_REIMBURSEMENTS SET RESOLVED = ? WHERE REIMB_ID = ?");
            preparedSqlStatement.setTimestamp(1, myTime);
            preparedSqlStatement.setString(2, reimbId);
            preparedSqlStatement.executeUpdate();

            preparedSqlStatement = sqlConnection.prepareStatement("UPDATE p1.ERS_REIMBURSEMENTS SET STATUS_ID = ? WHERE REIMB_ID = ?");
            preparedSqlStatement.setString(1, "2");
            preparedSqlStatement.setString(2, reimbId);
            preparedSqlStatement.executeUpdate();

            preparedSqlStatement = sqlConnection.prepareStatement("UPDATE p1.ERS_REIMBURSEMENTS SET RESOLVER_ID = ? WHERE REIMB_ID = ?");
            preparedSqlStatement.setString(1, userId);
            preparedSqlStatement.setString(2, reimbId);
            preparedSqlStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            logger.error("Failed to get deny Reimbursement in database.");
            throw new InvalidReimbursementException();
        }
    }

    public boolean getIsResolvedReimbursementTicket(String reimbId) {
        try (Connection sqlConnection = ConnectionFactory.getInstance().getConnection()) {
//            https://www.postgresql.org/docs/current/ddl-schemas.html
            PreparedStatement preparedSqlStatement = sqlConnection.prepareStatement("SELECT COUNT(REIMB_ID) FROM p1.ERS_REIMBURSEMENTS WHERE REIMB_ID = ? AND RESOLVED IS NOT NULL");
            preparedSqlStatement.setString(1, reimbId);
            ResultSet sqlResultSet = preparedSqlStatement.executeQuery();

            if (sqlResultSet.next()) {
                if (Integer.parseInt(sqlResultSet.getString("count")) == 1) {
                    return true;
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            logger.error("Failed to get unres Reimbursement in database.");
            throw new InvalidReimbursementException();
        }

        return false;
    }
}
