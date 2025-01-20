package kr.bit.controller;


import kr.bit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//restAPI는 보통 주소로 조냄
@RestController
public class RestTestController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/chexistId/{user_id}")
    public String existId(@PathVariable String user_id) {
        boolean chk = userService.existId(user_id);
        return chk+"";
    }
}
