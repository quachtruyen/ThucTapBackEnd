package com.globits.da.repository;

import com.globits.da.domain.EmployeeCertificate;
import com.globits.da.dto.EmployeeCertificateDTO;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeCertificateRepository extends JpaRepository<EmployeeCertificate, Integer> {
    List<EmployeeCertificate> findByEmployee_Id(Integer id);
}
