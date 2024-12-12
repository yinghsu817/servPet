package com.servPet.meb.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MebRepository extends JpaRepository<MebVO, Integer> {
    Optional<MebVO> findByMebMail(String mebMail);
    
    
    @Query("SELECT m FROM MebVO m WHERE m.mebId = :mebId")
    MebVO findMemberById(@Param("mebId") Integer mebId);
    
    @Modifying
    @Transactional
    @Query("UPDATE MebVO m SET m.bal = m.bal - :amount WHERE m.mebId = :mebId")
    void deductBalance(@Param("mebId") Integer mebId, @Param("amount") Double amount);
}
