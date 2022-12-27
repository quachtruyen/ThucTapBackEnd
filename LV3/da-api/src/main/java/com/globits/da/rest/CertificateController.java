package com.globits.da.rest;

import com.globits.da.domain.Certificate;
import com.globits.da.dto.CertificateDTO;
import com.globits.da.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/certificateAPI")
public class CertificateController {
    @Autowired
    CertificateService certificateService;

    @PostMapping("/certificate")
    public CertificateDTO addCertificate(@RequestBody CertificateDTO certificateDTO) {
        return certificateService.addCertificate(certificateDTO);
    }

    @DeleteMapping("/certificate/{id}")
    public String deleteCertificateById(@PathVariable Integer id) {
        if(certificateService.deleteCertificateById(id)) {
            return "Thành công";
        };
        return "Thất bại";
    }

    @PutMapping("/cerfiticate/{id}")
    public CertificateDTO updateCertificateById(@PathVariable Integer id, @RequestBody CertificateDTO certificateDTO) {
        return certificateService.updateCertificateById(id, certificateDTO);
    };
}
