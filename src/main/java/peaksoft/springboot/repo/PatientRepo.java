package peaksoft.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.springboot.entity.Patient;
import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
    // Пример стандартного метода поиска по ID больницы
    List<Patient> findAllByHospitalId(Long hospitalId);
}