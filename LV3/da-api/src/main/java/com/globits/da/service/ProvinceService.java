package com.globits.da.service;

import com.globits.da.domain.Province;
import com.globits.da.dto.DistrictDTO;
import com.globits.da.dto.ProvinceDTO;

import java.util.List;
import java.util.Optional;

public interface ProvinceService {
    ProvinceDTO addProvince(ProvinceDTO provinceDTODTO);
    void deleteProvinceById(Integer id);
    ProvinceDTO addProvinceAndDistrict(ProvinceDTO provinceDTO);
    ProvinceDTO addProvinceDistrictCommune(ProvinceDTO provinceDTO);
    ProvinceDTO updateProvinceAndDistrict(Integer id, ProvinceDTO provinceDTO);
    ProvinceDTO updateProvinceDistrictCommune(Integer id, ProvinceDTO provinceDTO);
    ProvinceDTO updateProvince(Integer id, ProvinceDTO provinceDTO);
}
