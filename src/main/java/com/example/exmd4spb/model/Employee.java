package com.example.exmd4spb.model;

import com.example.exmd4spb.model.dto.res.EmployeeResDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dob;
    private String phoneNum;
    private boolean gender;
    private String email;
    private String address;
    private String cityCard;

    @ManyToOne
    private Group group;

    public EmployeeResDto toEmployeeResDto(){
        return new EmployeeResDto(id, name, dob, phoneNum, gender, email, address, cityCard, group.toGroupResDto());
    }
}
