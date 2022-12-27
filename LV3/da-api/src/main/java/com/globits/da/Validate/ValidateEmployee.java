package com.globits.da.Validate;

import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidateEmployee {
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    CommuneRepository communeRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public ValidateEmployee() {
    }

    public ResponseStatus validate(Integer id, EmployeeDTO employeeDTO) {
        ResponseStatus response = ResponseStatus.SUCCESS;
        if(id != null && !employeeRepository.existsById(id)) {
            return ResponseStatus.EMPLOYEE_ID_NO_EXIST;
        }
        if(response != validateCode(employeeDTO.getCode())) {
            return validateCode(employeeDTO.getCode());
        }
        if (response != validateAge(employeeDTO.getAge())) {
            return validateAge(employeeDTO.getAge());
        }
        if (response != validateEmail(employeeDTO.getEmail())) {
            return validateEmail(employeeDTO.getEmail());
        }
        if (response != validatePhone(employeeDTO.getPhoneNumber())) {
            return validatePhone(employeeDTO.getPhoneNumber());
        }
        if(response != validateAddress(employeeDTO.getProvince_id(), employeeDTO.getDistrict_id(), employeeDTO.getCommune_id()))
        {
            return validateAddress(employeeDTO.getProvince_id(), employeeDTO.getDistrict_id(), employeeDTO.getCommune_id());
        }

        return response;
    }

    private ResponseStatus validateCode(String code) {
        if(!StringUtils.hasText(code)) {
            return ResponseStatus.EMPLOYEE_CODE_IS_NULL;
        }
        Pattern pattern = Pattern.compile(Regex.REGEX_CODE);
        Matcher matcher = pattern.matcher(code);
        if(!matcher.matches()) {
            return ResponseStatus.EMPLOYEE_CODE_WRONG_FORMAT;
        }
        if(employeeRepository.existsByCode(code)) {
            return ResponseStatus.EMPLOYEE_CODE_IS_EXIST;
        }

        return ResponseStatus.SUCCESS;
    }

    private ResponseStatus validateEmail(String email) {
        if(!StringUtils.hasText(email)) {
            return ResponseStatus.EMPLOYEE_EMAIL_IS_NULL;
        }
        Pattern pattern = Pattern.compile(Regex.REGEX_EMAIL);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) {
            return ResponseStatus.EMAIL_WRONG_FORMAT;
        }

        return ResponseStatus.SUCCESS;
    }

    private ResponseStatus validatePhone(String phone) {
        if(!StringUtils.hasText(phone)) {
            return ResponseStatus.EMPLOYEE_PHONE_IS_NULL;
        }
        Pattern pattern = Pattern.compile(Regex.REGEX_PHONE);
        Matcher matcher = pattern.matcher(phone);
        if(!matcher.matches()) {
            return ResponseStatus.PHONE_WRONG_FORMAT;
        }

        return ResponseStatus.SUCCESS;
    }

    private ResponseStatus validateAge(Integer age) {
        if(age == null) {
            return ResponseStatus.EMPLOYEE_AGE_IS_NULL;
        }
        if(age < 0) {
            return ResponseStatus.AGE_WRONG_FORMAT;
        }

        return ResponseStatus.SUCCESS;
    }

    private ResponseStatus validateAddress(Integer province_id,Integer district_id, Integer commune_id) {
        if(province_id == null) {
            return ResponseStatus.PROVINCE_ID_IS_NULL;
        }
        if(!provinceRepository.existsById(province_id)) {
            return ResponseStatus.PROVINCE_NOT_EXIST;
        }
        if(district_id == null) {
            return ResponseStatus.DISTRICT_ID_IS_NULL;
        }
        if(!districtRepository.existsById(district_id)) {
            return ResponseStatus.DISTRICT_NOT_EXIST;
        }

        District district = districtRepository.findById(district_id).get();
        if(!district.getProvince().getId().equals(province_id)) {
            return ResponseStatus.NOT_DISTRICT_EXIST;
        }
        if(commune_id == null) {
            return ResponseStatus.COMMUNE_ID_IS_NULL;
        }
        if(!communeRepository.existsById(commune_id)) {
            return ResponseStatus.COMMUNE_NOT_EXIST;
        }

        Commune commune = communeRepository.findById(commune_id).get();
        if(!commune.getDistrict().getId().equals(district_id)) {
            return ResponseStatus.NOT_COMMUNE_EXIST;
        }

        return ResponseStatus.SUCCESS;
    }
}
