CREATE TABLE stocks(
    id CHAR(32) PRIMARY KEY NOT NULL,
    craftsman_id CHAR(32) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    updated_at TIMESTAMP(6) NOT NULL,

    CONSTRAINT fk_stocks_craftsman_id FOREIGN KEY (craftsman_id) REFERENCES craftsmen(id) ON DELETE CASCADE
);

CREATE TABLE stocks_item(
    id CHAR(32) PRIMARY KEY NOT NULL,
    quantity INTEGER NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    product_id CHAR(32) NOT NULL,
    stock_id CHAR(32) NOT NULL,

    CONSTRAINT fk_stocks_item_product_id FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
    CONSTRAINT fk_stocks_item_stock_id FOREIGN KEY (stock_id) REFERENCES stocks (id) ON DELETE CASCADE
);