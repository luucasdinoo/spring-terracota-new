CREATE TABLE craftsmen(
    id CHAR(32) NOT NULL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(12) NOT NULL,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(11) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    photo_id CHAR(32) NULL,
    address_street VARCHAR(255) NULL,
    address_number VARCHAR(255) NULL,
    address_neighborhood VARCHAR(255) NULL,
    address_city VARCHAR(255) NULL,
    address_state VARCHAR(255) NULL,
    address_zip_code VARCHAR(255) NULL,
    created_at TIMESTAMP(6) NOT NULL,
    updated_at TIMESTAMP(6) NOT NULL,

    CONSTRAINT uk_craftsmen_email UNIQUE (email),
    CONSTRAINT uk_craftsmen_cpf UNIQUE (cpf),
    CONSTRAINT fk_customers_photo_id FOREIGN KEY (photo_id) REFERENCES resources (id) ON DELETE SET NULL
);