package github.pathakgaurav.service;

import github.pathakgaurav.model.Student;
import github.pathakgaurav.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> listAllStudents(){
        if(studentRepository.findAll().isEmpty()){
            List<Student> allStudents = Arrays.asList(new Student("Zack","Anderson","zack.anderson@gmaill.com"),
                                                      new Student("Cody","Anderson","cody.anderson@gmaill.com"),
                                                      new Student("Mark","Lews","mark.lews@gmaill.com"));
            studentRepository.saveAll(allStudents);
        }
        return studentRepository.findAll();
    }

    public Student findByStudentId(Long studentId){
        return studentRepository.findById(studentId).orElse(null);
    }

    public void deleteStudent(Student student){
        studentRepository.delete(student);
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student){
        return studentRepository.save(student);
    }
}
