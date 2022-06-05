package com.example.d14aichallenge.controllers;


import com.example.d14aichallenge.models.Citizen;
import com.example.d14aichallenge.models.http.requests.AddChildToCitizenRequest;
import com.example.d14aichallenge.models.http.requests.CitizenCreateRequest;
import com.example.d14aichallenge.models.http.requests.CitizenUpdateRequest;
import com.example.d14aichallenge.models.http.responses.CitizenResponse;
import com.example.d14aichallenge.services.CitizenService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CitizenController {

    private final CitizenService citizenService;

    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @GetMapping("/citizens")
    List<CitizenResponse> getAll(@RequestParam(required = false) String name, @RequestParam(required = false) Boolean hasDrivingLicence, @RequestParam(required = false) Boolean isCitizen) {

        List<Citizen> citizens = citizenService.getAllCitizensFilter(name, hasDrivingLicence, isCitizen);
        return citizens.stream().map(CitizenResponse::fromModel).collect(Collectors.toList());
    }

    @GetMapping("/citizens/{id}")
    CitizenResponse getById(@PathVariable Long id) {
        Citizen citizen = citizenService.getCitizenById(id);
        return CitizenResponse.fromModel(citizen);
    }

    @PostMapping("/citizens")
    Citizen create(@RequestBody CitizenCreateRequest citizenCreateRequest) {
        return citizenService.saveCitizen(citizenCreateRequest.toCitizen());
    }

    @PostMapping("/citizens/{id}/add-child")
    Citizen addChild(@PathVariable Long id, @RequestBody AddChildToCitizenRequest addChildToCitizenRequest) {
        return citizenService.addChildToCitizen(id, addChildToCitizenRequest.getChildrenId());
    }

    @PutMapping("/citizens/{id}")
    Citizen update(@PathVariable Long id, @RequestBody CitizenUpdateRequest citizenUpdateRequest) {
        return citizenService.updateCitizen(id, citizenUpdateRequest.toCitizen());
    }

}
