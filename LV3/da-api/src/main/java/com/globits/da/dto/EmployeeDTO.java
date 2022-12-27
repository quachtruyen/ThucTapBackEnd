package com.globits.da.dto;

import com.globits.da.domain.District;
import com.globits.da.domain.Employee;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDTO {
    private Integer id;
    private String code;
    private String name;
    private Integer age;
    private String email;
    private String phoneNumber;
    private Integer province_id;
    private Integer district_id;
    private Integer commune_id;
    private List<CertificateDTO> certificateDTOS = new LinkedList<>();

    public EmployeeDTO() {};

    public EmployeeDTO(Employee entity) {
        if(entity != null) {
            this.setId(entity.getId());
            this.code = entity.getCode();
            this.name = entity.getName();
            this.age = entity.getAge();
            this.email = entity.getEmail();
            this.phoneNumber = entity.getPhoneNumber();
            this.province_id = entity.getProvince().getId();
            this.district_id = entity.getDistrict().getId();
            this.commune_id = entity.getCommune().getId();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
    }

    public Integer getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(Integer district_id) {
        this.district_id = district_id;
    }

    public Integer getCommune_id() {
        return commune_id;
    }

    public void setCommune_id(Integer commune_id) {
        this.commune_id = commune_id;
    }
}
