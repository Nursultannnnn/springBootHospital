package peaksoft.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.springboot.entity.Hospital;
import peaksoft.springboot.service.HospitalService;


@Controller
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping
    public String getAllHospitals(Model model) {
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "hospitals";
    }

    @GetMapping("/new")
    public String createHospital(Model model) {
        model.addAttribute("newHospital", new Hospital());
        return "newHospital";
    }

    @PostMapping("/save")
    public String saveHospital(@ModelAttribute("newHospital") Hospital hospital) {
        hospitalService.saveHospital(hospital);
        return "redirect:/hospitals";
    }

    @GetMapping("/delete/{id}")
    public String deleteHospital(@PathVariable("id") Long id) {
        hospitalService.deleteHospital(id);
        return "redirect:/hospitals";
    }

    @GetMapping("/edit/{id}")
    public String editHospital(@PathVariable("id") Long id, Model model) {
        model.addAttribute("hospital", hospitalService.getById(id));
        return "editHospital";
    }

    @PostMapping("/update/{id}")
    public String updateHospital(@PathVariable("id") Long id, @ModelAttribute("hospital") Hospital hospital) {
        hospitalService.updateHospital(id, hospital);
        return "redirect:/hospitals";
    }

    @GetMapping("/{id}")
    public String getHospitalById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("hospital", hospitalService.getById(id));
        return "hospitalById";
    }


}