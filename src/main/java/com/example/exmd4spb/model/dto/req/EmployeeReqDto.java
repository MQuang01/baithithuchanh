package com.example.exmd4spb.model.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeReqDto {
    private String name;
    private LocalDate dob;
    private String phoneNum;
    private String gender;
    private String email;
    private String address;
    private String cityCard;
    private Long groupId;
}
