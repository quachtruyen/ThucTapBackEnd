package com.globits.da.service.impl;

import com.globits.da.Validate.ResponseStatus;
import com.globits.da.Validate.ValidateEmployee;
import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.Response;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository emRepo;
    @Autowired
    private ValidateEmployee validateEmployee;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private CommuneRepository communeRepository;

    public EmployeeServiceImpl(EmployeeRepository emRepo, ValidateEmployee validateEmployee) {
        this.emRepo = emRepo;
        this.validateEmployee = validateEmployee;
    }

    @Override
    public void addEmployee(Employee e) {
        emRepo.save(e);
    }

    @Override
    public Response<EmployeeDTO> addEmployee1(EmployeeDTO employeeDTO) {
        ResponseStatus status = validateEmployee.validate(null, employeeDTO);
        if (status != ResponseStatus.SUCCESS) {
            return new Response<>(status);
        }
        Employee employee = new Employee();
        employee.setCode(employeeDTO.getCode());
        employee.setName(employeeDTO.getName());
        employee.setAge(employeeDTO.getAge());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setProvince(provinceRepository.findById(employeeDTO.getProvince_id()).get());
        employee.setDistrict(districtRepository.findById(employeeDTO.getDistrict_id()).get());
        employee.setCommune(communeRepository.findById(employeeDTO.getCommune_id()).get());
        emRepo.save(employee);
        return new Response<>(new EmployeeDTO(employee));
    }

    public EmployeeDTO addEmployeeExcel(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setCode(employeeDTO.getCode());
        employee.setName(employeeDTO.getName());
        employee.setAge(employeeDTO.getAge());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setProvince(provinceRepository.findById(employeeDTO.getProvince_id()).get());
        employee.setDistrict(districtRepository.findById(employeeDTO.getDistrict_id()).get());
        employee.setCommune(communeRepository.findById(employeeDTO.getCommune_id()).get());
        emRepo.save(employee);

        return new EmployeeDTO(employee);
    }

    @Override
    public Response<List<EmployeeDTO>> getEmployee() {
        List<EmployeeDTO> employeeDTOs = new LinkedList<>();
        for (Employee employee : emRepo.findAll()) {
            employeeDTOs.add(new EmployeeDTO(employee));
        }
        return new Response<>(employeeDTOs);
    }

    @Override
    public Response<EmployeeDTO> findEmployeeById(Integer id) {
        Employee employee = emRepo.findById(id).get();
        return new Response<>(new EmployeeDTO(employee));
    }

    @Override
    public Response<Boolean> deleteEmployeeById(Integer id) {
        if (id != null) {
            if (emRepo.existsById(id)) {
                emRepo.deleteById(id);
                return new Response<>(true);
            }
            return new Response<>(false, ResponseStatus.EMPLOYEE_ID_NO_EXIST);
        }

        return new Response<>(false, ResponseStatus.EMPLOYEE_ID_IS_NULL);
    }

    @Override
    public Response<EmployeeDTO> updateEmployeeById(Integer id, EmployeeDTO employeeDTO) {
        ResponseStatus status = validateEmployee.validate(id, employeeDTO);
        if (status != ResponseStatus.SUCCESS) {
            return new Response<>(employeeDTO, status);
        }
        Employee employee = emRepo.findById(id).get();
        employee.setCode(employeeDTO.getCode());
        employee.setName(employeeDTO.getName());
        employee.setAge(employeeDTO.getAge());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setProvince(provinceRepository.findById(employeeDTO.getProvince_id()).get());
        employee.setDistrict(districtRepository.findById(employeeDTO.getDistrict_id()).get());
        employee.setCommune(communeRepository.findById(employeeDTO.getCommune_id()).get());

        emRepo.save(employee);
        return new Response<>(new EmployeeDTO(employee), status);
    }
}
