package com.globits.da.service.impl;

import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Employee;
import com.globits.da.domain.Province;
import com.globits.da.dto.CommuneDTO;
import com.globits.da.dto.DistrictDTO;
import com.globits.da.dto.ProvinceDTO;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private CommuneRepository communeRepository;

    @Override
    public ProvinceDTO addProvince(ProvinceDTO proDTO) {
        Province pro = new Province();
        pro.setName(proDTO.getName());
        provinceRepository.save(pro);
        return new ProvinceDTO(pro);
    }

    @Override
    public void deleteProvinceById(Integer id) {
        provinceRepository.deleteById(id);
    }

    @Override
    public ProvinceDTO addProvinceAndDistrict(ProvinceDTO provinceDTO) {
        Province pro = new Province();
        pro.setName(provinceDTO.getName());
        provinceRepository.save(pro);
        for (DistrictDTO i: provinceDTO.getDistrictDTOList()) {
            District dis = new District();
            dis.setName(i.getName());
            dis.setProvince(pro);
            pro.getDistricts().add(dis);
            districtRepository.save(dis);
        }
        return new ProvinceDTO(pro);
    }

    @Override
    public ProvinceDTO addProvinceDistrictCommune(ProvinceDTO provinceDTO) {
        Province pro = new Province();
        pro.setName(provinceDTO.getName());
        for (DistrictDTO districtDTO: provinceDTO.getDistrictDTOList()) {
            District district = new District();
            district.setName(districtDTO.getName());
            district.setProvince(pro);
            for (CommuneDTO communeDTO: districtDTO.getCommuneDTOs()) {
                Commune commune = new Commune();
                commune.setName(communeDTO.getName());
                commune.setDistrict(district);
                district.getCommunes().add(commune);
            }
            pro.getDistricts().add(district);
//            districtRepository.save(district);
        }
        provinceRepository.save(pro);

        return new ProvinceDTO(pro);
    }

    @Override
    public ProvinceDTO updateProvinceAndDistrict(Integer id, ProvinceDTO provinceDTO) {
        if(provinceRepository.existsById(id)) {
            Province province = provinceRepository.findById(id).get();
            province.setName(provinceDTO.getName());
            for (DistrictDTO districtDTO: provinceDTO.getDistrictDTOList()) {
                if(provinceRepository.existsById(id)) {
                    District district = districtRepository.findById(districtDTO.getId()).get();
                    district.setName(districtDTO.getName());
                    if (districtDTO.getProvince_id() != null) {
                        Province provinceNew = provinceRepository.findById(districtDTO.getProvince_id()).get();
                        district.setProvince(provinceNew);
                    }
                    districtRepository.save(district);
                }
            }
            provinceRepository.save(province);
            return new ProvinceDTO(province);
        }
        return null;
    }

    @Override
    public ProvinceDTO updateProvinceDistrictCommune(Integer id, ProvinceDTO provinceDTO) {
        Province province = provinceRepository.findById(id).orElseThrow(null);
        province.setName(provinceDTO.getName());
        provinceRepository.save(province);
        for (DistrictDTO districtDTO: provinceDTO.getDistrictDTOList()) {
            District district = districtRepository.findById(districtDTO.getId()).get();
            district.setName(districtDTO.getName());
            if(districtDTO.getProvince_id() != null) {
                Province provinceNew = provinceRepository.findById(districtDTO.getProvince_id()).get();
                district.setProvince(provinceNew);
            }
            districtRepository.save(district);
            for (CommuneDTO communeDTO: districtDTO.getCommuneDTOs()) {
                Commune commune = communeRepository.findById(communeDTO.getId()).get();
                commune.setName(communeDTO.getName());
                if(communeDTO.getDistrict_id() != null) {
                    District districtNew = districtRepository.findById(communeDTO.getDistrict_id()).orElseThrow(null);
                    commune.setDistrict(districtNew);
                }
                communeRepository.save(commune);
            }
        }

        return new ProvinceDTO(province);
    }

    @Override
    public ProvinceDTO updateProvince(Integer id, ProvinceDTO provinceDTO) {
        Province pro = provinceRepository.findById(id).get();
        pro.setName(provinceDTO.getName());
        provinceRepository.save(pro);
        return null;
    }
}
