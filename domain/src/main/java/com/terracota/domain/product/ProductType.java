package com.terracota.domain.product;

import java.util.Arrays;
import java.util.Optional;

public enum ProductType {

    HANDMADE_JEWELRY("joias_artesanais"),
    CERAMICS("cerâmica"),
    TEXTILE_ART("arte_têxtil"),
    WOODWORK("trabalhos_em_madeira"),
    LEATHER_CRAFT("artesanato_em_couro"),
    GLASS_ART("arte_em_vidro"),
    SCULPTURE("esculturas"),
    PAINTING("pintura"),
    PAPER_CRAFT("artesanato_em_papel"),
    CROCHET_KNITTING("crocê_e_tricô"),
    METAL_ART("arte_em_metal"),
    RESIN_ART("arte_em_resina"),
    ECO_FRIENDLY_PRODUCTS("produtos_sustentáveis"),
    HANDMADE_TOYS("brinquedos_artesanais");

    private String value;

    ProductType(final String value){
        this.value = value;
    }

    public static Optional<ProductType> of(final String inputType){
        return Arrays.stream(ProductType.values())
                .filter(type -> type.value.equalsIgnoreCase(inputType))
                .findFirst();
    }

    public String getValue() {
        return value;
    }
}
