package com.globits.da.service.impl;

import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.dto.CommuneDTO;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommuneServiceImpl implements CommuneService {
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private CommuneRepository communeRepository;

    @Override
    public CommuneDTO addCommune(CommuneDTO communeDTO) {
        District district = districtRepository.findById(communeDTO.getDistrict_id()).orElseThrow(null);
        Commune commune = new Commune();
        commune.setName(communeDTO.getName());
        commune.setDistrict(district);
        communeRepository.save(commune);

        return new CommuneDTO(commune);
    }

    @Override
    public boolean deleteCommuneById(Integer id) {
        if(communeRepository.existsById(id)){
            communeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Commune> findCommuneByDistrictId(Integer id) {
        List<Commune> communes = communeRepository.findByDistrict_Id(id);
        return communes;
    }

    @Override
    public CommuneDTO updateCommune(Integer id, CommuneDTO communeDTO) {
        District district = districtRepository.findById(communeDTO.getDistrict_id()).get();
        Commune commune = communeRepository.findById(id).get();
        commune.setName(communeDTO.getName());
        commune.setDistrict(district);
        communeRepository.save(commune);
        return new CommuneDTO(commune);
    }
}
