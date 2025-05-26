ALTER TABLE sales RENAME COLUMN id TO preference_id;

ALTER TABLE sales ALTER COLUMN preference_id TYPE VARCHAR(255);

ALTER TABLE sales ADD COLUMN payment_id BIGINT;

ALTER TABLE sales ADD COLUMN status VARCHAR(255);

ALTER TABLE sales DROP COLUMN nsu;

ALTER TABLE sales DROP COLUMN aut;

ALTER TABLE sale_products DROP CONSTRAINT IF EXISTS fk_sale_products_sale_id;

ALTER TABLE sale_products ALTER COLUMN sale_id TYPE VARCHAR(255);

ALTER TABLE sale_products
ADD CONSTRAINT fk_sale_products_sale_id
FOREIGN KEY (sale_id) REFERENCES sales(preference_id) ON DELETE CASCADE;