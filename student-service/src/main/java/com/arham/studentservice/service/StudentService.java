package com.arham.studentservice.service;

import com.arham.studentservice.VO.ResponseTemplateVO;
import com.arham.studentservice.VO.ResponseTemplateVO2;
import com.arham.studentservice.VO.Teacher;
import com.arham.studentservice.model.Student;
import com.arham.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    public List<Student> findAllStudents() {
        List<Student> studentList = repository.findAll();
        return studentList;
    }

    public Optional<Student> findStudentById(long id) {
        Optional<Student> student = repository.findById(id);
        return student;
    }

    public Student saveStudent(Student student) {
        Student savedStudent = repository.save(student);
        return savedStudent;
    }

    public Student updateStudent(long id, Student student) {
        boolean isStudentPresent = repository.findById(id).isPresent();
        Student updateStudent = repository.findById(id).get();
        if (isStudentPresent) {
            updateStudent.setName(student.getName());
            updateStudent.setAge(student.getAge());
            updateStudent.setHobby(student.getHobby());
            updateStudent.setRollNo(student.getRollNo());
            updateStudent.setDateOfBirth(student.getDateOfBirth());
            repository.save(updateStudent);
        }
        else {
            updateStudent = repository.save(student);
        }
        return updateStudent;
    }

    public Boolean deleteStudent(long id) {
        boolean isDeleted = false;
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    public List<Student> findStudentsByAge(int age) {
        List<Student> studentList = repository.findAllByAge(age);
        return studentList;
    }

    public List<Student> findStudentsByHobby(String hobby) {
        List<Student> studentList = repository.findAllByHobby(hobby);
        return studentList;
    }

    public List<Student> getStudentsByTeacherId(long teacherId) {
        List<Student> studentList = repository.findAllByTeacherId(teacherId);
        return studentList;
    }

    public ResponseTemplateVO findStudentsByTeacherName(String name) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Teacher teacher = restTemplate.getForObject("http://localhost:8091/api/teacher/name/" + name, Teacher.class);
        long teacherId = teacher.getId();
        List<Student> studentList = getStudentsByTeacherId(teacherId);
        vo.setTeacher(teacher);
        vo.setStudentList(studentList);
        return vo;
    }

    public ResponseTemplateVO2 findStudentsTeacherWithSameAge(int age) {
        ResponseTemplateVO2 vo2 = new ResponseTemplateVO2();
        List<Student> studentList = findStudentsByAge(age);
        Teacher[] list = (restTemplate.getForObject("http://localhost:8091/api/teacher/age/" + age, Teacher[].class));
        List<Teacher> teacherList = Arrays.asList(list);
        vo2.setTeacherList(teacherList);
        vo2.setStudentList(studentList);
        return vo2;
    }

//    public List<Student> findStudentsByDateOfBirth(Date dateOfBirth) {
//        List<Student> studentList = repository.findAllByDateOfBirth(dateOfBirth);
//        return studentList;
//    }
}
