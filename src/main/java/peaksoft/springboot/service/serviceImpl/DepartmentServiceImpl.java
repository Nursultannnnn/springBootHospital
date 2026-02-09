package peaksoft.springboot.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springboot.entity.Department;
import peaksoft.springboot.repo.DepartmentRepo;
import peaksoft.springboot.repo.HospitalRepo;
import peaksoft.springboot.service.DepartmentService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepo departmentRepo;
    private final HospitalRepo hospitalRepo;

    @Override
    public List<Department> getAllDepartmentByHospitalId(Long hospitalId) {
        return departmentRepo.findAllByHospitalId(hospitalId);
    }

    // Этот метод не был в интерфейсе, но вы его использовали внутри контроллера
    public void saveDepartment(Long hospitalId, Department department) {
        // getReferenceById создает прокси-объект (быстро, без лишнего запроса в БД)
        department.setHospital(hospitalRepo.getReferenceById(hospitalId));
        departmentRepo.save(department);
    }

    @Override
    public void saveDepartment(Department department) {
        departmentRepo.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    @Override
    public Department getById(Long id) {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department with id " + id + " not found"));
    }

    @Override
    public void updateDepartment(Long id, Department newDepartment) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department with id " + id + " not found"));

        department.setName(newDepartment.getName());
        // Если есть другие поля, обновите их тут

        departmentRepo.save(department);
    }

    @Override
    public void deleteDepartment(Long id) {
        if (departmentRepo.existsById(id)) {
            departmentRepo.deleteById(id);
        } else {
            throw new RuntimeException("Department with id " + id + " not found");
        }
    }
}