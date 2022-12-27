package com.globits.da.Validate;

public enum ResponseStatus {
    //null
    PROVINCE_ID_IS_NULL(40011, "Province id not null"),
    DISTRICT_ID_IS_NULL(40012, "District id not null"),
    COMMUNE_ID_IS_NULL(40013, "District id not null"),
    EMPLOYEE_ID_IS_NULL(40014, "District id not null"),

    PROVINCE_NAME_IS_NULL(40021, "Province name not null"),
    DISTRICT_NAME_IS_NULL(40022, "District name not null"),
    VILLAGE_NAME_IS_NULL(40023, "Village name not null"),
    EMPLOYEE_NAME_IS_NULL(40024, "Employee name not null"),
    DIPLOMA_NAME_IS_NULL(40025, "Employee name not null"),
    EMPLOYEE_CODE_IS_NULL(40026, "Employee code not null"),
    EMPLOYEE_PHONE_IS_NULL(40027, "Phone not null"),
    EMPLOYEE_AGE_IS_NULL(40028, "Age not null"),
    START_DATE_IS_NULL(40029, "StartDate not null"),
    END_DATE_IS_NULL(40030, "EndDate not null"),
    CELL_IS_NULL(40031, "Information is left blank"),
    AGE_DATA_TYPE_WRONG(40032, "Age is number"),
    EMPLOYEE_EMAIL_IS_NULL(40033, "Email not null"),

    //Exit
    CODE_IS_EXIST(40041, "Code already exists."),
    EMPLOYEE_ID_IS_EXIST(40044, "Employee id is exit"),
    EMPLOYEE_CODE_IS_EXIST(40042, "Code 'Employee' already exists."),
    DISTRICT_CODE_IS_EXIST(40045, "Code 'District' already exists."),
    VILLAGE_CODE_IS_EXIST(40046, "Code 'Village' already exists."),
    PROVINCE_CODE_IS_EXIST(40047, "Code 'Province' already exists."),
    DIPLOMA_CODE_IS_EXIST(40048, "Code 'Diploma' already exists."),
    VILLAGE_CODE_DUPLICATE(4004, "Code 'Village' matches"),
    EXCEL_EMPLOYEE_CODE_IS_EXIST(40043, "In the excel file there is the same 'Employee' Code."),
    PROVINCE_ID_NO_EXIST(40049, "Province id no exit"),
    DIPLOMA_READY_EXIST(40048, "The employee has this degree"),
    EXCEL_ERROR(40064, ""),
    HAS_DISTRICT_PROVINCE_VILLAGE_ID(40080, "Id District,Province,Village not null"),
    CERTIFICATE_IS_EXIST(4050, "The certificate already exists and is still valid"),

    // NOT EXIST,

    PROVINCE_NOT_EXIST(40051, "'province' does not exist"),

    NOT_DISTRICT_EXIST(40052, "No districts exist in the province"),
    NOT_COMMUNE_EXIST(40059, "No commune exist in the district"),

    DISTRICT_NOT_EXIST(40053, "'district' does not exist"),

    COMMUNE_NOT_EXIST(40054, "'commune' does not exist"),

    EMPLOYEE_ID_NO_EXIST(40055, "The id of 'employee' does not exist"),

    DIPLOMA_ID_NO_EXIST(40056, "The code of 'diploma' does not exist"),

    EMPLOYEE_CERTIFICATE_ID_NO_EXIST(40057, "The employee's certificate endorsement id does not exist"),

    NOT_EMPLOYEE_EXIST(40058, "No employees exist"),
    NEW_END_DATE_AFTER_TODAY(40059, "the end date must be after today"),
    ENDDATE_AFTER_STARTDATE(40060, "the end date must be after the start date"),

    //Wrong Format
    EMPLOYEE_CODE_WRONG_FORMAT(40073, "Badly formatted code (no space 'space', length 6-10 characters)"),

    EXCEL_WRONG(41111, "ERROR"),

    EMAIL_WRONG_FORMAT(40074, "EMAIL Wrong Format"),

    PHONE_WRONG_FORMAT(40075, "phone Wrong Format (11 number)"),

    AGE_WRONG_FORMAT(40076, "Age is not negative"),

    CONFLICT(40077, "Conflict occurs with input data"),

    EXPIRATION_EFFECTIVE_ERROR(40078, "The end date must be after the effective start date"),

    EXPIRATION_NOW_ERROR(40079, "The end date must be after the current creation date"),

    HAVE_3_DIPLOMA(40080, "Already have 3 diplomas of the same type that are still valid, can't add more"),

    CERTIFICATE_ALREADY_EXIST(40081, "Already have 3 certificates of the same type that are still valid, can't add more"),

    FILE_ERROR(400, "File gửi lên sai. [code, name, age, email, phone, province code, district coe, village code]"),

    SUCCESS(200, "Success !");

    private final int code;
    private String message;

    ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
