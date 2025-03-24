package com.terracota.infrastructure.api.controller.handler;

import java.time.Instant;

public record ErrorResponse(
        String title,
        String detail,
        Integer status,
        String path,
        Instant timestamp
) {
    static ErrorResponse with(
            final String title,
            final String detail,
            final Integer status,
            final String path
    ){
        return new ErrorResponse(title, detail, status, path, Instant.now());
    }
}
