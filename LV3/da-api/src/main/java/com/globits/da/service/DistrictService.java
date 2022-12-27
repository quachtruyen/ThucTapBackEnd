package com.globits.da.service;

import com.globits.da.domain.District;
import com.globits.da.dto.DistrictDTO;
import com.globits.da.dto.ProvinceDTO;

import java.util.List;
import java.util.Optional;

public interface DistrictService {
    DistrictDTO addDistrict(DistrictDTO eDTO);
    Boolean deleteDistrictById(Integer id);
    List<District> findDistrictByIdProvince(Integer id);
    DistrictDTO addDistrictAndCommune(DistrictDTO districtDTO);
    DistrictDTO updateDistrictAndCommune(Integer id, DistrictDTO districtDTO);
    DistrictDTO updateDistrict(Integer id, DistrictDTO districtDTO);
}
