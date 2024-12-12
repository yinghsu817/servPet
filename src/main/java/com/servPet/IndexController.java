package com.servPet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.servPet.pg.model.PgRepository;
import com.servPet.pg.model.PgService;

@Controller
public class IndexController {

	@Autowired
	PgService pgSvc;

	@Autowired
	PgRepository pgRepository;

	@GetMapping("/")
	public String index(ModelMap model) {
		return "front_end/pg/index"; // 美容師前台首頁
	}
	
	@GetMapping("/back")
	public String indexBack(ModelMap model) {
		return "index"; // 美容師後台暫時假登入頁面
	}
	 // 映射到 back_end/login 頁面
    @GetMapping("/back_end/login")
    public String showLoginPage() {
        return "back_end/login"; // 返回 templates/back_end/login.html
    }

	

}
