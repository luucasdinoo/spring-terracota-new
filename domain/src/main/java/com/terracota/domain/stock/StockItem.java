package com.terracota.domain.stock;

import com.terracota.domain.product.Product;

import java.math.BigDecimal;

public class StockItem {

    private int quantity;

    private BigDecimal unitPrice;

    private Product product;

    private Stock stock;

    private StockItem(
            final int quantity,
            final BigDecimal unitPrice,
            final Product product,
            final Stock stock
    ) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.product = product;
        this.stock = stock;
    }

    public static StockItem with(
            final int quantity,
            final BigDecimal unitPrice,
            final Product product,
            final Stock stock
    ){
        return new StockItem(quantity, unitPrice, product, stock);
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public Stock getStock() {
        return stock;
    }
}
