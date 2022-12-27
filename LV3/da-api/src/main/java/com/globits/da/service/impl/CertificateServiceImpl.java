package com.globits.da.service.impl;

import com.globits.da.domain.Certificate;
import com.globits.da.domain.Employee;
import com.globits.da.domain.Province;
import com.globits.da.dto.CertificateDTO;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.repository.*;
import com.globits.da.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    CertificateRepository certificateRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    CommuneRepository communeRepository;

    @Override
    public CertificateDTO addCertificate(CertificateDTO certificateDTO) {
        Certificate certificate = new Certificate();
        certificate.setName(certificateDTO.getName());
        certificateRepository.save(certificate);

        return new CertificateDTO(certificate);
    }

    @Override
    public Optional<Certificate> findCertificateById(Integer id) {
        return certificateRepository.findById(id);
    }

    @Override
    public Boolean deleteCertificateById(Integer id) {
        if(certificateRepository.existsById(id)){
            certificateRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public CertificateDTO updateCertificateById(Integer id, CertificateDTO certificateDTO) {
//        Province province = provinceRepository.findById(certificateDTO.getProvince_id()).get();
        Certificate certificate = certificateRepository.findById(id).get();
        certificate.setName(certificateDTO.getName());
//        certificate.setCertificateDate(certificateDTO.getCertificateDate());
//        certificate.setExpirationDate(certificateDTO.getExpirationDate());
//        for (EmployeeDTO employeeDTO : certificateDTO.getEmployeeDTOS()) {
//
//            Employee employee = new Employee();
//            employee.setCode(employeeDTO.getCode());
//            employee.setName(employeeDTO.getName());
//            employee.setAge(employeeDTO.getAge());
//            employee.setEmail(employeeDTO.getEmail());
//            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
//            employee.setProvince(provinceRepository.findById(employeeDTO.getProvince_id()).get());
//            employee.setDistrict(districtRepository.findById(employeeDTO.getDistrict_id()).get());
//            employee.setCommune(communeRepository.findById(employeeDTO.getCommune_id()).get());
//            certificate.getEmployees().add(employee);
//        }
//        certificate.setProvince(province);
        certificateRepository.save(certificate);

        return new CertificateDTO(certificate);
    }
}
