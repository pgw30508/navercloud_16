package kr.bit.controller;

import kr.bit.beans.User;
import kr.bit.service.UserService;
import kr.bit.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService; //컨트롤러 전후단계 - 비즈니스로직(service)
    
    @Resource(name="loginBean")
    private User loginBean; // 로그인 여부확인하기 위해 세션영역에 담아놓은것 주입받음
    @Autowired
    private User user;

    @GetMapping("/join")
    public String join(@ModelAttribute("joinBean") User joinBean) {
        return "user/join";
    }

    @PostMapping("/join_pro")
    public String join_pro(@Valid @ModelAttribute("joinBean") User joinBean, BindingResult result) {
        if (result.hasErrors()) {
            return "user/join";
        }

        userService.addUser(joinBean);
        return "user/join_success";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("loginProBean") User loginProBean, Model model,
                        @RequestParam(value="fail", defaultValue = "false") boolean fail) {

        model.addAttribute("fail", fail);

        return "user/login";
    }

    @PostMapping("/login_pro")
    public  String login_pro(@Valid @ModelAttribute("loginProBean") User loginProBean, BindingResult result) {
        if (result.hasErrors()) {
            return "user/login";
        }

        //로그인 성공하면(id, pw 일치하면) user_idx, user_name 추출
        userService.getLoginUser(loginProBean);

        if(loginBean.isUserLogin()==true) {
            return "user/login_success";
        }
        else {
            return "user/login_fail";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        loginBean.setUserLogin(false);
        return "user/logout";
    }

    @GetMapping("/not_login")
    public String not_login() {
        return "user/not_login";
    }

    @GetMapping("/modify")
    public String modify(@ModelAttribute("modifyBean") User modifyBean) {
        userService.getModifyUser(modifyBean);
        return "user/modify";
    }

    @PostMapping("/modify_pro")
    public String modify_pro(@Valid @ModelAttribute("modifyBean") User modifyBean,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "user/modify";
        }
        userService.modifyUser(modifyBean);
        return "user/modify_success";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        UserValidator userValidator = new UserValidator();
        binder.addValidators(userValidator);
    }
}
