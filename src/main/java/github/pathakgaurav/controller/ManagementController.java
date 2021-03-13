package github.pathakgaurav.controller;

import github.pathakgaurav.model.Student;
import github.pathakgaurav.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/management/api/v1")
@AllArgsConstructor
public class ManagementController {

    private final StudentService studentService;

    @GetMapping("/")
    public List<Student> getAllStudent() {
        return studentService.listAllStudents();
    }

    @GetMapping(path = "/{id}")
    public Student findByStudentId(@PathVariable("id") Long id) {
        Student student = studentService.findByStudentId(id);
        if (student == null) {
            throw new RuntimeException("Student Not Found");
        }
        return studentService.findByStudentId(student.getStudentId());
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteByStudentId(@PathVariable("id") Long id) {
        Student student = studentService.findByStudentId(id);
        if (student == null) {
            throw new RuntimeException("Student Not Found");
        }
        studentService.deleteStudent(student);
    }

    @PostMapping("/save")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

}
