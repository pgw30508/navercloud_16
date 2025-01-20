package kr.bit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //클라이언트의 요청 처리 , 뷰 반환
public class HomeController {

    //http://localhost:8080/url_mapping/
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){  //핸들러 메소드
        return "redirect:/main"; //main.jsp를 첫 화면으로
    }
    @GetMapping("/main")
    public String main(){
        return "main";
    }
}
