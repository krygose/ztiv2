package com.example.zti.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginMessage(@JsonProperty("token") String token) {}