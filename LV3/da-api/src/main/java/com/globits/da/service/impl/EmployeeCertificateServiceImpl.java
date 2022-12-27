package com.globits.da.service.impl;

import com.globits.da.Validate.ResponseStatus;
import com.globits.da.Validate.ValidateCertificate;
import com.globits.da.domain.*;
import com.globits.da.dto.CommuneDTO;
import com.globits.da.dto.EmployeeCertificateDTO;
import com.globits.da.dto.Response;
import com.globits.da.repository.*;
import com.globits.da.service.CertificateService;
import com.globits.da.service.EmployeeCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeCertificateServiceImpl implements EmployeeCertificateService {
    @Autowired
    private CertificateRepository certificateRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeCertificateRepository employeeCertificateRepository;
    @Autowired
    private ValidateCertificate validateCertificate;

    @Override
    public Response<EmployeeCertificateDTO> addEmployeeCertificate(EmployeeCertificateDTO employeeCertificateDTO) {
        ResponseStatus status = validateCertificate.validate(null, employeeCertificateDTO);
        if(status != ResponseStatus.SUCCESS) {
            return new Response<>(status);
        }
        Employee employee = employeeRepository.findById(employeeCertificateDTO.getEmployee_id()).orElseThrow(null);
        Certificate certificate = certificateRepository.findById(employeeCertificateDTO.getCertificate_id()).orElseThrow(null);
        Province province = provinceRepository.findById(employeeCertificateDTO.getProvince_id()).orElseThrow(null);
        EmployeeCertificate employeeCertificate = new EmployeeCertificate();
        employeeCertificate.setCertificateDate(employeeCertificateDTO.getCertificateDate());
        employeeCertificate.setExpirationDate(employeeCertificateDTO.getExpirationDate());
        employeeCertificate.setCertificate(certificate);
        employeeCertificate.setEmployee(employee);
        employeeCertificate.setProvince(province);
        employeeCertificateRepository.save(employeeCertificate);

        return new Response<>(new EmployeeCertificateDTO(employeeCertificate));
    }

    @Override
    public Boolean deleteEmployeeCertificateById(Integer id) {
        if(employeeCertificateRepository.existsById(id)) {
            employeeCertificateRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Response<EmployeeCertificateDTO> updateEmployeeCertificateById(Integer id, EmployeeCertificateDTO employeeCertificateDTO) {
        ResponseStatus status = validateCertificate.validate(id, employeeCertificateDTO);
        if(status != ResponseStatus.SUCCESS) {
            return new Response<>(status);
        }

        EmployeeCertificate employeeCertificate = employeeCertificateRepository.findById(id).get();
        Employee employee = employeeRepository.findById(employeeCertificateDTO.getEmployee_id()).orElseThrow(null);
        Certificate certificate = certificateRepository.findById(employeeCertificateDTO.getCertificate_id()).orElseThrow(null);
        Province province = provinceRepository.findById(employeeCertificateDTO.getProvince_id()).orElseThrow(null);
        employeeCertificate.setCertificateDate(employeeCertificateDTO.getCertificateDate());
        employeeCertificate.setExpirationDate(employeeCertificateDTO.getExpirationDate());
        employeeCertificate.setCertificate(certificate);
        employeeCertificate.setEmployee(employee);
        employeeCertificate.setProvince(province);
        employeeCertificateRepository.save(employeeCertificate);

        return new Response<>(new EmployeeCertificateDTO(employeeCertificate));
    }
}
