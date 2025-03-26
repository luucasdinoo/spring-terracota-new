CREATE TABLE products_image(
    id CHAR(32) NOT NULL PRIMARY KEY,
    checksum VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL
);

CREATE TABLE products(
    id CHAR(32) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(4000) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    type CHAR(32) NOT NULL,
    photo_id CHAR(32) NULL,
    craftsman_id CHAR(32) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    updated_at TIMESTAMP(6) NOT NULL,

    CONSTRAINT fk_products_craftsmen_id FOREIGN KEY (craftsman_id) REFERENCES craftsmen (id) ON DELETE CASCADE
);