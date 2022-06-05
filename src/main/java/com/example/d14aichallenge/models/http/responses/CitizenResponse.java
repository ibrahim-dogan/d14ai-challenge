package com.example.d14aichallenge.models.http.responses;

import com.example.d14aichallenge.models.Citizen;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Data
public class CitizenResponse {

    private Long id;
    private String name;
    private Boolean isCitizen;
    private Boolean hasDrivingLicense;
    private HashMap<String, Long> children;


    public static CitizenResponse fromModel(Citizen citizen) {

        return CitizenResponse.builder()
                .id(citizen.getId())
                .name(citizen.getName())
                .isCitizen(citizen.getIsCitizen())
                .hasDrivingLicense(citizen.getHasDrivingLicense())
                .children(childrenMapper(citizen.getLinkedCitizens()))
                .build();
    }

    private static HashMap<String, Long> childrenMapper(List<Citizen> linkedCitizens) {
        HashMap<String, Long> children = new HashMap<>();
        for (Citizen child : linkedCitizens) {
            children.put(child.getName(), child.getId());
        }
        return children;
    }
}
