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
        // Используем getReferenceById (бывший getById), чтобы не делать лишний запрос в БД, если нужен только ID для связи
        appointment.setHospital(hospitalRepo.getReferenceById(hospitalId));
        appointment.setPatient(patientRepo.getReferenceById(patientId));
        appointment.setDoctor(doctorRepo.getReferenceById(doctorId));
        appointment.setDepartment(departmentRepo.getReferenceById(departmentId));

        // Стандартный метод сохранения
        appointmentRepo.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointmentsByHospitalId(Long hospitalId) {
        // Вы в репозитории назвали его findAllByHospitalId, поэтому вызываем его так же
        return appointmentRepo.findAllByHospitalId(hospitalId);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        // Стандартный метод для получения всех записей
        return appointmentRepo.findAll();
    }

    @Override
    public Appointment getById(Long id) {
        // findById возвращает Optional, поэтому нужно "распаковать" его через orElseThrow
        return appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment with id " + id + " not found"));
    }

    @Override
    public void updateAppointment(Long id, Appointment newAppointment) {
        // В JPA нет метода update. Мы ищем объект, меняем его и сохраняем.
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment with id " + id + " not found"));

        // Здесь обновляем нужные поля. Например:
        appointment.setDate(newAppointment.getDate());
        // Если есть другие поля для обновления, добавьте их сюда (например, image, description и т.д.)

        // save работает как "обновить", если у объекта уже есть ID
        appointmentRepo.save(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        // Проверяем, существует ли запись перед удалением (опционально, но полезно)
        if (appointmentRepo.existsById(id)) {
            appointmentRepo.deleteById(id);
        } else {
            throw new RuntimeException("Appointment with id " + id + " not found");
        }
    }
}