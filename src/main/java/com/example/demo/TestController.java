package com.example.demo;

import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TestController {
	@GetMapping("sign_in")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login*")
	  public String login(@ModelAttribute("user") String user,
	      @RequestAttribute(name = WebAttributes.AUTHENTICATION_EXCEPTION, required = false) Exception exception,
	      Model model) {

	    // 入力されたユーザ名をセットする
	    model.addAttribute("message", user);
	    // exceptionがnullではない（エラー）場合、エラーメッセージをセットする
	    if (exception != null) {
	      model.addAttribute("message", exception.getMessage());
	    }
	    // login.htmlを表示
	    return "login";
	  }
}
