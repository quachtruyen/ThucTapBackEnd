package com.globits.da.repository;

import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.ProvinceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Integer> {
//    @Query(value = "select * from tb_district where province_id = :id", nativeQuery = true)
//    List<District> findByIdAndProvince(Integer id);

    //    @Query(value = "select * from tb_district where province_id = :id", nativeQuery = true)
    List<District> findByProvince_Id(Integer id);
    List<District> deleteByProvince_Id(Integer id);
}
