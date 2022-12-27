package com.globits.da.rest;

import com.globits.da.dto.ProvinceDTO;
import com.globits.da.service.DistrictService;
import com.globits.da.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provinceAPI")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @PostMapping("/province")
    public ProvinceDTO addProvince(@RequestBody ProvinceDTO e) {
        return provinceService.addProvince(e);
    }

    @DeleteMapping("/province/{id}")
    public String deleteProvince(@PathVariable Integer id) {
        provinceService.deleteProvinceById(id);
        return "Thành công";
    }

    @PostMapping("/provinceanddistrict")
    public ProvinceDTO addProvinceAndDistrict(@RequestBody ProvinceDTO provinceDTO) {
        ProvinceDTO result = provinceService.addProvinceAndDistrict(provinceDTO);
        return result;
    }



    @PostMapping("/provincedistrictcommune")
    public ProvinceDTO addProvinceDistrictCommune(@RequestBody ProvinceDTO provinceDTO) {
        ProvinceDTO result = provinceService.addProvinceDistrictCommune(provinceDTO);
        return result;
    }

    @PutMapping("/provinceanddistrict/{id}")
    public ProvinceDTO updateProvinceAndDistrict(@PathVariable Integer id, @RequestBody ProvinceDTO provinceDTO) {
        return provinceService.updateProvinceAndDistrict(id, provinceDTO);
    }

    @PutMapping("/provincedistrictcommune/{id}")
    public ProvinceDTO updateProvinceDistrictCommune(@PathVariable Integer id, @RequestBody ProvinceDTO provinceDTO) {
        return provinceService.updateProvinceDistrictCommune(id, provinceDTO);
    }
}
