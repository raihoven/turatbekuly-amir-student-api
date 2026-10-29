package kz.turatbekuly.amir.studentapi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFullNameContainingIgnoreCase(String name);
}
