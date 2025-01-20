package kr.bit.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

    private int user_idx;

    @Size(min=2, max=4)
    @Pattern(regexp = "[가-힣]*")
    private String user_name;

    @Size(min=4, max=10)
    @Pattern(regexp ="[a-zA-Z0-9]*")
    private String user_id;

    @Size(min=4, max=10)
    @Pattern(regexp ="[a-zA-Z0-9]*")
    private String user_pw;

    @Size(min=4, max=10)
    @Pattern(regexp ="[a-zA-Z0-9]*")
    private String user_pw2;

    private boolean existId;
    private boolean userLogin;

    public User() {
        this.existId = false; //중복확인검사 안한걸로 초기화해줌
        this.userLogin = false; //로그인 안된것으로 초기화
    }

    public int getUser_idx() {
        return user_idx;
    }

    public void setUser_idx(int user_idx) {
        this.user_idx = user_idx;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getUser_pw2() {
        return user_pw2;
    }

    public void setUser_pw2(String user_pw2) {
        this.user_pw2 = user_pw2;
    }

    public boolean isExistId() {
        return existId;
    }

    public void setExistId(boolean existId) {
        this.existId = existId;
    }

    public boolean isUserLogin() {
        return userLogin;
    }

    public void setUserLogin(boolean userLogin) {
        this.userLogin = userLogin;
    }
}
