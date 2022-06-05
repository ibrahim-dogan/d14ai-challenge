package com.example.d14aichallenge.models.http.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String errorMessage;
}
