package com.example.exmd4spb.service;

import com.example.exmd4spb.model.Employee;
import com.example.exmd4spb.model.dto.req.EmployeeReqDto;
import com.example.exmd4spb.model.dto.res.EmployeeResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmployeeService {
    Page<EmployeeResDto> findAllWithSearch(String search, Long groupId, Pageable pageable);

    Employee findById(Long id);
    EmployeeResDto findDetailsById(Long id);
    void create(EmployeeReqDto EmployeeReqDto);
    void deleteById(Long id);
    void update(Long id, EmployeeReqDto EmployeeReqDto);

}
