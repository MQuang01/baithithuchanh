package com.example.exmd4spb.repository;

import com.example.exmd4spb.model.Employee;
import com.example.exmd4spb.model.dto.res.EmployeeResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT new com.example.exmd4spb.model.dto.res.EmployeeResDto" +
            "(e.id, e.name, e.dob, e.phoneNum, e.gender, e.email, e.address, e.cityCard, g) " +
            "FROM Employee e " +
            "JOIN e.group g " +
            "ON e.group = g " +
            "WHERE (:groupId is null OR g.id = :groupId) " +
            "AND (" +
                "e.name LIKE %:search% " +
                "OR g.name LIKE %:search%" +
            ") "
//            "ORDER BY e.name DESC"
    )
    Page<EmployeeResDto> findAllWithSearch(@Param("search") String search, @Param("groupId") Long groupId, Pageable pageable);

}
