package com.globits.da.service;

import com.globits.da.domain.Commune;
import com.globits.da.dto.CommuneDTO;

import java.util.List;

public interface CommuneService {
    CommuneDTO addCommune(CommuneDTO communeDTO);
    boolean deleteCommuneById(Integer id);
    List<Commune> findCommuneByDistrictId(Integer id);
    CommuneDTO updateCommune(Integer id,CommuneDTO communeDTO);
}
