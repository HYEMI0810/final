package kh.spring.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.spring.config.SHA256;
import kh.spring.dto.PersonDTO;
import kh.spring.service.PersonService;
import kh.spring.service.certificationService;

@Controller
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService service;
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/join")
	public String join() {
		return "person/join";
	}
	
	@RequestMapping(value = "send",  produces = "application/String; charset=utf-8")
	@ResponseBody
	public String send(String phone) {
		Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        System.out.println("수신자 번호 : " + phone);
        System.out.println("인증번호 : " + numStr);
        certificationService.certifiedPhoneNumber(phone,numStr);
        return numStr;
    }
	
	@ResponseBody
	@RequestMapping(value="duplCheck",produces="text/html;charset=utf-8")
	public String duplCheck(String id) {
		
		int result = service.idDuplCheck(id);
		return String.valueOf(result);		
	}
	
	@RequestMapping("/joinProc")
	public String joinProc(PersonDTO dto) throws Exception{	
		String shaPass = SHA256.getSHA512(dto.getPw());
		dto.setPw(shaPass);
		service.insert(dto);
		return "redirect:/";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "person/login";
	}
	
	@RequestMapping("/loginProc")
	public String loginProc(PersonDTO dto) {
		String shaPass = SHA256.getSHA512(dto.getPw());
		dto.setPw(shaPass);
		int result = service.login(dto);
		if(result > 0) {
			session.setAttribute("loginID",dto.getId());
			}
		
		return "redirect:/";
	}
	
	@RequestMapping("/findId")
	public String findId() {
		return "person/findId";
	}
	
	@RequestMapping("/findIdProc")
	@ResponseBody
	public String findIdProc(PersonDTO dto) {
		String id = service.findid(dto);
		return id;
	}
}
