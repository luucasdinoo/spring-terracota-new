CREATE TABLE companies(
    id CHAR(32) NOT NULL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(12) NOT NULL,
    legal_name VARCHAR(255) NOT NULL,
    trade_name VARCHAR(255) NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    phone VARCHAR(11) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    photo_id CHAR(32) NULL,
    created_at TIMESTAMP(6) NOT NULL,
    updated_at TIMESTAMP(6) NOT NULL,

    CONSTRAINT uk_company_email UNIQUE (email),
    CONSTRAINT uk_company_cnpj UNIQUE (cnpj),
    CONSTRAINT fk_company_photo_id FOREIGN KEY (photo_id) REFERENCES resources (id) ON DELETE SET NULL
);

ALTER TABLE craftsmen ADD COLUMN company_id CHAR(32);

ALTER TABLE craftsmen ADD CONSTRAINT fk_craftsman_company FOREIGN KEY (company_id) REFERENCES companies(id);