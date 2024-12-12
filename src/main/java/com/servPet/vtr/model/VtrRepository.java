package com.servPet.vtr.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VtrRepository extends JpaRepository<VtrVO, Integer> {
    // 根據會員 ID 查詢交易紀錄
    List<VtrVO> findByMemberId(Integer memberId);
}
