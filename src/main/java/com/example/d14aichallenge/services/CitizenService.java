package com.example.d14aichallenge.services;

import com.example.d14aichallenge.exceptions.CitizenNotFoundException;
import com.example.d14aichallenge.models.Citizen;
import com.example.d14aichallenge.repositories.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class CitizenService {

    private final CitizenRepository citizenRepository;
    @PersistenceContext
    EntityManager em;

    @Autowired
    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    public Citizen saveCitizen(Citizen citizen) {
        return citizenRepository.save(citizen);
    }

    public Citizen addChildToCitizen(Long parentId, Long childId) {
        Citizen parent = getCitizenById(parentId);
        Citizen child = getCitizenById(childId);
        child.setParentCitizen(parent);

        return citizenRepository.save(child);
    }

    public Citizen updateCitizen(Long id, Citizen updatedCitizen) {
        return citizenRepository.findById(id).map(employee -> {
            employee.setName(updatedCitizen.getName());
            employee.setIsCitizen(updatedCitizen.getIsCitizen());
            employee.setHasDrivingLicense(updatedCitizen.getHasDrivingLicense());
            return citizenRepository.save(employee);
        }).orElseThrow(() -> new CitizenNotFoundException(id));
    }

    public Citizen getCitizenById(Long id) {
        return citizenRepository.findById(id).orElseThrow(() -> new CitizenNotFoundException(id));
    }


    public List<Citizen> getAllCitizensFilter(String name, Boolean hasDrivingLicence, Boolean isCitizen) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Citizen> query = builder.createQuery(Citizen.class);
        Root<Citizen> root = query.from(Citizen.class);

        List<Predicate> predicates = new ArrayList<>();

        if (isCitizen != null) {
            predicates.add(builder.equal(root.get("isCitizen"), isCitizen));
        }

        if (hasDrivingLicence != null) {
            predicates.add(builder.equal(root.get("hasDrivingLicense"), hasDrivingLicence));
        }

        if (name != null) {
            predicates.add(builder.like(
                    builder.lower(root.get("name")),
                    builder.lower(builder.literal("%" + name + "%"))
            ));
        }

        query.where(builder.and(predicates.toArray(new Predicate[]{})));

        return em.createQuery(query.select(root)).getResultList();
    }
}
