package com.example.d14aichallenge;

import com.example.d14aichallenge.models.Citizen;
import com.example.d14aichallenge.repositories.CitizenRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SeedDatabase {

    @Bean
    public CommandLineRunner init(CitizenRepository citizenRepository) {
        return (args) -> {
            citizenRepository.deleteAll();

            Citizen john = Citizen.builder()
                    .name("John Smith")
                    .isCitizen(true)
                    .hasDrivingLicense(true)
                    .build();

            john = citizenRepository.save(john);

            citizenRepository.save(Citizen.builder()
                    .name("Mike Smith")
                    .isCitizen(true)
                    .hasDrivingLicense(false)
                    .parentCitizen(john)
                    .build());

            citizenRepository.save(Citizen.builder()
                    .name("Jessica Smith")
                    .isCitizen(true)
                    .hasDrivingLicense(false)
                    .parentCitizen(john)
                    .build());

            citizenRepository.save(Citizen.builder()
                    .name("Sarah Smith")
                    .isCitizen(true)
                    .hasDrivingLicense(false)
                    .parentCitizen(john)
                    .build());

            citizenRepository.save(Citizen.builder()
                    .name("Michael Tall")
                    .isCitizen(false)
                    .hasDrivingLicense(false)
                    .build());

            Citizen joe = Citizen.builder()
                    .name("Joe Bloggs")
                    .isCitizen(true)
                    .hasDrivingLicense(true)
                    .build();

            joe = citizenRepository.save(joe);

            citizenRepository.save(Citizen.builder()
                    .name("Sarah Bloggs")
                    .isCitizen(true)
                    .hasDrivingLicense(false)
                    .parentCitizen(joe)
                    .build());
        };
    }
}
