package peaksoft.springboot.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springboot.entity.Appointment;
import peaksoft.springboot.repo.*;
import peaksoft.springboot.service.AppointmentService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final HospitalRepo hospitalRepo;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final DepartmentRepo departmentRepo;

    @Override
    public void saveAppointment(Long hospitalId, Long patientId, Long doctorId, Long departmentId, Appointment appointment) {
        appointment.setHospital(hospitalRepo.getReferenceById(hospitalId));
        appointment.setPatient(patientRepo.getReferenceById(patientId));
        appointment.setDoctor(doctorRepo.getReferenceById(doctorId));
        appointment.setDepartment(departmentRepo.getReferenceById(departmentId));
        appointmentRepo.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointmentsByHospitalId(Long hospitalId) {
        return appointmentRepo.findAllByHospitalId(hospitalId);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    @Override
    public Appointment getById(Long id) {
        return appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment with id " + id + " not found"));
    }

    @Override
    public void updateAppointment(Long id, Appointment newAppointment) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment with id " + id + " not found"));

        appointment.setDate(newAppointment.getDate());

        appointmentRepo.save(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        if (appointmentRepo.existsById(id)) {
            appointmentRepo.deleteById(id);
        } else {
            throw new RuntimeException("Appointment with id " + id + " not found");
        }
    }
}