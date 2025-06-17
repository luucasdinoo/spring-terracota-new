CREATE TABLE sales (
    id VARCHAR(36) PRIMARY KEY,
    craftsman_id CHAR(32) NOT NULL,
    customer_id CHAR(32) NOT NULL,
    payment_method VARCHAR(255),
    nsu VARCHAR(255) NOT NULL,
    aut BIGINT NOT NULL,
    total NUMERIC(19,2) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    CONSTRAINT fk_sales_craftsman_id FOREIGN KEY (craftsman_id) REFERENCES craftsmen(id) ON DELETE CASCADE,
    CONSTRAINT fk_sales_customer_id FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE
);

CREATE TABLE sale_products (
    sale_id VARCHAR(36) NOT NULL,
    products_ids VARCHAR(255) NOT NULL,
    CONSTRAINT fk_sale_products_sale_id FOREIGN KEY (sale_id) REFERENCES sales(id) ON DELETE CASCADE
);