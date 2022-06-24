package com.arham.teacherservice.service;

import com.arham.teacherservice.model.Teacher;
import com.arham.teacherservice.repository.TeacherRepository;
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository repository;

    public List<Teacher> findAllTeachers() {
        List<Teacher> teacherList = repository.findAll();
        return teacherList;
    }

    public Optional<Teacher> findTeacherById(long id) {
        Optional<Teacher> teacher = repository.findById(id);
        return teacher;
    }

    public Teacher saveTeacher(Teacher teacher) {
        Teacher savedTeacher = repository.save(teacher);
        return savedTeacher;
    }

    public Teacher updateTeacher(long id, Teacher teacher) {
        Teacher updateTeacher = new Teacher();
        if (repository.findById(id).isPresent()) {
            updateTeacher.setName(teacher.getName());
            updateTeacher.setAge(teacher.getAge());
            updateTeacher.setSubject(teacher.getSubject());
            updateTeacher.setDateOfJoining(teacher.getDateOfJoining());
        }
        else {
            updateTeacher = repository.save(teacher);
        }
        return updateTeacher;
    }

    public boolean deleteTeacher(long id) {
        boolean isDeleted = false;
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    public List<Teacher> findTeacherByAgeAndSubject(int age, String subject) {
        List<Teacher> teacherList = repository.findAllByAgeAndSubject(age, subject);
        return teacherList;
    }

    public Teacher getTeacherByName(String name) {
        Teacher teacher = repository.findByName(name);
        return teacher;
    }

    public List<Teacher> findTeacherByAge(int age) {
        List<Teacher> teacherList = repository.findByAge(age);
        return teacherList;
    }
}
