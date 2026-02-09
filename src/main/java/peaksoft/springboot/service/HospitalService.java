package peaksoft.springboot.service;

import peaksoft.springboot.entity.Hospital;

import java.util.List;

public interface HospitalService {
    void saveHospital (Hospital hospital);
    List<Hospital> getAllHospitals();
    Hospital getById(Long id);
    void updateHospital(Long id,Hospital newHospital);
    void deleteHospital(Long id);
}
