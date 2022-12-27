package com.globits.da.rest;

import com.globits.da.domain.District;
import com.globits.da.domain.Employee;
import com.globits.da.dto.DistrictDTO;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.ProvinceDTO;
import com.globits.da.service.DistrictService;
import com.globits.da.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/districtAPI")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @PostMapping("/district")
    public DistrictDTO addDistrict(@RequestBody DistrictDTO e) {
        return districtService.addDistrict(e);
    }

    @DeleteMapping("/district/{id}")
    public String deleteDistrictById(@PathVariable Integer id) {
        if(districtService.deleteDistrictById(id)) {
            return "Thành công";
        }
        return "id không tồn tại";
    }

    @GetMapping("/district/{id}")
    public List<District> findEmployeeByIdProvince(@PathVariable Integer id) {
        return districtService.findDistrictByIdProvince(id);
    }



    @PostMapping("/districtandcommune")
    public DistrictDTO addProvinceAndDistrict(@RequestBody DistrictDTO districtDTO) {
        return districtService.addDistrictAndCommune(districtDTO);
    }

    @PutMapping("/districtandcommune/{id}")
    public DistrictDTO updateDistrictandCommune(@PathVariable Integer id, @RequestBody DistrictDTO districtDTO) {
        return districtService.updateDistrictAndCommune(id, districtDTO);
    }

    @PutMapping("/district/{id}")
    public DistrictDTO updateDistrict(@PathVariable Integer id, @RequestBody DistrictDTO districtDTO) {
        return districtService.updateDistrict(id, districtDTO);
    }
}
