package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("This email taken.");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new IllegalStateException("Student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);

    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException("Student with id" + studentId + " does not exist. "));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
                student.setName(name);
            }
        if (email !=null && email.length() > 0){
            Optional<Student> studentOptional  = studentRepository.findStudentByByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("Email Taken.");
            }
            student.setEmail(email);
        }
    }
}
