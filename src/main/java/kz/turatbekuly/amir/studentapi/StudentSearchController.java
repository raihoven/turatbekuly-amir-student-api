package kz.turatbekuly.amir.studentapi;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentSearchController {

    private final StudentRepository repository;

    public StudentSearchController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/students/search")
    public List<Student> search(@RequestParam String name) {
        return repository.findByFullNameContainingIgnoreCase(name);
    }
}
