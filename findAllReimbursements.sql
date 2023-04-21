--https://www.postgresql.org/docs/current/ddl-schemas.html
SET search_path TO p1;

SELECT * FROM ERS_REIMBURSEMENTS;
SELECT * FROM ERS_REIMBURSEMENTS WHERE RESOLVED IS NULL;
DELETE FROM ERS_REIMBURSEMENTS WHERE reimb_id != '0';
