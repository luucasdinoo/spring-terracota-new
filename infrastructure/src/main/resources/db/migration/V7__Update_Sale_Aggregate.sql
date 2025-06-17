ALTER TABLE sales DROP COLUMN IF EXISTS craftsman_id;

ALTER TABLE sales ALTER COLUMN payment_id SET NOT NULL;

ALTER TABLE sales ALTER COLUMN status SET NOT NULL;

DROP TABLE IF EXISTS sale_products;

CREATE TABLE sale_products (
    sale_id VARCHAR(36) NOT NULL,
    product_id CHAR(32) NOT NULL,
    PRIMARY KEY (sale_id, product_id),
    CONSTRAINT fk_sale_products_sale_id FOREIGN KEY (sale_id) REFERENCES sales(preference_id) ON DELETE CASCADE,
    CONSTRAINT fk_sale_products_product_id FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);