package com.example.exmd4spb.model.dto.res;

import com.example.exmd4spb.model.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResDto {
    private Long id;
    private String name;
    private LocalDate dob;
    private String phoneNum;
    private boolean gender;
    private String email;
    private String address;
    private String cityCard;
    private String groupName;

    private GroupResDto group;

    public EmployeeResDto(Long id, String name, LocalDate dob, String phoneNum, boolean gender, String email, String address, String cityCard , Group group) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
        this.cityCard = cityCard;
        this.gender = gender;
        this.groupName = group.getName();
        this.group = group.toGroupResDto();
    }

    public EmployeeResDto(Long id, String name, boolean gender, String phoneNum, GroupResDto group) {
        this.id = id;
        this.name = name;
        this.phoneNum = phoneNum;
        this.gender = gender;
        this.group = group;
    }

    public EmployeeResDto(Long id, String name, LocalDate dob, String phoneNum, boolean gender, String email, String address, String cityCard, GroupResDto group) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phoneNum = phoneNum;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.cityCard = cityCard;
        this.group = group;
    }

}
