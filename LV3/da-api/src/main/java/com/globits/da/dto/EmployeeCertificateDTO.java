package com.globits.da.dto;

import com.globits.da.domain.Certificate;
import com.globits.da.domain.Employee;
import com.globits.da.domain.EmployeeCertificate;
import com.globits.da.domain.Province;

import javax.persistence.*;
import java.time.LocalDate;

public class EmployeeCertificateDTO {
    private Integer id;
    private LocalDate certificateDate;
    private LocalDate expirationDate;
    private Integer employee_id;
    private Integer certificate_id;
    private Integer province_id;


    public EmployeeCertificateDTO() {};

    public EmployeeCertificateDTO(EmployeeCertificate employeeCertificate) {
        this.id = employeeCertificate.getId();
        this.certificateDate = employeeCertificate.getCertificateDate();
        this.expirationDate = employeeCertificate.getExpirationDate();
        this.employee_id = employeeCertificate.getEmployee().getId();
        this.certificate_id = employeeCertificate.getCertificate().getId();
        this.province_id = employeeCertificate.getProvince().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getCertificateDate() {
        return certificateDate;
    }

    public void setCertificateDate(LocalDate certificateDate) {
        this.certificateDate = certificateDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Integer getCertificate_id() {
        return certificate_id;
    }

    public void setCertificate_id(Integer certificate_id) {
        this.certificate_id = certificate_id;
    }

    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
    }
}
