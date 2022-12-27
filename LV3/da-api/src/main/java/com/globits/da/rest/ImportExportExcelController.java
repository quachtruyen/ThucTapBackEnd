package com.globits.da.rest;

import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.Response;
import com.globits.da.dto.search.ResponseList;
import com.globits.da.service.ImportExportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/importExportExcelAPI")
public class ImportExportExcelController {
    @Autowired
    private ImportExportExcelService importExportExcelService;

    @PostMapping("/importExportExcel")
    public ResponseList<List<EmployeeDTO>> importExcel(@RequestParam(name = "path") String file) throws IOException {
        return importExportExcelService.importExcel(file);
    }

    @GetMapping("/importExportExcel")
    public List<EmployeeDTO> Export(@RequestParam(name = "path") String file) throws IOException {
        return importExportExcelService.exportExcel(file);
    }
}
