package com.servPet.meb.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.servPet.meb.model.MebService;
import com.servPet.meb.model.MebVO;

@Controller
//@RequestMapping("/front_end")
public class MebController {
	
//	private final MebService memberService;

//	public MebController(MebService memberService) {
//		this.memberService = memberService;
//	}
////註冊
//	@GetMapping("front_end/register")
//	public String showRegisterForm(Model model) {
//		model.addAttribute("member", new MebVO());
//		return "front_end/register";
//	}
//
//	@PostMapping("front_end/register")
//	public String registerMember(@ModelAttribute MebVO member) {
//		memberService.registerMember(member);
//		return "front_end/login";
//	}
//
//	
//	
//	@GetMapping("front_end/profile")
//	public String showProfile(Model model, Principal principal) {
//	    // 從 Spring Security 獲取當前登入用戶的電子郵件
////	    System.out.println("Principal: " + principal);
//	    String email = principal.getName();
//	    // 從資料庫中查詢會員資料
//	    Optional<MebVO> member = memberService.findMemberByEmail(email);
//	    // 添加會員資料到模型
//	    model.addAttribute("member", member);
//
//	    return "front_end/profile";
//	}
//	
////	@GetMapping("/dashboard")
////	@ResponseBody // 確保返回 JSON
////	public Map<String, Object> getDashboard(Principal principal) {
////	    Map<String, Object> response = new HashMap<>();
////	    try {
////	        String email = principal.getName();
////	        Optional<MebVO> member = memberService.findMemberByEmail(email);
////
////	        response.put("status", "success");
////	        response.put("member", member);
////	    } catch (Exception e) {
////	        response.put("status", "error");
////	        response.put("message", "會員資料加載失敗");
////	    }
////	    return response;
////	}
//	
//	@PostMapping("/profile/update")
//	public String updateProfile(@ModelAttribute MebVO member, @RequestParam("mebPhoto") MultipartFile file) {
//	    try {
//	        if (!file.isEmpty()) {
//	            member.setMebImg(file.getBytes()); // 將圖片二進制數據存入 mebImg
//	        }
//	        memberService.updateMember(member); // 更新會員資料
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	    return "redirect:/front_end/profile";
//	}
//
//
//	@GetMapping("front_end/index")
//	public String geIndex(Model model, Principal principal) {
//	    if (principal == null) {
//	        // 如果 Principal 為空，可能是會話已過期或未登錄，重定向到登錄頁面
//	        return "/front_end/index";
//	    }
//	    try {
//	        String email = principal.getName();
//	        Optional<MebVO> member = memberService.findMemberByEmail(email);
//	        model.addAttribute("member", member.orElse(null)); // 將會員資料添加到模型中
//	    } catch (Exception e) {
//	        model.addAttribute("error", "會員資料加載失敗");
//	    }
//	    return "front_end/index"; // 返回 Thymeleaf 模板名稱
//	}
//
//
//
////	@PostMapping("front_end/update")
////	public String updateProfile(@ModelAttribute MebVO member) {
////		memberService.updateMember(member);
////		return "redirect:/member/profile";
////	}
//
//	@GetMapping("/front_end/login")
//	public String login(HttpServletRequest request, Model model) {
//		CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
//		if (csrfToken != null) {
//			String token = csrfToken.getToken();
//			// 使用 token
//		}
////		return "front_end/login";
//		return "front_end/login";
//	}
//    // 顯示忘記密碼頁面
//    @GetMapping("front_end/forgot-password")
//    public String showForgotPasswordPage() {
//        return "front_end/forgot-password";
//    }
//
//    // 處理忘記密碼請求
//    @PostMapping("front_end/forgot-password")
//    public String forgotPassword(@RequestParam String mebMail, Model model) {
//        Optional<MebVO> member = memberService.findMemberByEmail(mebMail);
//
//        if (member.isPresent()) {
//            // 模擬發送重置郵件的邏輯
//            model.addAttribute("message", "密碼提示已發送至您的電子郵件！");
//        } else {
//            model.addAttribute("error", "該電子郵件未註冊！");
//        }
//        return "front_end/forgot-password";
//    }
//
//
//    // 檢查電子郵件是否已被使用
//    @PostMapping("front_end/checkEmail")
//    @ResponseBody
//    public Map<String, Boolean> checkEmail(@RequestBody Map<String, String> request) {
//        String email = request.get("email");
//        boolean exists = memberService.findMemberByEmail(email).isPresent();
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("exists", exists);
//        return response;
//    }
	
}