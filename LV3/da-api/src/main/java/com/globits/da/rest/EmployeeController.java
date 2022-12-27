package com.globits.da.rest;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.Response;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employeeAPI")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService emService;

    @PostMapping("/employeetest")
    public void addEmployee(@RequestBody Employee e) {
        emService.addEmployee(e);
    }

    @PostMapping("/employee")
    public Response<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO e) {
        return emService.addEmployee1(e);
    }

    @GetMapping("/employee")
    public Response<List<EmployeeDTO>> getEmloyee() {
        return emService.getEmployee();
    }

    @GetMapping("/employeeId")
    public Response<EmployeeDTO> getEmployeeById(@RequestParam Integer id) {
        return emService.findEmployeeById(id);
    }
    @GetMapping("/employeeId1")
    public Employee getEmployeeById1(@RequestParam Integer id) {
        return employeeRepository.findById(id).get();
    }

    @DeleteMapping("/employee/{id}")
    public Response<Boolean> deleteEmployeeById(@PathVariable Integer id) {
        return emService.deleteEmployeeById(id);
    }

    @PutMapping("/employee/{id}")
    public Response<EmployeeDTO> updateEmployeeById(@PathVariable Integer id,@RequestBody EmployeeDTO e) {
        return emService.updateEmployeeById(id, e);
    }
}
