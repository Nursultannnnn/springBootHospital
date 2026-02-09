package peaksoft.springboot.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springboot.entity.Department;
import peaksoft.springboot.entity.Doctor;
import peaksoft.springboot.repo.DepartmentRepo;
import peaksoft.springboot.repo.DoctorRepo;
import peaksoft.springboot.repo.HospitalRepo;
import peaksoft.springboot.service.DoctorService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepo doctorRepo;
    private final HospitalRepo hospitalRepo;
    private final DepartmentRepo departmentRepo;

    @Override
    public void saveDoctor(Long hospitalId, List<Long> departmentIds, Doctor doctor) {
        doctor.setHospital(hospitalRepo.getReferenceById(hospitalId));

        List<Department> departments = new ArrayList<>();
        if (departmentIds != null) {
            for (Long deptId : departmentIds) {
                departments.add(departmentRepo.getReferenceById(deptId));
            }
        }
        doctor.setDepartments(departments);

        doctorRepo.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long hospitalId) {
        return doctorRepo.findAllByHospitalId(hospitalId);
    }

    @Override
    public void updateDoctor(Long id, Doctor newDoctor, List<Long> departmentIds) {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor with id " + id + " not found"));

        doctor.setFirstName(newDoctor.getFirstName());
        doctor.setLastName(newDoctor.getLastName());
        doctor.setPosition(newDoctor.getPosition());
        doctor.setEmail(newDoctor.getEmail());

        List<Department> departments = new ArrayList<>();
        if (departmentIds != null) {
            for (Long deptId : departmentIds) {
                departments.add(departmentRepo.getReferenceById(deptId));
            }
        }
        doctor.setDepartments(departments);

        doctorRepo.save(doctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        if (doctorRepo.existsById(id)) {
            doctorRepo.deleteById(id);
        } else {
            throw new RuntimeException("Doctor with id " + id + " not found");
        }
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    @Override
    public Doctor getById(Long id) {
        return doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor with id " + id + " not found"));
    }
}