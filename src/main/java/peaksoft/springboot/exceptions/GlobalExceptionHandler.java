package peaksoft.springboot.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyException.class)
    public String handleMyException(MyException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "errorPage"; // Открываем страницу errorPage.html
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDuplicate(DataIntegrityViolationException e, Model model) {
        String msg = e.getMessage();

        if (msg != null && msg.contains("departments_name_key")) {
            model.addAttribute("errorMessage", "Department with this name already exists in database!");
        } else if (msg != null && msg.contains("doctors_email_key")) {
            model.addAttribute("errorMessage", "Doctor with this email already exists!");
        } else if (msg != null && msg.contains("patients_email_key")) {
            model.addAttribute("errorMessage", "Patient with this email already exists!");
        } else {
            model.addAttribute("errorMessage", "Database error: Duplicate value found.");
        }

        return "errorPage";
    }

    @ExceptionHandler(Exception.class)
    public String handleGlobalException(Exception e, Model model) {
        model.addAttribute("errorMessage", "Something went wrong: " + e.getMessage());
        return "errorPage";
    }
}