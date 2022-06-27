package com.arham.studentservice.controller;

import com.arham.studentservice.VO.ResponseTemplateVO;
import com.arham.studentservice.VO.ResponseTemplateVO2;
import com.arham.studentservice.VO.Teacher;
import com.arham.studentservice.model.Student;
import com.arham.studentservice.service.StudentService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sun.rmi.log.LogInputStream;

import javax.swing.text.Style;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping
    public List<Student> getAllStudents() {
        List<Student> studentList = service.findAllStudents();
        return studentList;
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable long id) {
        Student student = service.findStudentById(id);
        return student;
    }

    // Using Query Param
    @GetMapping("/search")
    public List<Student> searchStudent(@RequestParam("id") long id, @RequestParam(value = "name", required = false) String name) {
        List<Student> studentList = service.searchStudents(id, name);
        return studentList;
    }

//    @GetMapping("/search")
//    public List<Student> searchStudent(@RequestParam("id") long id, @RequestParam(value = "name", required = false) String name,
//                                 @RequestParam("age") int age, @RequestParam("hobby") String hobby,
//                                 @RequestParam("rollNo") int rollNo, @RequestParam("teacherId") long teacherId) {
//        List<Student> studentList = service.searchStudents(id, name, age, hobby, rollNo, teacherId);
//        return studentList;
//    }

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        Student savedStudent = service.saveStudent(student);
        return savedStudent;
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable long id, @PathVariable boolean isActive, @RequestBody Student student) {
        Student updatedStudent = service.updateStudent(id, isActive, student);
        return updatedStudent;
    }

    @PatchMapping("/patch/{id}")
    public Student patchUpdateStudent(@PathVariable long id, @RequestBody Student student) {
        Student updatedStudent = service.patchUpdateStudent(id, student);
        return updatedStudent;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteStudent(@PathVariable long id) {
        boolean isDeleted = service.deleteStudent(id);
        return isDeleted;
    }

    @GetMapping("/age/{age}")
    public List<Student> getStudentsByAge(@PathVariable int age) {
        List<Student> studentList = service.findStudentsByAge(age);
        return studentList;
    }

    @GetMapping("/hobby/{hobby}")
    public List<Student> getStudentsByHobby(@PathVariable String hobby) {
        List<Student> studentList = service.findStudentsByHobby(hobby);
        return studentList;
    }

    @GetMapping("/teacher/{name}")
    public ResponseTemplateVO getStudentsByTeacherName(@PathVariable String name) {
        ResponseTemplateVO vo = service.findStudentsByTeacherName(name);
        return vo;
    }

    @GetMapping("/both/{age}")
    public ResponseTemplateVO2 getStudentsTeachersWithSameAge(@PathVariable int age) {
        ResponseTemplateVO2 vo2 = service.findStudentsTeacherWithSameAge(age);
        return vo2;
    }


//    @GetMapping("/dob/{dateOfBirth}")
//    public List<Student> getStudentsByDateOfBirth(@PathVariable Date dateOfBirth) {
//        List<Student> studentList = service.findStudentsByDateOfBirth(dateOfBirth);
//        return studentList;
//    }

}
