package peaksoft.springboot.service;

import peaksoft.springboot.entity.Patient;

import java.util.List;

public interface PatientService {
    void savePatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getById(Long id);

    void savePatient(Long hospitalId, Patient patient);

    void updatePatient(Long id, Patient newPatient);
    void deletePatient(Long id);
    List<Patient> getAllPatientsByHospitalId(Long hospitalId);
}
