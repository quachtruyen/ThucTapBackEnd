package com.globits.da.dto;

import com.globits.da.domain.District;
import com.globits.da.domain.Province;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ProvinceDTO {
    private Integer id;
    private String name;
    private List<DistrictDTO> districtDTOList = new LinkedList<>();
    private List<CertificateDTO> certificateDTOS = new LinkedList<>();

    public ProvinceDTO() {};

    public ProvinceDTO(Province entity) {
        if(entity != null) {
            this.setId(entity.getId());
            this.name = entity.getName();
            for (DistrictDTO val : entity.getDistricts().stream().map(e -> new DistrictDTO(e)).collect(Collectors.toList())) {
                districtDTOList.add(val);
            }
        }
    }

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

    public List<DistrictDTO> getDistrictDTOList() {
        return districtDTOList;
    }

    public void setDistrictDTOList(List<DistrictDTO> districtDTOList) {
        this.districtDTOList = districtDTOList;
    }
}
