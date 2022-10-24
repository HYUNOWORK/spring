package com.meerket.project01.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.meerket.project01.domain.Member;
import com.meerket.project01.service.MemberService;

@Controller

// 
@SessionAttributes("member")
public class MemberController {

	private MemberService memberService;

	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping(value= {"/memberList", "/list"}, method=RequestMethod.GET)
	public String memberList(Model model) {
	// Service 클래스를 이용해 member 리스트를 가져옴

	List<Member> mList = memberService.memberList();
	/* 파라미터로 받은 모델 객체에 뷰로 보낼 모델을 저장한다.*/
	model.addAttribute("mList", mList);

	return "memberList";
	}
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, @RequestParam("userId") String userId, @RequestParam("pass") String pass,
			HttpSession session, HttpServletResponse response) throws ServletException, IOException {

		int result = memberService.login(userId, pass);

		// 

		// Id가 없을때
		if (result == -1) {
			response.setContentType("text/html: charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('아이디 혹은 비밀번호가 존재하지 않거나 틀렸습니다');");
			out.println(" history.back();");
			out.println("</script>");

			return null;

			// Id있고 비번 없을 때

		} else if (result == 0) {
			response.setContentType("text/html: charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('아이디 혹은 비밀번호가 존재하지 않거나 틀렸습니다');");
			out.println(" location.href='loginForm'");
			out.println("</script>");
			return null;
		}

		Member member = memberService.getMember(userId);
		session.setAttribute("isLogin", true);

		// Ŭ���� ������ @SessionAttributes("member") �ֳ����̼��� �����߰�, model�� member �Ӽ��� �߰��߱⿡ ���ǿ����� �����͸� ������

		model.addAttribute("member", member);
		System.out.println("member.name : " + member.getName());

		// �α����� �Ϸ�Ǹ� main page�� �̵�
		
		return "redirect:/main";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		// ���� ������ �����ϰ� ���ο� ������ ����
		session.invalidate();

		return "redirect:/main";
	}

	
	
	// view ���������� �޴� �� ����
	@RequestMapping("/joinResult")
	public String joinResult(Model model, Member member, String pass1, String emailId, String emailDomain,
			String phone1, String phone2, String phone3, String zipCode) {
		member.setPass(pass1);
		member.setEmail(emailId + "@" + emailDomain);
		member.setzipCode(zipCode);
		
		if (phone2.equals("") || phone3.equals("")) {
			member.setPhone("");
		} else {
			member.setPhone(phone1 + "-" + phone2 + "-" + phone3);
		}

		memberService.addMember(member);
		System.out.println("joinResult : " + member.getName());
		
		
		// �α��� ������ �����̷�Ʈ
		return "redirect:loginForm";
	}

	// ȸ������ ������ ������ �ߺ� ���̵� üũ ��û�� ó���ϴ� �޼���
	@RequestMapping("/overlapIdCheck")
	public String overlapIdCheck(Model model, String userId) {

		// ȸ�� ���̵� �ߺ� ���θ� �޾ƿ´�.
		boolean overlap = memberService.overlapIdCheck(userId);

		// model�� id�� ȸ�� ���̵� �ߺ� ���θ� ����
		model.addAttribute("userId", userId);
		model.addAttribute("overlap", overlap);
		
		return "forward:WEB-INF/views/member/overlapIdCheck.jsp";
	}

	// ���� �� ��û ó��
	@RequestMapping("/memberUpdateForm")
	public String updateForm(Model model, HttpSession session) {

		return "member/memberUpdateForm";
	}

	@RequestMapping("/memberUpdateResult")
	public String memberUpdateInfo(Model model, Member member, String pass1, String emailId, 
			String emailDomain, String phone1, String phone2, String phone3)
			 {
		member.setPass(pass1);
		member.setEmail(emailId + "@" + emailDomain);
		if (phone2.equals("") || phone3.equals("")) {
			member.setPhone("");
		} else {
			member.setPhone(phone1 + "-" + phone2 + "-" + phone3);
		}
		
		// MemberService�� ���� ȸ�� ���� ���� ���� �����͸� DB���� ����
		
		memberService.updateMember(member);
		System.out.println("memberUpdateResult : " + member.getUserId());
		/*
		 * Ŭ���� ������ @SessionAttributes({"member"}) �ֳ����̼��� �����ϰ� ��Ʈ�ѷ��� �޼��忡�� �Ʒ��� ���� ������
		 * �̸����� �𵨿� �߰��ϸ� �������� ���� ������ �����͸� ������ �ش�.
		 **/
		model.addAttribute("member", member);
		// �Խ� �� ����Ʈ�� �����̷�Ʈ ��Ų��.
		return "redirect:main";
	}

	// Kakao �α��� �޼���
	/*
	 * @RequestMapping(value = "/kakaoLogin", method = RequestMethod.GET) public
	 * String kakaoLogin(@RequestParam(value = "code", required = false) String
	 * code) throws Exception { System.out.println("#########" + code);
	 * 
	 * String access_Token = memberService.getAccessToken(code);
	 * System.out.println("###access_Token#### : " + access_Token);
	 * 
	 * return "redirect:/loginForm"; }
	 */
	
	// 회원 사진 등록
	@RequestMapping("/ImageForm.do")
	public String ImageForm() {
		return "memberImageForm";
	}
	
	
	
}
