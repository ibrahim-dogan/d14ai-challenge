package com.example.d14aichallenge.models.http.requests;

import com.example.d14aichallenge.models.Citizen;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitizenCreateRequest {
    private String name;
    private Boolean isCitizen;
    private Boolean hasDrivingLicense;

    public Citizen toCitizen() {
        return Citizen.builder()
                .isCitizen(getIsCitizen())
                .name(getName())
                .hasDrivingLicense(getHasDrivingLicense())
                .build();
    }
}