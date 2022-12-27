package com.globits.da.dto;

import com.globits.da.domain.Certificate;
import com.globits.da.domain.EmployeeCertificate;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class CertificateDTO {
    private Integer id;
    private String name;
    private List<EmployeeCertificate> employeeCertificates = new LinkedList<>();

    public CertificateDTO() {};

    public CertificateDTO(Certificate entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.name = entity.getName();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeCertificate> getEmployeeCertificates() {
        return employeeCertificates;
    }

    public void setEmployeeCertificates(List<EmployeeCertificate> employeeCertificates) {
        this.employeeCertificates = employeeCertificates;
    }
}
