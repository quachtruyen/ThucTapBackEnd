package com.globits.da.dto;

import com.globits.da.domain.Commune;
import com.globits.da.domain.District;

import java.util.stream.Collectors;

public class CommuneDTO {
    private Integer id;
    private String name;
    private Integer district_id;


    public CommuneDTO() {

    }

    public CommuneDTO(Commune entity) {
        if(entity != null) {
            this.setId(entity.getId());
            this.name = entity.getName();
            this.district_id = entity.getDistrict().getId();
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

    public Integer getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(Integer district_id) {
        this.district_id = district_id;
    }
}
