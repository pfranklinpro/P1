--https://www.postgresql.org/docs/current/ddl-schemas.html
SET search_path TO p1;

SELECT * FROM ers_users;
DELETE FROM ers_users WHERE username = 'testUsername';
