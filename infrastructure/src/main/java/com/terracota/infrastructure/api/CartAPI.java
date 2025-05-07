package com.terracota.infrastructure.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("carts")
public interface CartAPI {

    @PostMapping
    void addItem();
}
