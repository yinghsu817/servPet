package com.servPet.vtr.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VtrService {

    @Autowired
    private VtrRepository vtrRepository;

    // 新增交易紀錄
    public VtrVO addRecord(VtrVO record) {
        return vtrRepository.save(record);
    }

    // 查詢所有交易紀錄
    public List<VtrVO> getAllRecords() {
        return vtrRepository.findAll();
    }

    // 根據 ID 查詢交易紀錄
    public VtrVO getRecordById(Integer vtrId) {
        return vtrRepository.findById(vtrId).orElse(null);
    }

    // 根據會員 ID 查詢交易紀錄
    public List<VtrVO> getRecordsByMemberId(Integer memberId) {
        return vtrRepository.findByMemberId(memberId);
    }

    // 更新交易紀錄
    public VtrVO updateRecord(VtrVO record) {
        return vtrRepository.save(record);
    }

    // 刪除交易紀錄
    public void deleteRecord(Integer vtrId) {
    	vtrRepository.deleteById(vtrId);
    }
}
