package kz.turatbekuly.amir.studentapi;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class StudentController {

    private final StudentRepository repository;

    @Value("${app.student-name}")
    private String studentName;

    @Value("${app.student-group}")
    private String studentGroup;

    @Value("${app.version}")
    private String appVersion;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> info = new LinkedHashMap<>();
        info.put("application", "Student API");
        info.put("student", studentName);
        info.put("group", studentGroup);
        info.put("version", appVersion);
        return info;
    }

    @GetMapping("/api/students")
    public List<Student> getAll() {
        return repository.findAll();
    }

    @GetMapping("/api/students/{id}")
    public Student getOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Student with id " + id + " not found"));
    }

    @PostMapping("/api/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        student.setId(null);
        return repository.save(student);
    }

    @PutMapping("/api/students/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student data) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Student with id " + id + " not found"));
        student.setFullName(data.getFullName());
        student.setGroupName(data.getGroupName());
        student.setSpecialty(data.getSpecialty());
        return repository.save(student);
    }

    @DeleteMapping("/api/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Student with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
