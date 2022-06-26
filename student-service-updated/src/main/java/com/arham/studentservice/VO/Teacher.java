package com.arham.studentservice.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Teacher {
    private long id;
    private String name;
    private int age;
    private String subject;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfJoining;
}
