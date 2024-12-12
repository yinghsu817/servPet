package com.servPet.vtr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servPet.vtr.model.VtrService;
import com.servPet.vtr.model.VtrVO;

@RestController
@RequestMapping("/value-trading-records")
public class VtrController {

    @Autowired
    private VtrService valueTradingRecordService;

    // 新增交易紀錄
    @PostMapping
    public VtrVO addRecord(@RequestBody VtrVO record) {
        return valueTradingRecordService.addRecord(record);
    }

    // 查詢所有交易紀錄
    @GetMapping
    public List<VtrVO> getAllRecords() {
        return valueTradingRecordService.getAllRecords();
    }

    // 根據交易編號查詢
    @GetMapping("/{id}")
    public VtrVO getRecordById(@PathVariable("id") Integer vtrId) {
        return valueTradingRecordService.getRecordById(vtrId);
    }

    // 根據會員 ID 查詢交易紀錄
    @GetMapping("/member/{memberId}")
    public List<VtrVO> getRecordsByMemberId(@PathVariable("memberId") Integer memberId) {
        return valueTradingRecordService.getRecordsByMemberId(memberId);
    }

    // 更新交易紀錄
    @PutMapping("/{id}")
    public VtrVO updateRecord(@PathVariable("id") Integer vtrId, @RequestBody VtrVO record) {
        record.setVtrId(vtrId);
        return valueTradingRecordService.updateRecord(record);
    }

    // 刪除交易紀錄
    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable("id") Integer vtrId) {
        valueTradingRecordService.deleteRecord(vtrId);
    }
}
