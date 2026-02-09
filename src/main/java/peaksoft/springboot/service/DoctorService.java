package peaksoft.springboot.service;

import peaksoft.springboot.entity.Doctor;

import java.util.List;

public interface DoctorService {

    List<Doctor> getAllDoctors();

    Doctor getById(Long id);

    void updateDoctor(Long id, Doctor newDoctor, List<Long> departmentIds);

    void deleteDoctor(Long id);

    void saveDoctor(Long hospitalId, List<Long> departmentIds, Doctor doctor);

    List<Doctor> getAllDoctorsByHospitalId(Long hospitalId);
}
