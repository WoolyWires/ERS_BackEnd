# Expense Reimbursement System (ERS)

Back-end of an application that simulates a reimbursement system for employees.  
All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.
The application is setup to store and retrieve data from a database.  

## Database Setup

```sql
CREATE TABLE ers.reimbursement_status(
 	status_id INTEGER PRIMARY KEY,
 	status VARCHAR(10) NOT NULL
);

CREATE TABLE ers.reimbursement_type(
 	type_id INTEGER PRIMARY KEY,
 	type VARCHAR(10) NOT NULL
);

CREATE TABLE ers.user_roles(
 	role_id INTEGER PRIMARY KEY,
 	role VARCHAR(10) NOT NULL
);

CREATE TABLE ers.users(
 	user_id SERIAL PRIMARY KEY,
 	username VARCHAR(50) NOT NULL UNIQUE,
 	hash TEXT NOT NULL,
 	first_name VARCHAR(100) NOT NULL,
 	last_name VARCHAR(100) NOT NULL,
 	email VARCHAR(150) NOT NULL UNIQUE,
 	role_id INTEGER REFERENCES ers.user_roles(role_id)
);

CREATE TABLE ers.reimbursement(
 	reimb_id SERIAL PRIMARY KEY,
 	amount INTEGER NOT NULL,
 	submitted TIMESTAMP NOT NULL,
 	resolved TIMESTAMP,
 	description VARCHAR(250),
 	receipt_name VARCHAR(50),
 	receipt_img BYTEA,
 	author INTEGER REFERENCES ers.users(user_id),
 	resolver INTEGER REFERENCES ers.users(user_id),
 	status_id INTEGER REFERENCES ers.reimbursement_status(status_id),
 	type_id INTEGER REFERENCES ers.reimbursement_type(type_id)
);

INSERT INTO ers.reimbursement_status (status_id, status) VALUES ('0', 'pending');
INSERT INTO ers.reimbursement_status (status_id, status) VALUES ('1', 'approved');
INSERT INTO ers.reimbursement_status (status_id, status) VALUES ('2', 'denied');

INSERT INTO ers.user_roles (role_id, role) VALUES ('0', 'manager');
INSERT INTO ers.user_roles (role_id, role) VALUES ('1', 'employee');

INSERT INTO ers.reimbursement_type (type_id, type) VALUES ('0', 'lodging');
INSERT INTO ers.reimbursement_type (type_id, type) VALUES ('1', 'travel');
INSERT INTO ers.reimbursement_type (type_id, type) VALUES ('2', 'food');
INSERT INTO ers.reimbursement_type (type_id, type) VALUES ('3', 'other');

INSERT INTO ers.users (username, hash, first_name, last_name, email, role_id)
         VALUES('admin', crypt('adminpass', gen_salt('md5')), 'Admin', 'Sir', 'admin@boss.com', 0);
INSERT INTO ers.users (username, hash, first_name, last_name, email, role_id)
         VALUES('user1', crypt('user1', gen_salt('md5')), 'User', 'One', 'userone@users.com', 1);
INSERT INTO ers.users (username, hash, first_name, last_name, email, role_id)
         VALUES('user2', crypt('user2', gen_salt('md5')), 'User', 'Two', 'usertwo@users.com', 1);
```

## Built With

Spring Tool Suite - Project Management  
Java 8 - Core Application
JDBC - Database Connection
J-Unit - Unit Tests  
Log4J - Transaction Logging  
Maven - Dependency Management

## Additional Dependencies

AWS - Server Host  
PostgreSQL - Database Management  
