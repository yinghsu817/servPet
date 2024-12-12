package com.servPet.meb.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MebService {
	
	@Autowired
	private MebRepository mebRepository;
	
	public MebVO getMemberById(Integer mebId) {
        return mebRepository.findMemberById(mebId);
    }

    public void deductBalance(Integer mebId, Double amount) {
    	mebRepository.deductBalance(mebId, amount);
    }
    

//    // 根據電子郵件查找會員
//    public Optional<MebVO> findMemberByEmail(String email) {
//        return memberRepository.findByMebMail(email);
//    }
//    // 更新會員資料
//    public MebVO updateMember(MebVO updatedMember) {
//        MebVO existingMember = memberRepository.findByMebMail(updatedMember.getMebMail())
//                .orElseThrow(() -> new IllegalArgumentException("會員不存在，無法更新"));
//
//        // 只有當密碼被修改時，才進行加密
//        if (!existingMember.getMebPwd().equals(updatedMember.getMebPwd())) {
//            updatedMember.setMebPwd(passwordEncoder.encode(updatedMember.getMebPwd()));
//        }
//
//        return memberRepository.save(updatedMember);
//    }
    
}
