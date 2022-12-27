package com.globits.da.service;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.Response;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void addEmployee(Employee e);
    Response<EmployeeDTO> addEmployee1(EmployeeDTO eDTO);
    Response<List<EmployeeDTO>> getEmployee();
    Response<EmployeeDTO> findEmployeeById(Integer id);
    Response<Boolean> deleteEmployeeById(Integer id);
    Response<EmployeeDTO> updateEmployeeById(Integer id, EmployeeDTO eDTO);
}
