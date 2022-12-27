package com.globits.da.rest;

import com.globits.da.dto.CommuneDTO;
import com.globits.da.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/communeAPI")
public class CommuneController {
    @Autowired
    private CommuneService communeService;

    @PostMapping("/commune")
    public CommuneDTO addCommune(@RequestBody CommuneDTO communeDTO) {
        return communeService.addCommune(communeDTO);
    }

    @DeleteMapping("/commune/{id}")
    public String deleteCommune(@PathVariable Integer id) {
        if(communeService.deleteCommuneById(id)) {
            return "Thành công";
        }
        return "thất bại";
    }

    @PutMapping("/commune/{id}")
    public CommuneDTO updateCommune(@PathVariable Integer id,@RequestBody CommuneDTO communeDTO) {
        return communeService.updateCommune(id, communeDTO);
    }
}
