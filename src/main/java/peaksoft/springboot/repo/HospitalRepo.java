package peaksoft.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.springboot.entity.Hospital;

@Repository
public interface HospitalRepo extends JpaRepository<Hospital, Long> {
    // Старые методы (saveHospital и т.д.) удалите, они больше не нужны
}