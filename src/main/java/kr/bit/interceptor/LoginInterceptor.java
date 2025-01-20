package kr.bit.interceptor;


import kr.bit.beans.BoardInfo;
import kr.bit.beans.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//로그인 확인처리
public class LoginInterceptor implements HandlerInterceptor {
    
    private User loginBean;
    
    public LoginInterceptor(User loginBean) {
        this.loginBean = loginBean;
    }
    //로그인 안되어있음 주소를 입력해도 못들어가도록 막을거임

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        if(loginBean.isUserLogin()==false) {
            String str=request.getContextPath(); //첫경로 알아내서
            
            response.sendRedirect(str+"/user/not_login"); //로그인 안되어있음 이 주소로 강제 이동시킴
            return false;
        }
        return true;
    }
}
