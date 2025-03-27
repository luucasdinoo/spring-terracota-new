package com.terracota.infrastructure.stock.persistence;

import com.terracota.domain.product.Product;
import com.terracota.domain.stock.Stock;
import com.terracota.domain.stock.StockItem;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "StockItem")
@Table(name = "stock_item")
public class StockItemModel {

    @Id
    private String id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(nullable = false, name = "stock_id")
    private Stock stock;

    public StockItemModel(){}

    private StockItemModel(
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

    public static StockItemModel from(final StockItem item){
        return new StockItemModel(
                item.getId(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.getProduct(),
                item.getStock()
        );
    }

    public StockItem toDomain(){
        return StockItem.with(
                getId(),
                getQuantity(),
                getUnitPrice(),
                getProduct(),
                getStock()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
