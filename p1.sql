--Create and set schema

--https://www.postgresql.org/docs/9.3/sql-dropschema.html
DROP SCHEMA IF EXISTS p1 CASCADE;
--https://www.postgresql.org/docs/9.3/sql-createschema.html
CREATE SCHEMA IF NOT EXISTS p1;
--https://www.postgresql.org/docs/current/ddl-schemas.html
SET search_path TO p1;

--Create tables

--https://www.postgresql.org/docs/current/sql-droptable.html
DROP TABLE IF EXISTS ERS_USER_ROLES, ERS_USERS, ERS_REIMBURSEMENT_STATUSES, ERS_REIMBURSEMENT_TYPES, ERS_REIMBURSEMENTS;
--DROP TABLE IF EXISTS ERS_USER_ROLES;
CREATE TABLE ERS_USER_ROLES (
	ROLE_ID VARCHAR(255) PRIMARY KEY,
	ROLE VARCHAR(255) UNIQUE
);
--https://www.postgresql.org/docs/8.3/tutorial-fk.html
--DROP TABLE IF EXISTS ERS_USERS;
CREATE TABLE ERS_USERS (
	USER_ID VARCHAR(255) PRIMARY KEY,
	USERNAME VARCHAR(255) UNIQUE NOT NULL,
	EMAIL VARCHAR(255) UNIQUE NOT NULL,
	PASSWORD VARCHAR(255) NOT NULL,
	GIVEN_NAME VARCHAR(255) NOT NULL,
	SURNAME VARCHAR(255) NOT NULL,
	IS_ACTIVE BOOLEAN,
	ROLE_ID VARCHAR(255) REFERENCES ERS_USER_ROLES(ROLE_ID)
);
--DROP TABLE IF EXISTS ERS_REIMBURSEMENT_STATUSES;
CREATE TABLE ERS_REIMBURSEMENT_STATUSES (
	STATUS_ID VARCHAR(255) PRIMARY KEY,
	STATUS VARCHAR(255) UNIQUE
);
--DROP TABLE IF EXISTS ERS_REIMBURSEMENT_TYPES;
CREATE TABLE ERS_REIMBURSEMENT_TYPES (
	TYPE_ID VARCHAR(255) PRIMARY KEY,
	TYPE VARCHAR(255) UNIQUE
);
--https://www.postgresql.org/docs/9.6/datatype-numeric.html
--https://www.postgresql.org/docs/9.1/datatype-binary.html
--DROP TABLE IF EXISTS ERS_REIMBURSEMENTS;
CREATE TABLE ERS_REIMBURSEMENTS (
	REIMB_ID VARCHAR(255) PRIMARY KEY,
	AMOUNT NUMERIC(6,2) NOT NULL,
	SUBMITTED TIMESTAMP NOT NULL,
	RESOLVED TIMESTAMP,
	DESCRIPTION VARCHAR(255) NOT NULL,
	RECEIPT BYTEA,
	PAYMENT_ID VARCHAR(255),
	AUTHOR_ID VARCHAR(255) NOT NULL REFERENCES ERS_USERS(USER_ID),
	RESOLVER_ID VARCHAR(255) REFERENCES ERS_USERS,
	STATUS_ID VARCHAR(255) NOT NULL REFERENCES ERS_REIMBURSEMENT_STATUSES(STATUS_ID),
	TYPE_ID VARCHAR(255) NOT NULL REFERENCES ERS_REIMBURSEMENT_TYPES(TYPE_ID)
);

--https://www.postgresql.org/docs/current/sql-insert.html
INSERT INTO ERS_USER_ROLES
(role_id, "role")
VALUES('0', 'EMPLOYEE');
INSERT INTO ERS_USER_ROLES
(role_id, "role")
VALUES('1', 'FINANCE MANAGER');
INSERT INTO ERS_USER_ROLES
(role_id, "role")
VALUES('2', 'ADMIN');

INSERT INTO ERS_REIMBURSEMENT_STATUSES
(STATUS_ID, STATUS)
VALUES('0', 'PENDING');
INSERT INTO ERS_REIMBURSEMENT_STATUSES
(STATUS_ID, STATUS)
VALUES('1', 'APPROVED');
INSERT INTO ERS_REIMBURSEMENT_STATUSES
(STATUS_ID, STATUS)
VALUES('2', 'DENIED');

INSERT INTO ERS_REIMBURSEMENT_TYPES
(TYPE_ID, TYPE)
VALUES('0', 'LODGING');
INSERT INTO ERS_REIMBURSEMENT_TYPES
(TYPE_ID, TYPE)
VALUES('1', 'TRAVEL');
INSERT INTO ERS_REIMBURSEMENT_TYPES
(TYPE_ID, TYPE)
VALUES('2', 'FOOD');
INSERT INTO ERS_REIMBURSEMENT_TYPES
(TYPE_ID, TYPE)
VALUES('3', 'OTHER');

INSERT INTO ERS_USERS
(user_id, username, email, "password", given_name, surname, is_active, role_id)
VALUES('0', 'paul219', 'paul219@pf.com', 'password', 'paul', 'franklin', true, '2');

--https://www.postgresql.org/docs/9.1/datatype-datetime.html
--https://www.postgresql.org/docs/9.3/plpython-data.html
INSERT INTO ERS_REIMBURSEMENTS
(REIMB_ID,AMOUNT,SUBMITTED,RESOLVED,DESCRIPTION,RECEIPT,PAYMENT_ID,AUTHOR_ID,RESOLVER_ID,STATUS_ID,TYPE_ID)
VALUES('0', 1.23, '2022-12-01 00:00:00', NULL, 'first reimbursement ticket description', NULL, NULL, '0', NULL, '0', '0')
