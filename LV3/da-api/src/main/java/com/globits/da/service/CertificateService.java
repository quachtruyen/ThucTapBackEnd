package com.globits.da.service;

import com.globits.da.domain.Certificate;
import com.globits.da.dto.CertificateDTO;

import java.util.List;
import java.util.Optional;

public interface CertificateService {
    CertificateDTO addCertificate(CertificateDTO e);
    Optional<Certificate> findCertificateById(Integer id);
    Boolean deleteCertificateById(Integer id);
    CertificateDTO updateCertificateById(Integer id, CertificateDTO eDTO);
}
