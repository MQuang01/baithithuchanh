package com.example.exmd4spb.service.Impl;

import com.example.exmd4spb.model.dto.req.EmployeeReqDto;
import com.example.exmd4spb.model.dto.res.EmployeeResDto;
import com.example.exmd4spb.repository.IEmployeeRepository;
import com.example.exmd4spb.repository.IGroupRepository;
import com.example.exmd4spb.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.exmd4spb.model.Employee;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {
    private final IEmployeeRepository employeeRepository;
    private final IGroupRepository groupRepository;


    @Override
    public Page<EmployeeResDto> findAllWithSearch(String search, Long groupId, Pageable pageable) {
        search = "%" + search + "%";
        return employeeRepository.findAllWithSearch(search, groupId, pageable);
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public EmployeeResDto findDetailsById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        return employee.toEmployeeResDto();
    }

    @Override
    public void create(EmployeeReqDto employeeReqDto) {
        Employee employee = new Employee();
        employee.setName(employeeReqDto.getName());
        employee.setDob(employeeReqDto.getDob());
        employee.setPhoneNum(employeeReqDto.getPhoneNum());

        employee.setGender(employeeReqDto.getGender() == "1" ? true : false);
        employee.setEmail(employeeReqDto.getEmail());
        employee.setAddress(employeeReqDto.getAddress());
        employee.setCityCard(employeeReqDto.getCityCard());
        employee.setGroup(groupRepository.findById(employeeReqDto.getGroupId()).orElse(null));
        employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void update(Long id, EmployeeReqDto employeeReqDto) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        employee.setName(employeeReqDto.getName());
        employee.setDob(employeeReqDto.getDob());
        employee.setPhoneNum(employeeReqDto.getPhoneNum());
        employee.setGender(employeeReqDto.getGender() == "1" ? true : false);
        employee.setEmail(employeeReqDto.getEmail());
        employee.setAddress(employeeReqDto.getAddress());
        employee.setCityCard(employeeReqDto.getCityCard());
        employee.setGroup(groupRepository.findById(employeeReqDto.getGroupId()).orElse(null));
        employeeRepository.save(employee);
    }
}
