package com.arham.studentservice.VO;

import com.arham.studentservice.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseTemplateVO2 {
    private List<Teacher> teacherList;
    private List<Student> studentList;
}
