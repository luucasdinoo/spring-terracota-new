package com.terracota.domain.stock;

import com.terracota.domain.product.Product;
import com.terracota.domain.utils.IdUtils;

import java.math.BigDecimal;

public class StockItem {

    private String id;

    private int quantity;

    private BigDecimal unitPrice;

    private Product product;

    private Stock stock;

    private StockItem(
            final String id,
            final int quantity,
            final BigDecimal unitPrice,
            final Product product,
            final Stock stock
    ) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.product = product;
        this.stock = stock;
    }

    public static StockItem newItem(
            final int quantity,
            final BigDecimal unitPrice,
            final Product product,
            final Stock stock
    ){
        return new StockItem(IdUtils.uuid(), quantity, unitPrice, product, stock);
    }

    public static StockItem with(
            final String id,
            final int quantity,
            final BigDecimal unitPrice,
            final Product product,
            final Stock stock
    ){
        return new StockItem(id, quantity, unitPrice, product, stock);
    }

    public String getId() {
        return id;
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
