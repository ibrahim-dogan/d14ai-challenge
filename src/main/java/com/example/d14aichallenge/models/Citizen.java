package com.example.d14aichallenge.models;

import com.example.d14aichallenge.models.http.requests.CitizenCreateRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_citizen")
    private Boolean isCitizen;

    @Column(name = "has_driving_license")
    private Boolean hasDrivingLicense;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "parentCitizen")
    private Citizen parentCitizen;

    @OneToMany(mappedBy = "parentCitizen")
    @JsonManagedReference
    private List<Citizen> linkedCitizens;
}
