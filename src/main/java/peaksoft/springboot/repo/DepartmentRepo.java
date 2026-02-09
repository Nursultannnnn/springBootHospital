package peaksoft.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.springboot.entity.Department;
import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    // Пример стандартного метода поиска
    List<Department> findAllByHospitalId(Long hospitalId);
}