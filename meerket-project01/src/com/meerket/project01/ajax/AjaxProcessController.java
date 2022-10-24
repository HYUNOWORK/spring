package com.meerket.project01.ajax;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meerket.project01.service.MemberService;

@Controller
public class AjaxProcessController {
	@Autowired

	private MemberService memberService;

//	MappingJackson2HttpMessageConverter에 의해서 JSON 형식으로 변환
	
	@RequestMapping("/passCheck.ajax")
	@ResponseBody
	public Map<String, Boolean> memberPassCheck(String userId, String pass) {
		
		boolean result = memberService.memberPassCheck(userId, pass);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("result", result);
		
		/*
		 \ MappingJackson2HttpMessageConverter에 의해서 Map 객체가 아래와 같이 json 형식으로 변환된다. 
		 {"result": 0} 또는 {"result": 2}*/
		
		
		return map;
	}
}