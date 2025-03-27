package com.terracota.application.stock.movement;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.product.Product;
import com.terracota.domain.product.ProductGateway;
import com.terracota.domain.product.ProductID;
import com.terracota.domain.stock.Stock;
import com.terracota.domain.stock.StockGateway;
import com.terracota.domain.stock.StockID;
import com.terracota.domain.stock.StockItem;

import java.util.Objects;

public class DefaultAddRemoveItemUseCase extends AddRemoveItemUseCase {

    private final StockGateway stockGateway;
    private final ProductGateway productGateway;

    public DefaultAddRemoveItemUseCase(final StockGateway stockGateway,
                                       final ProductGateway productGateway
    ) {
        this.stockGateway = Objects.requireNonNull(stockGateway);
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public void execute(final AddRemoveItemCommand input) {
        StockID stockId = StockID.from(input.stockId());
        ProductID productId = ProductID.from(input.productId());

        Stock stock = this.stockGateway.findById(stockId)
                .orElseThrow(() -> EntityNotFoundException.with(Stock.class, stockId));

        Product product = this.productGateway.findById(productId, stock.getCraftsman().getId())
                .orElseThrow(() -> EntityNotFoundException.with(Product.class, productId));

        StockItem stockItem = StockItem.newItem(
                input.quantity(),
                product.getPrice(),
                product,
                stock
        );

        switch (input.operation()){
            case "ADD" -> stock.addItem(stockItem);
            case "REMOVE" -> stock.removeItem(stockItem);
            default -> {}
        }
        this.stockGateway.save(stock);
    }
}
