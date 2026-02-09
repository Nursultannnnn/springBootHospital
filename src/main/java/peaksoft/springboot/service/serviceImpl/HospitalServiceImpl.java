package peaksoft.springboot.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springboot.entity.Hospital;
import peaksoft.springboot.repo.HospitalRepo;
import peaksoft.springboot.service.HospitalService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepo hospitalRepo;

    @Override
    public void saveHospital(Hospital hospital) {
        hospitalRepo.save(hospital);
    }

    @Override
    public List<Hospital> getAllHospitals() {
        return hospitalRepo.findAll();
    }

    @Override
    public Hospital getById(Long id) {
        return hospitalRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospital with id " + id + " not found"));
    }

    @Override
    public void updateHospital(Long id, Hospital newHospital) {
        Hospital hospital = hospitalRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospital with id " + id + " not found"));

        hospital.setName(newHospital.getName());
        hospital.setAddress(newHospital.getAddress());
        // Добавьте image, если есть

        hospitalRepo.save(hospital);
    }

    @Override
    public void deleteHospital(Long id) {
        if (hospitalRepo.existsById(id)) {
            hospitalRepo.deleteById(id);
        } else {
            throw new RuntimeException("Hospital with id " + id + " not found");
        }
    }
}