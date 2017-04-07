package com.wumugulu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/session")
public class SessionController {

	@RequestMapping("/home")
	@ResponseBody
	public String sessionHome(HttpServletRequest request){
		// 使用session存儲驗證碼的話，超時時間就沒辦法單獨調整了
		System.out.println("home:  sessionId = " + request.getSession().getId());
		String verifyCode = "" + (int)((Math.random()*9+1)*100000);
		System.out.println("验证码的值保存进redis中，verifyCode=" + verifyCode);
		request.getSession().setAttribute("verifyCode", verifyCode);
		return "success - 这里显示登陆页面和相应的图形验证码";
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public String sessionLogin(HttpServletRequest request, @RequestParam(required=true)String myCode){
		System.out.println("login: sessionId = " + request.getSession().getId());
		// 从session中取出验证码，和前端提交的code进行比对，相同则登陆成功
		String verifyCode = (String) request.getSession().getAttribute("verifyCode");
		if(myCode.equalsIgnoreCase(verifyCode)){
			return "success - verifyCode is correct !";
		} else {
			return "failed  - verifyCode is wrong !!!";
		}
	}
	
}
