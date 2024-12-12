package com.servPet.pet.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PetRepository extends JpaRepository<PetVO, Integer> {
    // 可根據需求擴充查詢方法，例如透過會員 ID 查詢(改方法名
    List<PetVO> findByMebId(Integer mebId);
    
    @Query("SELECT p FROM PetVO p WHERE p.mebId = :mebId")
    List<PetVO> findPetsByMebId(@Param("mebId") Integer mebId);
}
