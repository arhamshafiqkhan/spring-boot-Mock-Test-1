package com.arham.studentservice.repository;

import com.arham.studentservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
//    @Query(value = "SELECT * FROM Student WHERE Student.age = ?1", nativeQuery = true)
    List<Student> findAllByAge(int age);

//    @Query(value = "SELECT * FROM Student WHERE Student.hobby = ?1", nativeQuery = true)
    List<Student> findAllByHobby(String hobby);

    List<Student> findAllByTeacherIdAndIsActive(long teacherId, boolean isActive);

//    @Query(value = "SELECT * FROM Student WHERE Student.is_active = ?1", nativeQuery = true)
    List<Student> findAllByIsActive(boolean isActive);

//    @Query(value = "SELECT * FROM Student WHERE Student.id = ?1 AND Student.is_active = true", nativeQuery = true)
    Student findByIdAndIsActive(long id, boolean isActive);

//    List<Student> findByQueryParam(long id, String name, boolean isActive);


//    List<Student> findBy(long id, String name, int age, String hobby, int rollNo, long teacherId, boolean isActive);

//    @Query(value = "SELECT Student.date_of_birth FROM Student, nativeQuery = true)
//    List<Date> findAllByDateOfBirth(Date dateOfBirth);
}
