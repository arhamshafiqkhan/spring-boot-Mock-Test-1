package com.arham.teacherservice.repository;

import com.arham.teacherservice.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    @Query(value = "SELECT * FROM Teacher WHERE Teacher.age = ?1 AND Teacher.subject = ?2", nativeQuery = true)

    List<Teacher> findAllByAgeAndSubject(int age, String subject);

    Teacher findByName(String name);

    List<Teacher> findByAge(int age);
}
