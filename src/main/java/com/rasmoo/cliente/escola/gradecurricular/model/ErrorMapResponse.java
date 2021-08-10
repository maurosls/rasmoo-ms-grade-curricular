package com.rasmoo.cliente.escola.gradecurricular.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class ErrorMapResponse {
    private int httpStatus;
    private long timestamp;
    private Map<String,String> errors;
}
