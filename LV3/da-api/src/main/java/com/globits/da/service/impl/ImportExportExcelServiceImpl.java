package com.globits.da.service.impl;

import com.globits.da.Validate.ResponseStatus;
import com.globits.da.Validate.ValidateEmployee;
import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.Response;
import com.globits.da.dto.search.ResponseList;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.service.EmployeeService;
import com.globits.da.service.ImportExportExcelService;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class ImportExportExcelServiceImpl implements ImportExportExcelService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ValidateEmployee validateEmployee;
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;


    final int COLUMN_INDEX_ID = 0;
    final int COLUMN_INDEX_CODE = 1;
    final int COLUMN_INDEX_NAME = 2;
    final int COLUMN_INDEX_AGE = 3;
    final int COLUMN_INDEX_EMAIL = 4;
    final int COLUMN_INDEX_PHONE = 5;
    final int COLUMN_INDEX_PROVICE_ID = 6;
    final int COLUMN_INDEX_DISTRICT_ID = 7;
    final int COLUMN_INDEX_COMMUNE_ID = 8;
    private static CellStyle cellStyleFormatNumber = null;

    @Override
    public ResponseList<List<EmployeeDTO>> importExcel(String excelFilePath) throws IOException {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        // Get file
        InputStream inputStream = new FileInputStream(excelFilePath);

        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Get all rows
        Iterator<Row> rowIterator = sheet.iterator();
        ResponseList<List<EmployeeDTO>> response = new ResponseList<>(employeeDTOS);
        while (rowIterator.hasNext()) {
            Row nextRow = rowIterator.next();

            int rowIndex = nextRow.getRowNum();
            if (rowIndex == 0) {
                // Ignore header
                continue;
            }

            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // Read cells and set value for book object
            EmployeeDTO employeeDTO = new EmployeeDTO();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case COLUMN_INDEX_CODE:
                        employeeDTO.setCode((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_NAME:
                        employeeDTO.setName((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_AGE:
                        employeeDTO.setAge(new BigDecimal((double) cellValue).intValue());
                        break;
                    case COLUMN_INDEX_EMAIL:
                        employeeDTO.setEmail((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_PHONE:
                        employeeDTO.setPhoneNumber((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_PROVICE_ID:
                        employeeDTO.setProvince_id(new BigDecimal((double) cellValue).intValue());
                        break;
                    case COLUMN_INDEX_DISTRICT_ID:
                        employeeDTO.setDistrict_id(new BigDecimal((double) cellValue).intValue());
                        break;
                    case COLUMN_INDEX_COMMUNE_ID:
                        employeeDTO.setCommune_id(new BigDecimal((double) cellValue).intValue());
                        break;
                    default:
                        break;
                }

            }

            ResponseStatus status = validateEmployee.validate(null, employeeDTO);
            if (status != ResponseStatus.SUCCESS) {
                String subMessage = status.getMessage();
                String newMessage = "Row " + rowIndex + " 'false': " + subMessage;
                response.addCode(status.getCode());
                response.addMessage(newMessage);
                continue;
            } else {
                employeeDTOS.add(employeeServiceImpl.addEmployeeExcel(employeeDTO));
            }
        }

        if (response.getMessages().size() == 0) {
            response.addCode(ResponseStatus.SUCCESS.getCode());
            response.addMessage(ResponseStatus.SUCCESS.getMessage());
        }

        workbook.close();
        inputStream.close();


        return response;
    }

    @Override
    public List<EmployeeDTO> exportExcel(String excelFilePath) throws IOException {
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeDTO> employeeDTOs = new LinkedList<>();
        for (Employee employee : employees) {
            employeeDTOs.add(new EmployeeDTO(employee));
        }
        // Create Workbook
        Workbook workbook = new XSSFWorkbook();

        // Create sheet
        Sheet sheet = workbook.createSheet("Books"); // Create sheet with sheet name

        int rowIndex = 0;

        // Write header
        writeHeader(sheet, rowIndex);

        // Write data
        rowIndex++;
        for (EmployeeDTO EmployeeDTO : employeeDTOs) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeBook(EmployeeDTO, row);
            rowIndex++;
        }

        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        createOutputFile(workbook, excelFilePath);

        return employeeDTOs;
    }

    // Write header with format
    private void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);
        // Create cells

        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Id");

        cell = row.createCell(COLUMN_INDEX_CODE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Code");

        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Name");

        cell = row.createCell(COLUMN_INDEX_AGE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Age");

        cell = row.createCell(COLUMN_INDEX_EMAIL);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Email");

        cell = row.createCell(COLUMN_INDEX_PHONE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Phone number");

        cell = row.createCell(COLUMN_INDEX_PROVICE_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Province Id");

        cell = row.createCell(COLUMN_INDEX_DISTRICT_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("District id");

        cell = row.createCell(COLUMN_INDEX_COMMUNE_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Commune Id");
    }

    // Write data
    private void writeBook(EmployeeDTO employeeDTO, Row row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }

        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(employeeDTO.getId());

        cell = row.createCell(COLUMN_INDEX_CODE);
        cell.setCellValue(employeeDTO.getCode());

        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellValue(employeeDTO.getName());
        cell.setCellStyle(cellStyleFormatNumber);

        cell = row.createCell(COLUMN_INDEX_AGE);
        cell.setCellValue(employeeDTO.getAge());

        cell = row.createCell(COLUMN_INDEX_EMAIL);
        cell.setCellValue(employeeDTO.getEmail());

        cell = row.createCell(COLUMN_INDEX_PHONE);
        cell.setCellValue(employeeDTO.getPhoneNumber());

        cell = row.createCell(COLUMN_INDEX_PROVICE_ID);
        cell.setCellValue(employeeDTO.getProvince_id());


        cell = row.createCell(COLUMN_INDEX_DISTRICT_ID);
        cell.setCellValue(employeeDTO.getDistrict_id());

        cell = row.createCell(COLUMN_INDEX_COMMUNE_ID);
        cell.setCellValue(employeeDTO.getCommune_id());
    }

    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }

    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) {
        Workbook workbook = null;
        try {
            if (excelFilePath.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (excelFilePath.endsWith("xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                throw new IllegalArgumentException("The specified file is not Excel file");
            }
        } catch (IOException e) {
            System.err.println("không lấy được work book");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }
}
