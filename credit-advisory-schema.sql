CREATE TYPE advisor_role ('ASSOCIATE', 'PARTNER', 'SENIOR');

CREATE TYPE phone_number_type ('HOME', 'WORK', 'MOBILE');

CREATE TYPE application_status ('NEW', 'ASSIGNED', 'ON_HOLD', 'APPROVED', 'CANCELED', 'DECLINED');

CREATE TYPE user_type ('ADVISOR', 'APPLICANT');


CREATE TABLE IF NOT EXISTS addresses
(
    id BIGINT AUTO_INCREMENT,
    city VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    street_number INT NOT NULL,
    zip VARCHAR(255) NOT NULL,
    apt VARCHAR(255) NOT NULL,

    CONSTRAINT addresses_PK PRIMARY KEY (id),
);

CREATE TABLE IF NOT EXISTS users
(
    id BIGINT AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    user_type user_type NOT NULL,

    CONSTRAINT users_PK PRIMARY KEY (id),
    CONSTRAINT users_username_UK UNIQUE (username),
    CONSTRAINT users_email_UK UNIQUE (email),
);

CREATE TABLE IF NOT EXISTS advisors
(
    user_id BIGINT,
    role advisor_role NOT NULL,

    CONSTRAINT advisors_PK PRIMARY KEY (user_id),
    CONSTRAINT advisors_users_FK FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS applicants
(
    user_id BIGINT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    social_security_number VARCHAR(255) NOT NULL,
    address_id BIGINT NOT NULL,

    CONSTRAINT applicants_PK PRIMARY KEY (user_id),
    CONSTRAINT applicants_users_FK FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT
    CONSTRAINT applicants_addresses_FK FOREIGN KEY (address_id) REFERENCES addresses (id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS phones
(
    id BIGINT AUTO_INCREMENT,
    phone_number VARCHAR(255) NOT NULL,
    number_type phone_number_type NOT NULL,
    applicant_id BIGINT NOT NULL,

    CONSTRAINT phones_PK PRIMARY KEY (id),
    CONSTRAINT phones_applicants_FK FOREIGN KEY (applicant_id) REFERENCES applicants (user_id) ON DELETE CASCADE
    CONSTRAINT phones_phone_number_UK UNIQUE (phone_number),
);

CREATE TABLE IF NOT EXISTS applications
(
    id BIGINT AUTO_INCREMENT,
    money DECIMAL(19, 4) NOT NULL,
    status application_status NOT NULL DEFAULT 'NEW',
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    assigned_at TIMESTAMP,
    applicant_id BIGINT NOT NULL,
    advisor_id BIGINT,

    CONSTRAINT applications_PK PRIMARY KEY (id),
    CONSTRAINT applications_applicants_FK FOREIGN KEY (applicant_id) REFERENCES applicants (user_id) ON DELETE RESTRICT,
    CONSTRAINT applications_advisors_FK FOREIGN KEY (advisor_id) REFERENCES advisors (user_id) ON DELETE RESTRICT,
);
