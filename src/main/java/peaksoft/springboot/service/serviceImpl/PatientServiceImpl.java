package peaksoft.springboot.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springboot.entity.Hospital;
import peaksoft.springboot.entity.Patient;
import peaksoft.springboot.exceptions.MyException;
import peaksoft.springboot.repo.HospitalRepo;
import peaksoft.springboot.repo.PatientRepo;
import peaksoft.springboot.service.PatientService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepo patientRepo;
    private final HospitalRepo hospitalRepo;

    @Override
    public void savePatient(Long hospitalId, Patient patient) {
        // Валидация
        if (!patient.getPhoneNumber().startsWith("+996")) {
            throw new MyException("Phone number must start with +996 (e.g., +996700123456)");
        }
        if (patient.getPhoneNumber().length() != 13) {
            throw new MyException("Phone number length must be 13 symbols!");
        }

        patient.setHospital(hospitalRepo.getReferenceById(hospitalId));
        patientRepo.save(patient);
    }

    @Override
    public void updatePatient(Long id, Patient newPatient) {
        if (!newPatient.getPhoneNumber().startsWith("+996")) {
            throw new MyException("Phone number must start with +996");
        }

        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient with id " + id + " not found"));

        patient.setFirstName(newPatient.getFirstName());
        patient.setLastName(newPatient.getLastName());
        patient.setPhoneNumber(newPatient.getPhoneNumber());
        patient.setGender(newPatient.getGender());
        patient.setEmail(newPatient.getEmail());

        patientRepo.save(patient);
    }

    @Override
    public List<Patient> getAllPatientsByHospitalId(Long hospitalId) {
        return patientRepo.findAllByHospitalId(hospitalId);
    }

    @Override
    public void savePatient(Patient patient) {
        patientRepo.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    @Override
    public Patient getById(Long id) {
        return patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient with id " + id + " not found"));
    }

    @Override
    public void deletePatient(Long id) {
        if (patientRepo.existsById(id)) {
            patientRepo.deleteById(id);
        } else {
            throw new RuntimeException("Patient with id " + id + " not found");
        }
    }
}