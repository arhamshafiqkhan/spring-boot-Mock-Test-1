package com.arham.teacherservice.controller;

import com.arham.teacherservice.model.Teacher;
import com.arham.teacherservice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    private TeacherService service;

    @GetMapping
    public List<Teacher> getAllTeacher() {
        List<Teacher> teacherList = service.findAllTeachers();
        return teacherList;
    }

    @GetMapping("/{id}")
    public Optional<Teacher> getTeacherById(@PathVariable long id) {
        Optional<Teacher> teacher = service.findTeacherById(id);
        return teacher;
    }

    @GetMapping("/name/{name}")
    public Teacher getTeacherByName(@PathVariable String name) {
        Teacher teacher = service.getTeacherByName(name);
        return teacher;
    }

    @GetMapping("/age/{age}")
    public List<Teacher> getTeacherByAge(@PathVariable int age) {
        List<Teacher> teacherList = service.findTeacherByAge(age);
        return teacherList;
    }

    @PostMapping
    public Teacher saveTeacher(@RequestBody Teacher teacher) {
        Teacher savedTeacher = service.saveTeacher(teacher);
        return savedTeacher;
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable long id, @RequestBody Teacher teacher) {
        Teacher updatedTeacher = service.updateTeacher(id, teacher);
        return updatedTeacher;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteTeacher(@PathVariable long id) {
        boolean isDeleted = service.deleteTeacher(id);
        return isDeleted;
    }

    @GetMapping("/age/{age}/subject/{subject}")
    public List<Teacher> getTeachersByAgeAndSubject(@PathVariable int age, @PathVariable String subject) {
        List<Teacher> teacherList = service.findTeacherByAgeAndSubject(age, subject);
        return teacherList;
    }
}
