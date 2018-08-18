package org.leanhis.patientmanagement;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface PatientRepository extends CrudRepository<Patient, UUID> {

    @Query("Select p from Patient p where " +
            "lower(p.name) like lower(concat('%',:patientNumberOrName,'%')) or " +
            "lower(p.patientNumber) like lower(concat('%',:patientNumberOrName,'%'))" )
    List<Patient> findByIdOrName(@Param("patientNumberOrName") String patientNumberOrName);

    Patient getById(UUID patientId);
}