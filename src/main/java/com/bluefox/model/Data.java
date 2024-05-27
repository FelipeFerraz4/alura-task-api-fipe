package com.bluefox.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Data(@JsonAlias("codigo") String id,
    @JsonAlias("nome") String name) {
}
