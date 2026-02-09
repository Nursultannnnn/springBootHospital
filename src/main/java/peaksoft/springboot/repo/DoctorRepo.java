package peaksoft.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.springboot.entity.Doctor;
import java.util.List;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    // Если у вас тут был метод getAllDoctorsByHospitalId, замените его на стандартный:
    List<Doctor> findAllByHospitalId(Long hospitalId);
}