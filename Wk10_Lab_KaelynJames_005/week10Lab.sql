-- Week 10 Lab Database
CREATE DATABASE IF NOT EXISTS week10Lab;
USE week10Lab;

CREATE TABLE IF NOT EXISTS employment_form (
    id                             INT AUTO_INCREMENT PRIMARY KEY,
    full_name                      VARCHAR(255),
    current_address                VARCHAR(255),
    contact_number                 VARCHAR(50),
    email_address                  VARCHAR(255),
    highest_educational_attainment VARCHAR(255),
    gender                         VARCHAR(20),
    date_available                 DATE,
    desired_position               VARCHAR(255),
    desired_salary                 VARCHAR(50),
    authorized_to_work             BOOLEAN,
    relatives_at_company           BOOLEAN,
    relatives_explanation          VARCHAR(500),
    created_at                     DATETIME DEFAULT CURRENT_TIMESTAMP
);

SELECT * FROM employment_form;
