package com.globits.da.service;

import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.Response;
import com.globits.da.dto.search.ResponseList;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ImportExportExcelService {
    ResponseList<List<EmployeeDTO>> importExcel(String file) throws IOException;
    List<EmployeeDTO> exportExcel(String filePath) throws IOException;
}
