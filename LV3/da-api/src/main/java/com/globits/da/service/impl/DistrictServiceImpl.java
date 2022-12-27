package com.globits.da.service.impl;

import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.CommuneDTO;
import com.globits.da.dto.DistrictDTO;
import com.globits.da.dto.ProvinceDTO;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CommuneRepository communeRepository;

    @Override
    public DistrictDTO addDistrict(DistrictDTO districtDTO) {
        Province province = provinceRepository.findById(districtDTO.getProvince_id()).orElseThrow(null);
        District dis = new District();
        dis.setName(districtDTO.getName());
        dis.setProvince(province);
        districtRepository.save(dis);
        return new DistrictDTO(dis);
    }

    @Override
    public Boolean deleteDistrictById(Integer id) {
        if(districtRepository.existsById(id)) {
            districtRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<District> findDistrictByIdProvince(Integer id) {
        List<District> district = districtRepository.findByProvince_Id(id);
        return district;
    }

    @Override
    public DistrictDTO addDistrictAndCommune(DistrictDTO districtDTO) {
        Province province = provinceRepository.findById(districtDTO.getProvince_id()).orElseThrow(null);
        District district = new District();
        district.setName(districtDTO.getName());
        district.setProvince(province);
        for (CommuneDTO i: districtDTO.getCommuneDTOs()) {
            Commune commune = new Commune();
            commune.setName(i.getName());
            commune.setDistrict(district);
            district.getCommunes().add(commune);
        }
        districtRepository.save(district);

        return new DistrictDTO(district);
    }

    @Override
    public DistrictDTO updateDistrictAndCommune(Integer id, DistrictDTO districtDTO) {
        District district = districtRepository.findById(id).get();
        district.setName(districtDTO.getName());

        for (CommuneDTO i: districtDTO.getCommuneDTOs()) {
            District districtNew = districtRepository.findById(i.getDistrict_id()).get();
            Commune commune = communeRepository.findById(i.getId()).get();
            commune.setName(i.getName());
            commune.setDistrict(districtNew);
            communeRepository.save(commune);
        }
        districtRepository.save(district);

        return new DistrictDTO(district);
    }

    @Override
    public DistrictDTO updateDistrict(Integer id, DistrictDTO districtDTO) {
        Province province = provinceRepository.findById(districtDTO.getProvince_id()).orElseThrow(null);
        District district = districtRepository.findById(id).get();
        district.setName(districtDTO.getName());
        district.setProvince(province);
        districtRepository.save(district);

        return new DistrictDTO(district);
    }
}
