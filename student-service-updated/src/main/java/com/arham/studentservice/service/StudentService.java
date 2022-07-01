package com.arham.studentservice.service;

import com.arham.studentservice.VO.ResponseTemplateVO;
import com.arham.studentservice.VO.ResponseTemplateVO2;
import com.arham.studentservice.VO.Teacher;
import com.arham.studentservice.model.Student;
import com.arham.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static com.arham.studentservice.service.ServiceUtils.*;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    public List<Student> findAllStudents() {
        List<Student> studentList = new ArrayList<>();
        studentList = repository.findAllByIsActive(true);
        return studentList;
    }

    public Student findStudentById(long id) {
        Student student = repository.findByIdAndIsActive(id, true);
        return student;
    }

//    public List<Student> searchStudents(long id, String name) {
//        List<Student> studentList = repository.findByQueryParam(id, name, true);
//        return studentList;
//    }

//    public List<Student> searchStudents(long id, String name, int age, String hobby, int rollNo, long teacherId) {
//        List<Student> studentList = repository.findBy(id, name, age, hobby, rollNo, teacherId, true);
//        return studentList;
//    }

    public Student saveStudent(Student student) {
        Student saveStudent = new Student();
        saveStudent = repository.save(student);
        return saveStudent;
    }

    public Student updateStudent(long id, Student student) {
        Student updateStudent = repository.findByIdAndIsActive(id, true);
        if (updateStudent != null) {
            updateStudent.setName(student.getName());
            updateStudent.setAge(student.getAge());
            updateStudent.setHobby(student.getHobby());
            updateStudent.setRollNo(student.getRollNo());
            updateStudent.setDateOfBirth(student.getDateOfBirth());
            updateStudent.setActive(student.isActive());
            repository.save(updateStudent);
        }
        else {
            updateStudent = repository.save(student);
        }
        return updateStudent;
    }

//    public Student patchUpdateStudent(long id, Student student) {
//        boolean isStudentPresent = repository.findById(id).isPresent();
//        Student updateStudent = repository.findById(id).get();
//        if (isStudentPresent) {
//            updateStudent.setActive(student.isActive());
//            repository.save(updateStudent);
//        }
//        return updateStudent;
//    }

    public Boolean deleteStudent(long id) {
        boolean isDeleted = false;
        Student student;
        student = repository.findByIdAndIsActive(id, true);
        if (student != null) {
            student.setActive(false);
            repository.save(student);
            isDeleted = true;
        }
        return isDeleted;
    }

    public List<Student> findStudentsByAge(int age) {
        List<Student> studentList = new ArrayList<>();
        studentList = repository.findAllByAge(age);
        return studentList;
    }

    public List<Student> findStudentsByHobby(String hobby) {
        List<Student> studentList = new ArrayList<>();
        studentList = repository.findAllByHobby(hobby);
        return studentList;
    }

    public List<Student> getStudentsByTeacherId(long teacherId) {
        List<Student> studentList = new ArrayList<>();
        studentList = repository.findAllByTeacherIdAndIsActive(teacherId, true);
        return studentList;
    }

    public ResponseTemplateVO findStudentsByTeacherName(String name) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Teacher teacher = restTemplate.getForObject(FIND_STUDENTS_BY_TEACHER_NAME + name, Teacher.class);
        long teacherId = Objects.requireNonNull(teacher).getId();
        List<Student> studentList = getStudentsByTeacherId(teacherId);
        vo.setTeacher(teacher);
        vo.setStudentList(studentList);
        return vo;
    }

    public ResponseTemplateVO2 findStudentsTeacherWithSameAge(int age) {
        ResponseTemplateVO2 vo2 = new ResponseTemplateVO2();
        List<Student> studentList = findStudentsByAge(age);
        Teacher[] list = (restTemplate.getForObject(FIND_STUDENTS_TEACHERS_WITH_SAME_AGE + age, Teacher[].class));
        List<Teacher> teacherList = Arrays.asList(Objects.requireNonNull(list));
        vo2.setTeacherList(teacherList);
        vo2.setStudentList(studentList);
        return vo2;
    }




//    public List<Student> findStudentsByDateOfBirth(Date dateOfBirth) {
//        List<Student> studentList = repository.findAllByDateOfBirth(dateOfBirth);
//        return studentList;
//    }
}
