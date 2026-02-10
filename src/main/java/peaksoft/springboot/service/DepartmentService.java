package peaksoft.springboot.service;

import peaksoft.springboot.entity.Department;

import java.util.List;

public interface DepartmentService {

    void saveDepartment(Long hospitalId, Department department);

    void saveDepartment(Department department);

    List<Department> getAllDepartments();

    Department getById(Long id);

    void updateDepartment(Long id, Department newDepartment);

    void deleteDepartment(Long id);

    List<Department> getAllDepartmentByHospitalId(Long hospitalId);
}