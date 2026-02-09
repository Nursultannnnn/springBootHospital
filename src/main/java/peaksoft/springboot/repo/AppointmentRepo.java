package peaksoft.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.springboot.entity.Appointment;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    // Этот метод Spring реализует сам, потому что он следует правилам именования
    // find - искать, All - все, ByHospitalId - по полю hospitalId
    List<Appointment> findAllByHospitalId(Long hospitalId);

}