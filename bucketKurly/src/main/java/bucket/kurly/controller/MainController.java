package bucket.kurly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/main.do")
	public String main() {
		return "main";
	}
	
	@RequestMapping("login.do")
	public String login() {
		return "login";
	}
	
	//��������
	@RequestMapping("/notice.do")
	public String notice() {
		return "notice";
	}
	
	//�������� �Խñ� �󼼺���
	@RequestMapping("/notice_no.do")
	public String notice_no() {
		return "notice_no";
	}
}
