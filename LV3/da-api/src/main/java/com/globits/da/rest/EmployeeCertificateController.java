package com.globits.da.rest;

import com.globits.da.dto.CertificateDTO;
import com.globits.da.dto.EmployeeCertificateDTO;
import com.globits.da.dto.Response;
import com.globits.da.service.CertificateService;
import com.globits.da.service.EmployeeCertificateService;
import com.globits.da.service.impl.EmployeeCertificateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/EmployeeCertificateAPI")
public class EmployeeCertificateController {
    @Autowired
    EmployeeCertificateService employeeCertificateService;

    @PostMapping("/EmployeeCertificate")
    public Response<EmployeeCertificateDTO> addCertificate(@RequestBody EmployeeCertificateDTO employeeCertificateDTO) {
        return employeeCertificateService.addEmployeeCertificate(employeeCertificateDTO);
    }

    @DeleteMapping("/EmployeeCertificate/{id}")
    public String deleteCertificateById(@PathVariable Integer id) {
        if(employeeCertificateService.deleteEmployeeCertificateById(id)) {
            return "Thành công";
        };
        return "Thất bại";
    }

    @PutMapping("/EmployeeCertificate/{id}")
    public Response<EmployeeCertificateDTO> updateCertificateById(@PathVariable Integer id, @RequestBody EmployeeCertificateDTO employeeCertificateDTO) {
        return employeeCertificateService.updateEmployeeCertificateById(id, employeeCertificateDTO);
    };
}
