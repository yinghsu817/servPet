package com.servPet.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.servPet.admin.model.AdminService;
import com.servPet.admin.model.AdminVO;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminSvc;
	
		// 公共方法來檢查 admin 是否存在於 session 中
		private boolean isAdminLoggedIn(HttpSession session) {
		    return session.getAttribute("adminVO") != null;
		}
		
		
		//===<<  將admin存於 session 中一起送出 >>===/
		public String SaveSession(Model model, HttpSession session) {
		    // 檢查 session 是否包含 adminVO 資料
		    AdminVO admin = (AdminVO) session.getAttribute("adminVO");
		    // 在 model 中加入新的 AdminVO 物件，這樣可以在前端頁面中使用
		    model.addAttribute("adminVO", new AdminVO());
		    // 將 admin 資訊傳遞給前端頁面
		    model.addAttribute("admin", admin);  
		    // 若需要返回視圖名稱，可以根據需求返回
		    return null;  // 若只處理 session 和 model，這裡可以返回 null 或其它
		}
		
		//===<<  將admin存於 session 中一起送出 >>===/
				public String SaveSession(ModelMap model, HttpSession session) {
				    // 檢查 session 是否包含 adminVO 資料
				    AdminVO admin = (AdminVO) session.getAttribute("adminVO");
				    // 在 model 中加入新的 AdminVO 物件，這樣可以在前端頁面中使用
				    model.addAttribute("adminVO", new AdminVO());
				    // 將 admin 資訊傳遞給前端頁面
				    model.addAttribute("admin", admin);  
				    // 若需要返回視圖名稱，可以根據需求返回
				    return null;  // 若只處理 session 和 model，這裡可以返回 null 或其它
				}
		
		
		//===========<< 導向addAdmin >>============/
		@GetMapping("addAdmin")
		public String addAdmin(Model model, HttpSession session) {
		    // 呼叫 SaveSession 方法處理 session 和 model
			SaveSession(model, session);
			
		    // 檢查是否已經登入
		    if (!isAdminLoggedIn(session)) {
		        return "redirect:/back_end/toAdminLogin"; // 如果未登入，跳轉到登入頁面
		    }
		    // 返回新增管理員頁面
		    return "back_end/admin/addAdmin";
			}
		
		
		//===========<< 新增單一管理員資料 >>============/
		@PostMapping("insert")
		public String insert(@Valid AdminVO adminVO, BindingResult result, ModelMap model,
			@RequestParam("upFiles") MultipartFile[] parts,HttpSession session) throws IOException {
			SaveSession(model, session);
			result = removeFieldError(adminVO, result, "upFiles");
		
		if (result.hasErrors() || parts == null ||parts.length == 0 || parts[0].isEmpty()) {
			model.addAttribute("errorMessage", "管理員照片: 請上傳照片");
			System.out.println(result);
			System.out.println(parts);
		    return "back_end/admin/addAdmin"; // 返回新增頁面，並將錯誤訊息顯示在表單中
		}
		
//		// 處理文件上傳
//		if (parts.length == 0 || parts[0].isEmpty()) {
//			model.addAttribute("errorMessage", "管理員照片: 請上傳照片");
//		}
		else {
			// 將檔案轉換為 byte[] 存入 AdminVO
			for (MultipartFile multipartFile : parts) {
				byte[] buf = multipartFile.getBytes();
			adminVO.setUpFiles(buf); // 假設只處理單張上傳圖片
		}
		// 如果驗證錯誤或沒有選擇文件
//		if (result.hasErrors() || parts.length == 0 || parts[0].isEmpty()) {
//			return "back_end/admin/addAdmin"; // 返回新增頁面
		}
		// 新增管理員資料
		adminSvc.addAdmin(adminVO);
		// 顯示成功訊息並列出所有管理員
		model.addAttribute("adminListData", adminSvc.getAll());
		model.addAttribute("success", "- (新增成功)");
		return "redirect:/admin/listAllAdmin"; // 新增後重導至列表頁面
		}
		

		//===========<< 查詢單一管理員資料 >>============/
		@PostMapping("getOne_For_Display")
		public String getOneForDisplay(@RequestParam("adminId") 
		String adminIdStr, ModelMap model,HttpSession session) {
			SaveSession(model, session);
		// 使用正則表達式檢查 adminId 是否為有效的數字
		if (adminIdStr == null || !adminIdStr.matches("\\d+")) {
			model.addAttribute("errorMessage", "請提供有效的管理員編號");
			return "back_end/admin/select_admin_page"; // 返回管理員列表頁面
		}
		Integer adminId = Integer.parseInt(adminIdStr);
		AdminVO adminVO = adminSvc.getOneAdmin(adminId);
		if (adminVO == null) {
			model.addAttribute("errorMessage", "查無此管理員資料");
			return "back_end/admin/select_admin_page"; // 無此管理員則返回管理員管理頁面
		}
		model.addAttribute("adminVO", adminVO);
		return "back_end/admin/listOneAdmin"; // 返回顯示管理員資料的頁面
		}

		//===========<< 導向update頁面 >>============/
		@PostMapping("getOne_For_Update")
		public String getOne_For_Update(@RequestParam("adminId") String adminId, ModelMap model, HttpSession session) {
		    SaveSession(model, session);
		    AdminVO adminVO = adminSvc.getOneAdmin(Integer.valueOf(adminId));
		    if (adminVO == null) {
		        model.addAttribute("errorMessage", "無此管理員資料");
		        return "back_end/admin/select_admin_page"; // 返回管理員查詢頁面
		    }
		    model.addAttribute("adminVO", adminVO);
		    return "back_end/admin/update_admin_input"; // 查詢完成後轉交更新頁面
		}

		//===========<< 更新單一管理員資料 >>============/
		@PostMapping("update")
		public String update(@Valid AdminVO adminVO, BindingResult result, ModelMap model,
			@RequestParam("upFiles") MultipartFile[] parts,HttpSession session) throws IOException {
			 SaveSession(model, session);
		// 去除BindingResult中upFiles欄位的FieldError紀錄
		result = removeFieldError(adminVO, result, "upFiles");
		// 處理文件上傳
		if (parts.length == 0 || parts[0].isEmpty()) {
			// 如果沒有選擇新圖片，保留舊圖片
			byte[] upFiles = adminSvc.getOneAdmin(adminVO.getAdminId()).getUpFiles();
			adminVO.setUpFiles(upFiles);
		} else {
			// 上傳新圖片
			adminVO.setUpFiles(parts[0].getBytes()); // 假設只處理單張上傳圖片
		}
		// 如果驗證錯誤，返回更新頁面
		if (result.hasErrors()) {
			return "back_end/admin/update_admin_input";
		}
		// 更新管理員資料
		adminSvc.updateAdmin(adminVO);
		// 顯示更新成功訊息並重新查詢資料
		model.addAttribute("success", "- (修改成功)");
		adminVO = adminSvc.getOneAdmin(adminVO.getAdminId());
		model.addAttribute("adminVO", adminVO);
		return "back_end/admin/listOneAdmin"; // 更新後轉到管理員詳細頁面
	}

		//===========<< 更新單一管理員資料 >>============/
		@PostMapping("listAdmins_ByCompositeQuery")
		public String listAllAdmin(HttpServletRequest req, Model model,HttpSession session) {
		SaveSession(model, session);
		Map<String, String[]> map = req.getParameterMap();
		List<AdminVO> list = adminSvc.getAll(map);
		model.addAttribute("adminListData", list);
		return "back_end/admin/listAllAdmin"; // 顯示所有管理員
		}

		// 去除BindingResult中某個欄位的FieldError紀錄
		private BindingResult removeFieldError(AdminVO adminVO, BindingResult result, String removedFieldname) {
			List<org.springframework.validation.FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
				.collect(java.util.stream.Collectors.toList());
		result = new org.springframework.validation.BeanPropertyBindingResult(adminVO, "adminVO");
		for (org.springframework.validation.FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
		}
		
		
		//========<< 一鍵切換管理員狀態 >>=========//
		@PostMapping("toggleStatus")
		public @ResponseBody Map<String, String> toggleStatus(@RequestParam("adminId") String adminIdStr) {
			Map<String, String> response = new HashMap<>();
		// 檢查 adminId 是否為有效數字
		if (adminIdStr == null || !adminIdStr.matches("\\d+")) {
			response.put("status", "error");
			return response; // 如果 adminId 不是數字，返回錯誤
		}
		Integer adminId = Integer.parseInt(adminIdStr);
		AdminVO adminVO = adminSvc.getOneAdmin(adminId);
		if (adminVO == null) {
			response.put("status", "error");
			return response; // 如果找不到該管理員，返回錯誤
		}
		System.out.println("Received adminId: " + adminId);
		String newStatus = adminVO.getAdminAccStatus().equals("1") ? "0" : "1";
		adminVO.setAdminAccStatus(newStatus); // 更新狀態
		adminSvc.updateAdmin(adminVO); // 儲存更新
		System.out.println("Updated status to: " + newStatus); // 日誌輸出

		// 返回更新後的狀態
		response.put("status", "success");
		response.put("newStatus", newStatus);
		return response;
	}
		//========<< 導向select_admin_page >>=========//
		@GetMapping("/select_admin_page")
		public String goSelectPage(Model model, HttpSession session) {
		SaveSession(model, session);
	    // 檢查是否已經登入
	    if (!isAdminLoggedIn(session)) {
	        return "redirect:/back_end/toAdminLogin"; // 如果未登入，跳轉到登入頁面
	    }
	    // 從 session 獲取 adminVO
	    return "back_end/admin/select_admin_page"; // 返回選擇頁面
		}
		
		//========<< 查詢所有管理員 >>=========//
		 @GetMapping("/listAllAdmin")
		    public String listAllAdmin(Model model,HttpSession session) {
		        // 這裡假設 adminService.getAllAdmins() 是一個從資料庫中獲取所有管理員資料的方法
		        List<AdminVO> adminList = adminSvc.getAll();
		        // 將 adminList 加入到模型中
		        AdminVO admin = (AdminVO) session.getAttribute("adminVO");
		        model.addAttribute("adminListData", adminList);
		        model.addAttribute("admin", admin); // 將 admin 資訊傳遞給前端頁面
		        // 返回對應的視圖名稱，即 'back_end/admin/listAllAdmin'
		        return "back_end/admin/listAllAdmin";
		    }		 
		 		//========<< 查詢多資料用方法>>=========//
		 	@ModelAttribute("adminListData")  // for select_admin_page.html 第97 109行用 // for listAllAdmin.html 第85行用
			protected List<AdminVO> referenceListData(Model model) {
				
		    	List<AdminVO> list = adminSvc.getAll();
				return list;
			}
		 	
		 	
}
