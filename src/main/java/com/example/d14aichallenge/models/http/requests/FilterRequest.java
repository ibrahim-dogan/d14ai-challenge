package com.example.d14aichallenge.models.http.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterRequest {
    private String name;
    private Boolean isCitizen;
    private Boolean hasDrivingLicense;
}