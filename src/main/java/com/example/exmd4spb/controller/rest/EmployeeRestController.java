package com.example.exmd4spb.controller.rest;

import com.example.exmd4spb.model.Employee;
import com.example.exmd4spb.model.dto.req.EmployeeReqDto;
import com.example.exmd4spb.model.dto.req.GroupReqDto;
import com.example.exmd4spb.model.dto.res.EmployeeResDto;
import com.example.exmd4spb.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeRestController {
    private final IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<EmployeeResDto>> findAllWithSearch(@RequestParam(required = false, defaultValue = "") String search
            , @RequestParam(required = false) Long groupId
            , @PageableDefault(sort = "name", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(employeeService.findAllWithSearch(search, groupId, pageable));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody EmployeeReqDto employeeReqDto) {
        employeeService.create(employeeReqDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody EmployeeReqDto request,
                                       @PathVariable Long id)
    {
        employeeService.update(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResDto> findById( @PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findDetailsById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
