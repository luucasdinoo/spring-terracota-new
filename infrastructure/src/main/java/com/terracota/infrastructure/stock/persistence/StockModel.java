package com.terracota.infrastructure.stock.persistence;

import com.terracota.domain.stock.Stock;
import com.terracota.domain.stock.StockID;
import com.terracota.infrastructure.user.craftsman.persistence.CraftsmanModel;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity(name = "Stock")
@Table(name = "stocks")
public class StockModel {

    @Id
    private String id;

    @OneToOne
    @JoinColumn(nullable = false, name = "craftsman_id")
    private CraftsmanModel craftsman;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<StockItemModel> items;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public StockModel(){}

    private StockModel(
            final String id,
            final CraftsmanModel craftsman,
            final List<StockItemModel> items,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        this.id = id;
        this.craftsman = craftsman;
        this.items = items;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static StockModel from(final Stock stock){
        return new StockModel(
                stock.getId().getValue(),
                CraftsmanModel.from(stock.getCraftsman()),
                stock.getItems().stream()
                        .map(StockItemModel::from)
                        .toList(),
                stock.getCreatedAt(),
                stock.getUpdatedAt()
        );
    }

    public Stock toDomain(){
        return Stock.with(
                StockID.from(getId()),
                getCraftsman().toDomain(),
                getItems().stream()
                        .map(StockItemModel::toDomain)
                        .toList(),
                getCreatedAt(),
                getUpdatedAt()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CraftsmanModel getCraftsman() {
        return craftsman;
    }

    public void setCraftsman(CraftsmanModel craftsman) {
        this.craftsman = craftsman;
    }

    public List<StockItemModel> getItems() {
        return items;
    }

    public void setItems(List<StockItemModel> items) {
        this.items = items;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
