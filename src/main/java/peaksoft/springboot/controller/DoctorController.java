package peaksoft.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.springboot.entity.Doctor;
import peaksoft.springboot.service.DepartmentService;
import peaksoft.springboot.service.DoctorService;


import java.util.List;

@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final DepartmentService departmentService;

    @GetMapping("/{hospitalId}")
    public String getAllDoctors(@PathVariable("hospitalId") Long hospitalId, Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctorsByHospitalId(hospitalId));
        model.addAttribute("hospitalId", hospitalId);
        return "doctors";
    }

    @GetMapping("/{hospitalId}/new")
    public String createDoctor(@PathVariable("hospitalId") Long hospitalId, Model model) {
        model.addAttribute("newDoctor", new Doctor());
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("departments", departmentService.getAllDepartmentByHospitalId(hospitalId));
        return "newDoctor";
    }

    @PostMapping("/{hospitalId}/save")
    public String saveDoctor(@PathVariable("hospitalId") Long hospitalId,
                             @ModelAttribute("newDoctor") Doctor doctor,
                             @RequestParam(value = "departmentIds", required = false) List<Long> departmentIds) {
        doctorService.saveDoctor(hospitalId, departmentIds, doctor);
        return "redirect:/doctors/" + hospitalId;
    }

    @GetMapping("/delete/{hospitalId}/{id}")
    public String deleteDoctor(@PathVariable("hospitalId") Long hospitalId, @PathVariable("id") Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors/" + hospitalId;
    }

    @GetMapping("/edit/{hospitalId}/{id}")
    public String editDoctor(@PathVariable("hospitalId") Long hospitalId, @PathVariable("id") Long id, Model model) {
        Doctor doctor = doctorService.getById(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("allDepartments", departmentService.getAllDepartmentByHospitalId(hospitalId));
        return "editDoctor";
    }

    @PostMapping("/update/{hospitalId}/{id}")
    public String updateDoctor(@PathVariable("hospitalId") Long hospitalId,
                               @PathVariable("id") Long id,
                               @ModelAttribute("doctor") Doctor doctor,
                               @RequestParam(value = "departmentIds", required = false) List<Long> departmentIds) {
        doctorService.updateDoctor(id, doctor, departmentIds);
        return "redirect:/doctors/" + hospitalId;
    }
}