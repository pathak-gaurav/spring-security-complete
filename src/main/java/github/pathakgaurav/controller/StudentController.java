package github.pathakgaurav.controller;

import github.pathakgaurav.model.Student;
import github.pathakgaurav.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService service;

    @GetMapping("/all")
    public List<Student> findAllStudent() {
        return service.listAllStudents();
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable("id") Long id) {
        return service.findByStudentId(id);
    }

}
