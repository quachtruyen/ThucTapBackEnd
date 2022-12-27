package com.globits.da.service;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeCertificateDTO;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.Response;

import java.util.List;

public interface EmployeeCertificateService {
    Response<EmployeeCertificateDTO> addEmployeeCertificate(EmployeeCertificateDTO eDTO);
    Boolean deleteEmployeeCertificateById(Integer id);
    Response<EmployeeCertificateDTO> updateEmployeeCertificateById(Integer id, EmployeeCertificateDTO eDTO);
}
