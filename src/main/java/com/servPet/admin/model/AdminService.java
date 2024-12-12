package com.servPet.admin.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servPet.admin.hibernateUtilCompositeQuery.HibernateUtil_CompositeQuery_admin;

@Service("adminService")
public class AdminService {

    @Autowired
    AdminRepository adminrepository;

    @Autowired
    private SessionFactory sessionFactory;

    // 添加管理員
    public void addAdmin(AdminVO adminVO) {
        adminrepository.save(adminVO);  // 直接保存管理員，無加密
    }

    // 更新管理員
    public void updateAdmin(AdminVO adminVO) {
        adminrepository.save(adminVO);  // 直接保存管理員，無加密
    }

    // 根據 adminId 查詢單一管理員
    public AdminVO getOneAdmin(Integer adminId) {
        Optional<AdminVO> optional = adminrepository.findById(adminId);
        return optional.orElse(null);  // 如果存在，返回該管理員，否則返回 null
    }

    // 查詢所有管理員資料
    public List<AdminVO> getAll() {
        return adminrepository.findAll();  // 返回所有管理員資料
    }

    // 複合查詢所有管理員資料
    public List<AdminVO> getAll(Map<String, String[]> map) {
        return HibernateUtil_CompositeQuery_admin.getAllC(map, sessionFactory.openSession());
    }

    // 管理員登錄
    public AdminVO login(String adminAcc, String adminPwd) {
        AdminVO admin = adminrepository.findByadminAcc(adminAcc);
        if (admin != null && adminPwd.equals(admin.getAdminPwd())) {  // 直接比對密碼
            return admin; // 驗證成功
        } else {
            return null; // 登入失敗
        }
    }
    
}
