package com.globits.da.domain;

import javax.annotation.Priority;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "tb_province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới
    private List<District> districts = new LinkedList<>();
    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới
    private List<Employee> employees  = new LinkedList<>();
    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private List<EmployeeCertificate> employeeCertificates = new LinkedList<>();

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

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
