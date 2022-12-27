package com.globits.da.dto;

import com.globits.da.domain.District;
import com.globits.da.domain.Province;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DistrictDTO {
    private Integer id;
    private String name;
    private Integer province_id;
    private List<CommuneDTO> communeDTOs = new LinkedList<>();

    public DistrictDTO() {};

    public DistrictDTO(District entity) {
        if(entity != null) {
            this.setId(entity.getId());
            this.name = entity.getName();
            this.province_id = entity.getProvince().getId();
            for (CommuneDTO val : entity.getCommunes().stream().map(e -> new CommuneDTO(e)).collect(Collectors.toList())) {
                communeDTOs.add(val);
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

    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
    }

    public List<CommuneDTO> getCommuneDTOs() {
        return communeDTOs;
    }

    public void setCommuneDTOs(List<CommuneDTO> communeDTOs) {
        this.communeDTOs = communeDTOs;
    }
}
