package peaksoft.springboot.service;

import peaksoft.springboot.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointments();
    Appointment getById(Long id);
    void updateAppointment(Long id,Appointment newAppointment);
    void deleteAppointment(Long id);
    void saveAppointment(Long hospitalId, Long patientId, Long doctorId, Long departmentId, Appointment appointment);
    List<Appointment> getAllAppointmentsByHospitalId(Long hospitalId);
}
