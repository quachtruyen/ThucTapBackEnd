package com.globits.da.Validate;

import com.globits.da.domain.Employee;
import com.globits.da.domain.EmployeeCertificate;
import com.globits.da.dto.EmployeeCertificateDTO;
import com.globits.da.repository.EmployeeCertificateRepository;
import com.globits.da.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ValidateCertificate {
    @Autowired
    private EmployeeCertificateRepository employeeCertificateRepository;

    public ValidateCertificate() {}

    public ResponseStatus validate(Integer id, EmployeeCertificateDTO employeeCertificateDTO) {
        ResponseStatus responseStatus = ResponseStatus.SUCCESS;

        if(id != null && !employeeCertificateRepository.existsById(id)) {
            return ResponseStatus.EMPLOYEE_CERTIFICATE_ID_NO_EXIST;
        }
        if(responseStatus != validateProvince(employeeCertificateDTO)) {
            return validateProvince(employeeCertificateDTO);
        }
        if(responseStatus != validateCertificateOfTheSame(employeeCertificateDTO)) {
            return validateCertificateOfTheSame(employeeCertificateDTO);
        }

        return responseStatus;
    }

    public ResponseStatus validateProvince(EmployeeCertificateDTO employeeCertificateDTO) {
        List<EmployeeCertificate> employeeCertificates = employeeCertificateRepository.findByEmployee_Id(employeeCertificateDTO.getEmployee_id());
        LocalDate toDay = LocalDate.now();

        for (EmployeeCertificate employeeCertificate: employeeCertificates) {
            if (employeeCertificate.getProvince().getId() == employeeCertificateDTO.getProvince_id()) {
                if(!employeeCertificate.getExpirationDate().isBefore(toDay)) {
                    return ResponseStatus.CERTIFICATE_IS_EXIST;
                }
            }
        }

        return ResponseStatus.SUCCESS;
    }

    public ResponseStatus validateCertificateOfTheSame(EmployeeCertificateDTO employeeCertificateDTO) {
        List<EmployeeCertificate> employeeCertificates = employeeCertificateRepository.findByEmployee_Id(employeeCertificateDTO.getEmployee_id());
        LocalDate toDay = LocalDate.now();

        int count = 0;
        for (EmployeeCertificate employeeCertificate: employeeCertificates) {
            if (employeeCertificate.getCertificate().getId() == employeeCertificateDTO.getCertificate_id()) {
                if(employeeCertificate.getExpirationDate().isAfter(toDay)) {
                    count++;
                }
            }
        }

        if (count == 2) {
            return ResponseStatus.CERTIFICATE_ALREADY_EXIST;
        }

        return ResponseStatus.SUCCESS;
    }
}
